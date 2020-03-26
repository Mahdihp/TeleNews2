package com.telenews.models.news.repository;

import com.telenews.models.comment.Comment;
import com.telenews.models.news.News;
import com.telenews.models.news.Status;
import com.telenews.models.subject.Subject;
import com.telenews.models.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepo extends PagingAndSortingRepository<News, Long> {

    News findByCode(String code);
    News findByTitle(String title);
    List<News> findAllByStatus(Status status, Pageable pageable);
    List<News> findAllByTagsContains(String tags, Pageable pageable);
    List<News> findAllByUser(User user, Pageable pageable);
    List<News> findAllByComments(Comment comment, Pageable pageable);
    List<News> findAllBySubjects(Subject subject, Pageable pageable);

}
