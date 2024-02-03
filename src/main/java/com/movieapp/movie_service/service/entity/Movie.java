package com.movieapp.movie_service.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@Builder
public class Movie {
    @Id
    private String id;
    @Indexed(unique = true)
    private String title;
    private String overview;
    private Long voteAverage;
    private String originalLanguage;
    private Integer budget;
    private Integer revenue;
    private Integer runtime;
    private String[] genres;
    private String productionCountry;
    private String status;
    private String tagline;
    private String backdropPath;
    private String posterPath;
}
