package startspring.config.csv;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import startspring.dto.restaurant.RestaurantDto;


import java.io.File;
import java.util.List;

@Slf4j
public class RestaurantItemReader implements ItemReader<RestaurantDto> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<RestaurantDto> restaurantList;
    private int nextRestaurantIndex;

    public RestaurantItemReader(String filePath) throws Exception {
        File jsonFile = new File(filePath);
        restaurantList = objectMapper.readValue(jsonFile, new TypeReference<List<RestaurantDto>>(){});
        // DTO 리스트를 로깅하여 확인
        for (RestaurantDto restaurant : restaurantList) {
            System.out.println("ayw" + restaurant);
        }
        nextRestaurantIndex = 0;
    }

    @Override
    public RestaurantDto read() throws Exception {
        RestaurantDto nextRestaurant = null;
        if (nextRestaurantIndex < restaurantList.size()) {
            nextRestaurant = restaurantList.get(nextRestaurantIndex);
            nextRestaurantIndex++;
        }
        return nextRestaurant;
    }
}
