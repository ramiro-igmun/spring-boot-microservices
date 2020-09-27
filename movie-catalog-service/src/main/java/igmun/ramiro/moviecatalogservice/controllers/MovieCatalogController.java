package igmun.ramiro.moviecatalogservice.controllers;

import igmun.ramiro.moviecatalogservice.models.CatalogItem;
import igmun.ramiro.moviecatalogservice.models.Movie;
import igmun.ramiro.moviecatalogservice.models.UserRatings;
import igmun.ramiro.moviecatalogservice.services.MovieInfoService;
import igmun.ramiro.moviecatalogservice.services.RatingDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    private final MovieInfoService movieInfoService;
    private final RatingDataService ratingDataService;

    public MovieCatalogController(MovieInfoService movieInfoService, RatingDataService ratingDataService) {
        this.movieInfoService = movieInfoService;
        this.ratingDataService = ratingDataService;
    }

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {

        UserRatings userRatings = ratingDataService.getUserRatings(userId);

        return userRatings.getRatings().stream().map(rating -> {
            Movie movie = movieInfoService.getMovieInfo(rating);
            return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
        }).collect(Collectors.toList());
    }
}
