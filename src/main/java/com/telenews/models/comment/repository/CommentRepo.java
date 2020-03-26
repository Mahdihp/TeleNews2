package com.telenews.models.comment.repository;

import com.telenews.models.comment.Comment;
import com.telenews.models.news.News;
import com.telenews.models.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends PagingAndSortingRepository<Comment, Long> {

    List<Comment> findAllByUser(User user, Pageable pageable);
    List<Comment> findAllByNews(News news, Pageable pageable);
}
