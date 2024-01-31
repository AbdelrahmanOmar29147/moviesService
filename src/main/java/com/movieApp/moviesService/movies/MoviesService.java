package com.movieApp.moviesService.movies;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MoviesService {

    private final MoviesRepository moviesRepository;

    public Page<Movie> getAllMovies(Integer pageNo, int pageSize) {
        Pageable page = PageRequest.of(pageNo, pageSize);
        return moviesRepository.findAll(page);
    }

    public Movie getMovie(String id) throws NotFoundException {
        return moviesRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void addMovie(Movie movie) {
        moviesRepository.insert(movie);
    }

    public void updateMovie(Movie movie) throws NotFoundException {
        String id = moviesRepository.findMovieByTitle(movie.getTitle()).orElseThrow(NotFoundException::new).getId();
        movie.setId(id);
        moviesRepository.save(movie);
    }

    public void deleteMovie(String id) throws NotFoundException {
        Movie movie = moviesRepository.findById(id).orElseThrow(NotFoundException::new);
        moviesRepository.delete(movie);
    }
}
