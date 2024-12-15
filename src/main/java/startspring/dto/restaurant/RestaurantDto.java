package startspring.dto.restaurant;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import startspring.entity.restaurant.Restaurant;

import java.math.BigDecimal;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {
    private String name;
    private String category;
    private String city;
    private String district;
    private String parcelAddress;
    private String roadAddress;
    private String districtPostal;
    private String latitude;
    private String longitude;
    @JsonProperty("xCoordinate")
    private BigDecimal xCoordinate;
    @JsonProperty("yCoordinate")
    private BigDecimal yCoordinate;

    /**
     * Schedule 엔티티 반환
     * @return
     */
    public Restaurant toEntity(){
        return Restaurant.builder()
                .name(this.name)
                .category(this.category)
                .city(this.city)
                .district(this.district)
                .parcelAddress(this.parcelAddress)
                .roadAddress(this.roadAddress)
                .districtPostal(this.districtPostal)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .xCoordinate(String.valueOf(this.xCoordinate))
                .yCoordinate(String.valueOf(this.yCoordinate))
                .build();

    }

}
