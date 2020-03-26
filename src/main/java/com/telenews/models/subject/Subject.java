package com.telenews.models.subject;

import com.telenews.models.news.News;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "subjects")
    private Set<News> posts = new HashSet<>();

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="subject_id")
    private Subject subject;

    @OneToMany(mappedBy="subject")
    private Set<Subject> subordinates = new HashSet<>();
}
