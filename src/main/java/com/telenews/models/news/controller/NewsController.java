package com.telenews.models.news.controller;


import com.telenews.common.BaseDto;
import com.telenews.common.Constants;
import com.telenews.models.news.dto.NewsDto;
import com.telenews.models.news.dto.NewsForm;
import com.telenews.models.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
            return (ResponseEntity.status(HttpStatus.OK).body(new BaseDto(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND)));
    }

    @PostMapping(value = Constants.KEY_API_NEWS + "/update", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@RequestBody @Valid NewsForm form) {
        if (service.update(form))
            return (ResponseEntity.status(HttpStatus.OK).body(new BaseDto(HttpStatus.OK.value(), Constants.KEY_UPDATE_NEWS)));
        else
            return (ResponseEntity.status(HttpStatus.OK).body(new BaseDto(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND)));
    }

    @PostMapping(value = Constants.KEY_API_NEWS + "/status", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> setNewdStatus(@RequestParam(name = "id") String id,String status) {
        if (service.setNewsStatus(id,status))
            return (ResponseEntity.status(HttpStatus.OK).body(new BaseDto(HttpStatus.OK.value(), Constants.KEY_UPDATE_NEWS)));
        else
            return (ResponseEntity.status(HttpStatus.OK).body(new BaseDto(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND)));
    }

    @GetMapping(value = Constants.KEY_API_NEWS , produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findById(@RequestParam(name = "id") String id){
        Optional<NewsDto> news = service.findById(id);
        if (news.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(news);
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(HttpStatus.OK.value(),Constants.KEY_NOT_FOUND));
    }

    @GetMapping(value = Constants.KEY_API_NEWS +"/all" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll(@RequestParam(name = "pages") Integer pages){
        Optional<NewsDto> news = service.findAll(pages);
        if (news.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(news);
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(HttpStatus.OK.value(),Constants.KEY_NOT_FOUND));
    }

    @GetMapping(value = Constants.KEY_API_NEWS +"/all/tags" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAllByTags(@RequestParam(name = "tags") String tags,@RequestParam(name = "pages") Integer pages){
        Optional<NewsDto> news = service.findAllByTags(tags,pages);
        if (news.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(news);
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(HttpStatus.OK.value(),Constants.KEY_NOT_FOUND));
    }

    @GetMapping(value = Constants.KEY_API_NEWS +"/all/status" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAllByStatus(@RequestParam(name = "status") String status,@RequestParam(name = "pages") Integer pages){
        Optional<NewsDto> news = service.findAllByStatus(status,pages);
        if (news.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(news);
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(HttpStatus.OK.value(),Constants.KEY_NOT_FOUND));
    }

    @GetMapping(value = Constants.KEY_API_NEWS +"/all/user" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAllByUser(@RequestParam(name = "userid") String userid,@RequestParam(name = "pages") Integer pages){
        Optional<NewsDto> news = service.findAllByUser(userid,pages);
        if (news.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(news);
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(HttpStatus.OK.value(),Constants.KEY_NOT_FOUND));
    }

    @GetMapping(value = Constants.KEY_API_NEWS +"/all/subject" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAllBySubject(@RequestParam(name = "subject") String subject,@RequestParam(name = "pages") Integer pages){
        Optional<NewsDto> news = service.findAllBySubject(subject,pages);
        if (news.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(news);
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDto(HttpStatus.OK.value(),Constants.KEY_NOT_FOUND));
    }
}
