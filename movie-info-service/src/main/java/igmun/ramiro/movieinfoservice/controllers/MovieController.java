package igmun.ramiro.movieinfoservice.controllers;

import igmun.ramiro.movieinfoservice.models.Movie;
import igmun.ramiro.movieinfoservice.services.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable String movieId) {
        return movieService.getDetail(movieId);
    }

}
