package com.movieapp.movie_service.service.entity;

import lombok.Data;

@Data
public class MovieDT0 {

    private String title;
    private String overview;
    private Long vote_average;
    private String original_language;
    private Integer budget;
    private Integer revenue;
    private String[] genres;
    private Integer runtime;
    private String production_country;
    private String status;
    private String tagline;
    private String backdrop_path;
    private String poster_path;
}
