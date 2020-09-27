package igmun.ramiro.moviecatalogservice.models;

import java.util.ArrayList;
import java.util.List;

public class UserRatings {

    private List<Rating> ratings;

    public UserRatings() {
        ratings = new ArrayList<>();
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
