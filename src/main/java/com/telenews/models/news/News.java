package com.telenews.models.news;

import com.telenews.common.DateAudit;
import com.telenews.models.comment.Comment;
import com.telenews.models.role.RoleName;
import com.telenews.models.subject.Subject;
import com.telenews.models.user.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "news")
public class News extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    @NotNull
    private String title;
    @NotNull
    private String summary;
    @NotNull
    private String text;
    private String tags;
    private String reference;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    @NotNull
    private Status status;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "news")
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "news_subject",
            joinColumns = { @JoinColumn(name = "news_id") },
            inverseJoinColumns = { @JoinColumn(name = "subject_id") })
    private Set<Subject> subjects = new HashSet<>();


}
