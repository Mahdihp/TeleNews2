package com.telenews.models.news.dto;

import com.telenews.models.news.Status;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
public class NewsForm {

    private String id;
    private String code;
    @NotNull
    private String title;
    @NotNull
    private String summary;
    @NotNull
    private String text;
    private String tags;
    private String reference;
    @NotNull
    private String status;
    private String userName;
    private List<String> subjectsId;
}
