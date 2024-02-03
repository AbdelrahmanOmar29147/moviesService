package com.movieapp.movie_service.repository;

import com.movieapp.movie_service.service.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoviesRepository extends MongoRepository<Movie, String> {
    Optional<Movie> findMovieByTitle(String id);
}
