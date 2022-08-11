package com.sentisquare.tdidfresolver.controller;

import com.sentisquare.tdidfresolver.dto.TextDto;
import com.sentisquare.tdidfresolver.dto.WordListDto;
import com.sentisquare.tdidfresolver.exception.NoDataException;
import com.sentisquare.tdidfresolver.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class WordController {

    @Autowired
    WordService wordService;

    @ExceptionHandler(NoDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNoTrainedData() {
        return "There is no completed trained data right now";
    }

    @PostMapping("/train")
    public void train(@RequestBody String text) {
        wordService.train(text);
    }

    @PostMapping("/process")
    public WordListDto process(@RequestBody TextDto textDto) {
        return new WordListDto(wordService.countIdf(textDto.getText()));
    }
}
