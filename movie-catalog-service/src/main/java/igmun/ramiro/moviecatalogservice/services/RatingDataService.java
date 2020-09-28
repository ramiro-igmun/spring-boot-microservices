package igmun.ramiro.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import igmun.ramiro.moviecatalogservice.models.UserRatings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class RatingDataService {

    @Value("${ratings-data-service}")
    private String RATINGS_DATA_SERVICE_API;

    private final RestTemplate restTemplate;

    public RatingDataService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getFallBackUserRatings",
                    threadPoolKey = "ratingsDataPool",
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
    public UserRatings getUserRatings(String userId) {
        return restTemplate.getForObject(String.format("%s/ratings/users/%s", RATINGS_DATA_SERVICE_API, userId), UserRatings.class);
    }

    public UserRatings getFallBackUserRatings(String userId) {
        return new UserRatings();
    }
}
