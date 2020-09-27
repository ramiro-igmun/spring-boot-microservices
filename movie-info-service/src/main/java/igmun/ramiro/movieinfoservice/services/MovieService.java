package igmun.ramiro.movieinfoservice.services;

import igmun.ramiro.movieinfoservice.models.Movie;
import igmun.ramiro.movieinfoservice.repositories.MovieData;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MovieService {

    private final MovieData movieData;

    public MovieService() {
        this.movieData = new MovieData();
    }

    public Movie getDetail(String movieId) {
        return movieData.getMovieList().stream()
                .filter(movie -> movie.getMovieId().equals(movieId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
    }
}
