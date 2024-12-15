package startspring.config.csv;

import org.springframework.batch.item.ItemProcessor;
import startspring.dto.restaurant.RestaurantDto;
import startspring.entity.restaurant.Restaurant;


public class RestaurantProcessor implements ItemProcessor<RestaurantDto, Restaurant> {
    @Override
    public Restaurant process(RestaurantDto restaurantDto) throws Exception {
        Restaurant restaurant = new Restaurant();
            restaurant.setName(restaurantDto.getName());
            restaurant.setCategory(restaurantDto.getCategory());
            restaurant.setCity(restaurantDto.getCity());
            restaurant.setDistrict(restaurantDto.getDistrict());
            restaurant.setDistrictPostal(restaurantDto.getDistrictPostal());
            restaurant.setRoadAddress(restaurantDto.getRoadAddress());
            restaurant.setParcelAddress(restaurantDto.getParcelAddress());
            restaurant.setLatitude(restaurantDto.getLatitude());
            restaurant.setLongitude(restaurantDto.getLongitude());
            restaurant.setXCoordinate(String.valueOf(restaurantDto.getXCoordinate()));
            restaurant.setYCoordinate(String.valueOf(restaurantDto.getYCoordinate()));
            return restaurant;
    }
}
