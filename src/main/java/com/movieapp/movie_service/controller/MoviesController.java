package com.movieapp.movie_service.controller;

import com.movieapp.movie_service.service.entity.Movie;
import com.movieapp.movie_service.service.entity.MovieDTO;
import com.movieapp.movie_service.service.service.MoviesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin
@RequiredArgsConstructor
public class MoviesController {

    private final MoviesService moviesService;

    @GetMapping()
    public ResponseEntity<Page<Movie>> getAllMovies(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize
    ) {
        return ResponseEntity.ok(moviesService.getAllMovies(pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable String id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(moviesService.getMovie(id));
    }

    @PostMapping
    public void addMovie(@RequestBody MovieDTO movie)  {
        moviesService.addMovie(movie);
    }

    @PutMapping
    public void updateMovie(@RequestBody MovieDTO movie) throws ChangeSetPersister.NotFoundException {
        moviesService.updateMovie(movie);
    }

    @DeleteMapping("{id}")
    public void deleteMovie(@PathVariable String id) throws ChangeSetPersister.NotFoundException {
        moviesService.deleteMovie(id);
    }
}
