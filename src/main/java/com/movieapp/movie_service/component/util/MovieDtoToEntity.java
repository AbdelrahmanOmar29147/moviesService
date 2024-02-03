package com.movieapp.movie_service.component.util;

import com.movieapp.movie_service.service.entity.Movie;
import com.movieapp.movie_service.service.entity.MovieDT0;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MovieDtoToEntity {

    public static Movie mapper(MovieDT0 movieDT0){
        return Movie
                .builder()
                .title(movieDT0.getTitle())
                .overview(movieDT0.getOverview())
                .vote_average(movieDT0.getVote_average())
                .original_language(movieDT0.getOriginal_language())
                .budget(movieDT0.getBudget())
                .revenue(movieDT0.getRevenue())
                .runtime(movieDT0.getRuntime())
                .genres(movieDT0.getGenres())
                .production_country(movieDT0.getProduction_country())
                .status(movieDT0.getStatus())
                .tagline(movieDT0.getTagline())
                .backdrop_path(movieDT0.getBackdrop_path())
                .poster_path(movieDT0.getPoster_path())
                .build();
    }
}
