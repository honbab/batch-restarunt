package startspring.config.csv;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;
import startspring.dto.restaurant.RestaurantDto;
import startspring.entity.restaurant.Restaurant;
import startspring.repository.shop.RestaurantRepository;


@Configuration
@RequiredArgsConstructor
public class JsonReaderJobConfig {

    private final JobRepository jobRepository;
    private final EntityManagerFactory entityManagerFactory;
    private final RestaurantRepository restaurantRepository; // Repository 주입
    private final PlatformTransactionManager transactionManager; // 트랜잭션 관리

    private static final int chunkSize = 1000; //데이터 처리할 row size

    @Bean
    public Step stepForJson() throws Exception {
        return new StepBuilder("stepForJson", jobRepository)
                .<RestaurantDto, Restaurant>chunk(10, transactionManager) // Chunk 사이즈 및 트랜잭션 설정
                .reader(new RestaurantItemReader("src/main/resources/data/store_info_seoul.json"))
                .processor(new RestaurantProcessor())
                .writer(new JdbcRestaurantBatchItemWriter(restaurantRepository))
                .transactionManager(transactionManager)
                .build();
    }

    @Bean
    public Job jsonToDbJob() throws Exception {
        return new JobBuilder("jsonToDbJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(stepForJson())
                .build();
    }

    @Bean
    public FlatFileItemReader<RestaurantDto> csvScheduleReader() {
        return new FlatFileItemReaderBuilder<RestaurantDto>()
                .name("csvScheduleReader")
                .resource(new ClassPathResource("/data/store_info_seoul_eng.csv"))
                .delimited()
                .delimiter(",")
                .names("name", "category", "city", "district", "parcelAddress", "roadAddress", "roadPostal", "districtPostal", "latitude", "longitude", "xCoordinate", "yCoordinate") // Expected 11 fields
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(RestaurantDto.class);
                }})
                .strict(false)
                .build();
    }


    @Bean
    public ItemProcessor<RestaurantDto, Restaurant> processor() {
        return restaurantDto -> {
            Restaurant restaurant = new Restaurant();
            restaurant.setName(restaurantDto.getName());
            restaurant.setCategory(restaurantDto.getCategory());
            restaurant.setCity(restaurantDto.getCity());
            restaurant.setDistrict(restaurantDto.getDistrict());
            restaurant.setParcelAddress(restaurantDto.getParcelAddress());
            restaurant.setRoadAddress(restaurantDto.getRoadAddress());
            restaurant.setLatitude(restaurantDto.getLatitude());
            restaurant.setLongitude(restaurantDto.getLongitude());
            restaurant.setXCoordinate(String.valueOf(restaurantDto.getXCoordinate()));
            restaurant.setYCoordinate(String.valueOf(restaurantDto.getYCoordinate()));
            return restaurant;
        };
    }

    @Bean
    public ItemWriter<Restaurant> writer() {
        return restaurantRepository::saveAll; // 데이터베이스에 저장
    }

//    @Bean
//    public Step csvScheduleReaderStep(){
//        return new StepBuilder("csvScheduleReaderStep", jobRepository)
//                .<RestaurantDto, RestaurantDto>chunk(chunkSize)
//                .reader(csvReader.csvScheduleReader()) //추가
//                .writer(csvRestaurantWriter) //추가
////                .allowStartIfComplete(true)
//                .build();
//    }
}
