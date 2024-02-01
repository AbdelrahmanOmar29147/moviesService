package com.movieApp.moviesService.movies;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.movieApp.moviesService.config.AuthFilter;
import com.movieApp.moviesService.config.WireMockConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest()
@ContextConfiguration(classes = {WireMockConfig.class})
public class MoviesControllerSecurityTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private WireMockServer wireMockServer;

    private MockMvc mockMvc;

    @Autowired
    AuthFilter authFilter;

    @MockBean
    MoviesRepository moviesRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(authFilter).build();
    }

    String INVALID_TOKEN = "IAMAVERYREALTOKEN";
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


    @Test
    void getMovieWithValidToken() throws Exception {
        this.wireMockServer.stubFor(get("/api/v1/auth/validate").willReturn(aResponse().withStatus(200)));
        Mockito.when(moviesRepository.findById("1")).thenReturn(Optional.ofNullable(MOVIE_1));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/movies/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + INVALID_TOKEN)        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.title").value("Rayven Yor"));

    }

    @Test
    void getMovieNoHeader_failure() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/movies/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getMovieInvalidToken_failure() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/movies/2")
                        .header("Authorization", "Bearer " + INVALID_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}
