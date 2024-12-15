package startspring.config;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi boardGroupedOpenApi() {
        return GroupedOpenApi
                .builder()
                .group("Honbab API")
                .pathsToMatch()
                .addOpenApiCustomizer(
                        openApi ->
                                openApi
                                        .setInfo(
                                                new Info()
                                                        .title("Honbab API") // API 제목
                                                        .description("Honbab API") // API 설명
                                                        .version("1.0.0") // API 버전
                                        )
                )
                .build();
    }
}