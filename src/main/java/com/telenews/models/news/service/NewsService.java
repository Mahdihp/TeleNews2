package com.telenews.models.news.service;

import com.telenews.config.Constants;
import com.telenews.models.news.News;
import com.telenews.models.news.Status;
import com.telenews.models.news.dto.NewsDto;
import com.telenews.models.news.dto.NewsForm;
import com.telenews.models.news.repository.NewsRepo;
import com.telenews.models.role.Role;
import com.telenews.models.subject.Subject;
import com.telenews.models.subject.repository.SubjectRepo;
import com.telenews.models.user.User;
import com.telenews.models.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class NewsService {

    @Autowired
    private NewsRepo newsRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SubjectRepo subjectRepo;


    public boolean create(NewsForm form) {
        Optional<User> user = userRepo.findByUserName(form.getUserName());
        if (user.isPresent()) {
            News news = new News();
            news.setCode(form.getCode());
            news.setTitle(form.getTitle());
            news.setSummary(form.getSummary());
            news.setText(form.getText());
            news.setTags(form.getTags());
            news.setReference(form.getReference());
            news.setStatus(Status.valueOf(form.getStatus()));
            news.setUser(user.get());
            Set<Subject> subjects = new HashSet<>();
            for (String subject : form.getSubjectsId()) {
                Optional<Subject> subject1 = subjectRepo.findById(Long.valueOf(subject));
                if (subject1.isPresent()) {
                    subjects.add(subject1.get());
                }
            }
            news.setSubjects(subjects);
            newsRepo.save(news);
            return true;
        }
        return false;
    }

    public boolean update(NewsForm form) {
        Optional<News> news = newsRepo.findById(Long.valueOf(form.getId()));
        if (news.isPresent()) {
            news.get().setTitle(form.getTitle());
            news.get().setSummary(form.getSummary());
            news.get().setText(form.getText());
            news.get().setTags(form.getTags());
            news.get().setReference(form.getReference());
            news.get().setStatus(Status.valueOf(form.getStatus()));
            Set<Subject> subjects = new HashSet<>();
            for (String subject : form.getSubjectsId()) {
                Optional<Subject> subject1 = subjectRepo.findById(Long.valueOf(subject));
                if (subject1.isPresent()) {
                    subjects.add(subject1.get());
                }
            }
            news.get().setSubjects(subjects);
            newsRepo.save(news.get());
            return true;
        }
        return false;
    }

    public boolean setNewsStatus(String id, String status) {
        Optional<News> news = newsRepo.findById(Long.valueOf(id));
        if (news.isPresent()) {
            news.get().setStatus(Status.valueOf(status));
            newsRepo.save(news.get());
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<NewsDto> findById(String id) {
        Optional<News> news = newsRepo.findById(Long.valueOf(id));
        if (news.isPresent()) {
            NewsDto newsDto = new NewsDto();
            newsDto.setStatus(HttpStatus.OK.value());
            newsDto.setMessage(Constants.KEY_SUCESSE);
            List<News> dtoList = new ArrayList<>();
            dtoList.add(news.get());
            newsDto.setData(dtoList);
            return Optional.ofNullable(newsDto);
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<NewsDto> findAll(Integer page) {
        Pageable paging = PageRequest.of(page, 10);
        NewsDto newsDto = new NewsDto();
        Page<News> list = newsRepo.findAll(paging);
        if (list != null) {
            newsDto.setStatus(HttpStatus.OK.value());
            newsDto.setMessage(Constants.KEY_SUCESSE);
            newsDto.setData(list.getContent());
            return Optional.ofNullable(newsDto);
        }
        return Optional.empty();
    }

    public Optional<NewsDto> findAllByTags(String tags, Integer page) {
        Pageable paging = PageRequest.of(page, 10);
        NewsDto newsDto = new NewsDto();
        List<News> list = newsRepo.findAllByTagsContains(tags, paging);
        if (list != null) {
            newsDto.setStatus(HttpStatus.OK.value());
            newsDto.setMessage(Constants.KEY_SUCESSE);
            newsDto.setData(list);
            return Optional.ofNullable(newsDto);
        }
        return Optional.empty();
    }

    public Optional<NewsDto> findAllByStatus(String status, Integer page) {
        Pageable paging = PageRequest.of(page, 10);
        NewsDto newsDto = new NewsDto();
        List<News> list = newsRepo.findAllByStatus(Status.valueOf(status), paging);
        if (list != null) {
            newsDto.setStatus(HttpStatus.OK.value());
            newsDto.setMessage(Constants.KEY_SUCESSE);
            newsDto.setData(list);
            return Optional.ofNullable(newsDto);
        }
        return Optional.empty();
    }

    public Optional<NewsDto> findAllByUser(String userId, Integer page) {
        Pageable paging = PageRequest.of(page, 10);
        NewsDto newsDto = new NewsDto();
        Optional<User> user = userRepo.findById(Long.valueOf(userId));
        if (user.isPresent()) {
            List<News> list = newsRepo.findAllByUser(user.get(), paging);
            if (list != null) {
                newsDto.setStatus(HttpStatus.OK.value());
                newsDto.setMessage(Constants.KEY_SUCESSE);
                newsDto.setData(list);
                return Optional.ofNullable(newsDto);
            }
        }
        return Optional.empty();
    }

    public Optional<NewsDto> findAllBySubject(String subject, Integer page) {
        Pageable paging = PageRequest.of(page, 10);
        NewsDto newsDto = new NewsDto();
        Subject subject1 = subjectRepo.findByName(subject);
        if (subject1 != null) {
            List<News> list = newsRepo.findAllBySubjects(subject1, paging);
            if (list != null) {
                newsDto.setStatus(HttpStatus.OK.value());
                newsDto.setMessage(Constants.KEY_SUCESSE);
                newsDto.setData(list);
                return Optional.ofNullable(newsDto);
            }
        }
        return Optional.empty();
    }
}
