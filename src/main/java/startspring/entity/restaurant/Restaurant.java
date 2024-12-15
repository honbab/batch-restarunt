package startspring.entity.restaurant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Entity
@NoArgsConstructor
@Data
@ToString
public class Restaurant {
    @Id
    @Column
    private String name;

    @Column
    private String category;

    @Column
    private String city;

    @Column
    private String district;

    @Column
    private String parcelAddress;

    @Column
    private String roadAddress;

    @Column
    private String districtPostal;

    @Column
    private String latitude;

    @Column
    private String longitude;

    @Column

    private String xCoordinate;

    @Column
    private String yCoordinate;

    public Restaurant(String name, String category, String city, String district, String parcelAddress, String roadAddress, String districtPostal, String latitude, String longitude, String xCoordinate, String yCoordinate) {
        this.name = name;
        this.category = category;
        this.city = city;
        this.district = district;
        this.parcelAddress = parcelAddress;
        this.roadAddress = roadAddress;
        this.districtPostal = districtPostal;
        this.latitude = latitude;
        this.longitude = longitude;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    // 정적 팩토리 메서드
//    public static Restaurant of(String name,
//                                String category,
//                                String city,
//                                String district,
//                                String parcelAddress,
//                                String roadAddress,
//                                String roadPostal,
//                                String districtPostal,
//                                String latitude,
//                                String longitude,
//                                String xCoordinate,
//                                String yCoordinate) {
//        return new Restaurant(name, category, city, district, parcelAddress, roadAddress, roadPostal, districtPostal, latitude, longitude, xCoordinate, yCoordinate);
//
//    }
}


