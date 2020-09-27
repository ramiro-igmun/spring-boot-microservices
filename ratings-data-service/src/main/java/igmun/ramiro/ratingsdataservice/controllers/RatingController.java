package igmun.ramiro.ratingsdataservice.controllers;

import igmun.ramiro.ratingsdataservice.models.UserRatings;
import igmun.ramiro.ratingsdataservice.services.RatingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/users/{userId}")
    public UserRatings getUserRatings(@PathVariable String userId) {
       return ratingService.getUserRatings(userId);
    }
}
