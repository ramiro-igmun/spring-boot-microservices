package igmun.ramiro.movieinfoservice.repositories;

import igmun.ramiro.movieinfoservice.models.Movie;

import java.util.Arrays;
import java.util.List;

public class MovieData {

    private final List<Movie> movieList;

    public MovieData() {
        this.movieList = Arrays.asList(
                new Movie("1", "Fight Club", "Fight Club description"),
                new Movie("2", "American History X", "American History X description")
        );
    }

    public List<Movie> getMovieList() {
        return movieList;
    }
}
