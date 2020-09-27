package igmun.ramiro.ratingsdataservice.services;

import igmun.ramiro.ratingsdataservice.models.Rating;
import igmun.ramiro.ratingsdataservice.models.UserRatings;
import igmun.ramiro.ratingsdataservice.repositories.UserRatingData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {

    private final UserRatingData userRatingData = new UserRatingData();

    public UserRatings getUserRatings(String userId) {
        List<Rating> ratings = userRatingData.getRatings().stream()
                .filter(rating -> rating.getUserId().equals(userId))
                .collect(Collectors.toList());
        UserRatings userRatings = new UserRatings();
        userRatings.setRatings(ratings);
        return userRatings;
    }
}
