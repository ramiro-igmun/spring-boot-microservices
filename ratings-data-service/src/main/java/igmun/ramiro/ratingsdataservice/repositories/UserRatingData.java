package igmun.ramiro.ratingsdataservice.repositories;

import igmun.ramiro.ratingsdataservice.models.Rating;

import java.util.Arrays;
import java.util.List;

public class UserRatingData {

    private final List<Rating> ratings;

    public UserRatingData() {
        this.ratings = Arrays.asList(
                new Rating("1", "1", 5),
                new Rating("2", "1", 4)
        );
    }

    public List<Rating> getRatings() {
        return ratings;
    }
}
