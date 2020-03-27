package com.telenews.models.news.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.telenews.common.BaseDto;
import com.telenews.models.news.News;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsDto extends BaseDto {
    private List<News> data;
}
