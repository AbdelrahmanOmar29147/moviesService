package com.movieapp.movie_service.service.entity;

import lombok.Data;

@Data
public class MovieDTO {

    private String title;
    private String overview;
    private Long voteAverage;
    private String originalLanguage;
    private Integer budget;
    private Integer revenue;
    private String[] genres;
    private Integer runtime;
    private String productionCountry;
    private String status;
    private String tagline;
    private String backdropPath;
    private String posterPath;
}
