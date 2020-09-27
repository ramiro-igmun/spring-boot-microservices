package igmun.ramiro.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import igmun.ramiro.moviecatalogservice.models.Movie;
import igmun.ramiro.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoService {

    @Value("${movie-info-service}")
    private String MOVIE_INFO_SERVICE_API;

    private final RestTemplate restTemplate;

    public MovieInfoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getFallBackMovieInfo",
            threadPoolKey = "movieInfoPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "20"), // Limit the movie info service thread pool to x threads
                    @HystrixProperty(name = "maxQueueSize", value = "10") // x maximum request can wait to enter the thread pool
            },
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"), // Timeout for the rest call
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), // Look at the last x calls to compute the circuit break
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"), // If 50% of the last x calls fail, break circuit
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000") // Wait this time to try to make the 'real' call again
            })
    public Movie getMovieInfo(Rating rating) {
        return restTemplate.getForObject(String.format("%s/movies/%s", MOVIE_INFO_SERVICE_API, rating.getMovieId()), Movie.class);
    }

    public Movie getFallBackMovieInfo(Rating rating) {
        return new Movie(rating.getMovieId(), "Service Down", "Movie Info Service Not Responding");
    }
}
