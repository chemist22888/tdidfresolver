package com.sentisquare.tdidfresolver.mapper;

import com.sentisquare.tdidfresolver.domain.Word;
import com.sentisquare.tdidfresolver.dto.WordDto;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Component
public class WordMapper {
    public WordDto toDto(Word entity) {
        WordDto dto = new WordDto();
        dto.setWord(entity.getWord());
        return dto;
    }
    public Word toWord(WordDto wordDto) {
        Word word = new Word();
        word.setWord(wordDto.getWord());

        return word;
    }
}
