package igmun.ramiro.ratingsdataservice.models;

public class Rating {

    private String movieId;
    private String userId;
    private int rating;

    public Rating() {
    }

    public Rating(String movieId, String userId, int rating) {
        this.movieId = movieId;
        this.userId = userId;
        this.rating = rating;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
