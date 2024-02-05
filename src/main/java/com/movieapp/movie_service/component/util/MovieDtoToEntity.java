package com.movieapp.movie_service.component.util;

import com.movieapp.movie_service.service.entity.Movie;
import com.movieapp.movie_service.service.entity.MovieDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MovieDtoToEntity {

    public static Movie mapper(MovieDTO movieDT0){
        return Movie
                .builder()
                .title(movieDT0.getTitle())
                .overview(movieDT0.getOverview())
                .voteAverage(movieDT0.getVoteAverage())
                .originalLanguage(movieDT0.getOriginalLanguage())
                .budget(movieDT0.getBudget())
                .revenue(movieDT0.getRevenue())
                .runtime(movieDT0.getRuntime())
                .genres(movieDT0.getGenres())
                .productionCountry(movieDT0.getProductionCountry())
                .status(movieDT0.getStatus())
                .tagline(movieDT0.getTagline())
                .backdropPath(movieDT0.getBackdropPath())
                .posterPath(movieDT0.getPosterPath())
                .build();
    }
}
