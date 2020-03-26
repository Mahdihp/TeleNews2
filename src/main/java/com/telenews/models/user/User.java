package com.telenews.models.user;

import com.telenews.common.DateAudit;
import com.telenews.models.comment.Comment;
import com.telenews.models.news.News;
import com.telenews.models.role.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String passWord;
    private String firstName;
    private String lastName;
    private String displayName;
    private String email;
    private String adderss;
    private boolean active;

    @OneToMany(cascade = CascadeType.ALL,
              fetch = FetchType.LAZY,
              mappedBy = "user")
    private Set<News> news = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
