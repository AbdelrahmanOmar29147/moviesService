package com.movieApp.moviesService.movies;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Movie {

    @Id
    private String id;
    @Indexed(unique = true)
    private String title;
    private String overview;
    private Long vote_average;
    private String original_language;
    private Integer budget;
    private Integer revenue;
    private Integer runtime;
    private String[] genres;
    private String production_country;
    private String status;
    private String tagline;
    private String backdrop_path;
    private String poster_path;
}
