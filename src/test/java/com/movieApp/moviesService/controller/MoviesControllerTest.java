package com.movieApp.moviesService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieApp.moviesService.service.entity.Movie;
import com.movieApp.moviesService.repository.MoviesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest()
class MoviesControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    MoviesRepository moviesRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    Movie MOVIE_1 = new Movie(
            "1",
            "Rayven Yor",
            "Welcome to a world without rules.",
            (long) 9.627,
            "en",
            120,
            30007,
            127,
            new String[]{"Comedy", "Action"},
            "US",
            "Active",
            "THE BEST TAGLINE!",
            "/PICTURE",
            "/POSTER"
    );
    Movie MOVIE_2 = new Movie(
            "2",
            "David Landup",
            "Welcome to a world without rules.",
            (long) 9.627,
            "en",
            120,
            30007,
            127,
            new String[]{"Comedy", "Action"},
            "US",
            "Active",
            "THE BEST TAGLINE!",
            "/PICTURE",
            "/POSTER"
    );
    Movie MOVIE_3 = new Movie(
            "3",
            "Jane Doe",
            "Welcome to a world without rules.",
            (long) 9.627,
            "en",
            120,
            30007,
            127,
            new String[]{"Comedy", "Action"},
            "US",
            "Active",
            "THE BEST TAGLINE!",
            "/PICTURE",
            "/POSTER"
    );


    @Test
    void getAllMoviesPage_success() throws Exception {
        List<Movie> movies = new ArrayList<>(Arrays.asList(MOVIE_1, MOVIE_2, MOVIE_3));
        Page<Movie> moviesPage = new PageImpl<Movie>(movies);
        Mockito.when(moviesRepository.findAll(any(PageRequest.class))).thenReturn(moviesPage);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/movies?pageNo=1&pageSize=5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].id").value(movies.get(0).getId()))
                .andExpect(jsonPath("$.content.[0].title").value(movies.get(0).getTitle()))
                .andExpect(jsonPath("$.content.[1].id").value(movies.get(1).getId()))
                .andExpect(jsonPath("$.content.[1].title").value(movies.get(1).getTitle()))
                .andDo(print());
    }

    @Test
    void getMovie_success() throws Exception {
        Mockito.when(moviesRepository.findById(MOVIE_2.getId())).thenReturn(Optional.ofNullable(MOVIE_2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.title").value("David Landup"));
    }

    @Test
    void addMovie_success() throws Exception {
        Movie movie = Movie.builder()
                .title("Harry Potter")
                .backdrop_path("/Harry Backdrop")
                .budget(20000)
                .build();
        Mockito.when(moviesRepository.insert(movie)).thenReturn(movie);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(movie)))
                .andExpect(status().isOk());
    }

    @Test
    void updateMovie_success() throws Exception {
        Movie movie = Movie.builder()
                .id("4")
                .title("Harry Potter")
                .backdrop_path("/Harry Backdrop")
                .budget(20000)
                .build();
        Mockito.when(moviesRepository.findMovieByTitle("Harry Potter")).thenReturn(Optional.of(movie));
        Mockito.when(moviesRepository.save(movie)).thenReturn(movie);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(movie)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteMovie() throws Exception {
//        Mockito.when(moviesRepository.deleteById(MOVIE_2.getId())).thenReturn(Optional.of(MOVIE_1));
        Mockito.when(moviesRepository.findById(MOVIE_2.getId())).thenReturn(Optional.of(MOVIE_2));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/movies/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}