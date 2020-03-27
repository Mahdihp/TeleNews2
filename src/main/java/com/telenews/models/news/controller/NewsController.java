package com.telenews.models.news.controller;


import com.telenews.common.BaseDto;
import com.telenews.common.Constants;
import com.telenews.models.news.dto.NewsForm;
import com.telenews.models.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.KEY_API_VER)
public class NewsController {
    @Autowired
    private NewsService service;

    @PostMapping(value = Constants.KEY_API_NEWS + "/add", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> add(@RequestBody @Valid NewsForm form) {
        if (service.create(form))
            return (ResponseEntity.status(HttpStatus.OK).body(new BaseDto(HttpStatus.OK.value(), Constants.KEY_CREATE_NEWS)));
        else
            return (ResponseEntity.status(HttpStatus.OK).body(new BaseDto(HttpStatus.OK.value(), Constants.KEY_FAIL)));
    }
}
