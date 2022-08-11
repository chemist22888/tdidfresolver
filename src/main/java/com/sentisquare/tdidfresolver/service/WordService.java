package com.sentisquare.tdidfresolver.service;

import com.sentisquare.tdidfresolver.dto.WordDto;
import com.sentisquare.tdidfresolver.exception.NoDataException;

import java.util.List;

public interface WordService {
    List<WordDto> countIdf(String text) throws NoDataException;
    void train(String input);

//    Long countIdf(String word);
}
