package com.movieApp.moviesService.repository;

import com.movieApp.moviesService.service.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoviesRepository extends MongoRepository<Movie, String> {
    Optional<Movie> findMovieByTitle(String id);
}
