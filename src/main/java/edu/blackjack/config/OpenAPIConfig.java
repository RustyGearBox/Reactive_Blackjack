package edu.blackjack.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    
    @Bean
    public OpenAPI blackjackOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Blackjack API")
                .description("Spring reactive API for playing Blackjack")
                .version("1.0")
                .license(new License().name("MIT").url("https://opensource.org/licenses/MIT")));
    }
}