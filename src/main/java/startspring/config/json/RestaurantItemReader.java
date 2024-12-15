package startspring.config.json;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import startspring.dto.restaurant.RestaurantDto;

public class RestaurantItemReader implements ItemReader<RestaurantDto> {

    public RestaurantItemReader(Object o) {
    }

    @Bean
    @StepScope
    public org.springframework.batch.item.json.JsonItemReader<RestaurantDto> postItemReader(@Value("csv.csv-path") Resource inputFile) {
        JacksonJsonObjectReader<RestaurantDto> jsonObjectReader = new JacksonJsonObjectReader<>(RestaurantDto.class);
        return new JsonItemReaderBuilder<RestaurantDto>()
                .name("restaurntItemReader")
                .jsonObjectReader(jsonObjectReader)
                .resource(inputFile)
                .build();
    }

    @Override
    public RestaurantDto read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
