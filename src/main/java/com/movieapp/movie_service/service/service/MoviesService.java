package com.movieapp.movie_service.service.service;

import com.movieapp.movie_service.component.util.MovieDtoToEntity;
import com.movieapp.movie_service.service.entity.Movie;
import com.movieapp.movie_service.repository.MoviesRepository;
import com.movieapp.movie_service.service.entity.MovieDT0;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public void addMovie(MovieDT0 movie) {
        Movie movieEntity = MovieDtoToEntity.mapper(movie);
        moviesRepository.insert(movieEntity);
    }

    public void updateMovie(MovieDT0 movie) throws NotFoundException {
        Movie movieEntity = moviesRepository.findMovieByTitle(movie.getTitle()).orElseThrow(NotFoundException::new);
        moviesRepository.save(movieEntity);
    }

    public void deleteMovie(String id) throws NotFoundException {
        Movie movie = moviesRepository.findById(id).orElseThrow(NotFoundException::new);
        moviesRepository.delete(movie);
    }
}
