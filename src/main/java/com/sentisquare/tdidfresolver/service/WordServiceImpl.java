package com.sentisquare.tdidfresolver.service;

import com.sentisquare.tdidfresolver.domain.Word;
import com.sentisquare.tdidfresolver.dto.WordDto;
import com.sentisquare.tdidfresolver.exception.NoDataException;
import com.sentisquare.tdidfresolver.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class WordServiceImpl implements WordService {
    @Autowired
    WordRepository wordRepository;

    @Override
    public List<WordDto> countIdf(String text) throws NoDataException {
        if (wordRepository.isEmpty())
            throw new NoDataException();

        List<WordDto> wordDtoList = new LinkedList<>();
        String[] wordsArray = text.split("\\P{L}+");
        List<String> words = Arrays.stream(wordsArray).map(this::normalize).collect(Collectors.toList());

        Map<String, Double> wordTdMap = words.stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.summingDouble(e -> 1d / wordsArray.length)));


        wordTdMap.forEach((key, value) -> wordRepository.findByWord(key).ifPresent(word ->
                wordDtoList.add(new WordDto(word.getWord(), word.getIdf() * value))
        ));

        wordDtoList.sort(Comparator.comparing(WordDto::getTdidf));

        return wordDtoList;
    }

    @Override
    @Transactional
    public void train(String input) {
        List<String> docs = List.of(input.split("\n"));

        Map<String, Integer> res = new HashMap<>();
        docs.forEach(document -> {
            String[] wordsArray = document.split("\\P{L}+");

            Set<String> words = Arrays.stream(wordsArray).map(this::normalize).collect(Collectors.toSet());

            words.forEach(word ->
                    res.put(word, res.getOrDefault(word, 0) + 1)
            );

        });
        wordRepository.deleteAll();

        int documentsCount = docs.size();

        res.forEach((key, value) -> {
            double idf = Math.log10((double) documentsCount / value);

            Word word = new Word();
            word.setWord(key);
            word.setIdf(idf);

            wordRepository.save(word);
        });

    }

    private String normalize(String word) {
        return Normalizer.normalize(word, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
    }
}
