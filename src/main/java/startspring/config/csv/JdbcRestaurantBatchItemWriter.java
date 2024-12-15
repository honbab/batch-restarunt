package startspring.config.csv;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import startspring.entity.restaurant.Restaurant;
import startspring.repository.shop.RestaurantRepository;

@Component
public class JdbcRestaurantBatchItemWriter implements ItemWriter<Restaurant> {
    private final RestaurantRepository restaurantRepository;

    public JdbcRestaurantBatchItemWriter(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void write(Chunk<? extends Restaurant> chunk) throws Exception {
        restaurantRepository.saveAll(chunk);

    }
}
