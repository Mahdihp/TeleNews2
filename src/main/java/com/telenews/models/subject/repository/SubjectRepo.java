package com.telenews.models.subject.repository;

import com.telenews.models.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepo extends JpaRepository<Subject,Long> {

        List<Subject> findAllBySubject(Subject subject);
        Subject findByName(String name);
}
