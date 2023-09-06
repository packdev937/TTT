package dev.pack.ttt.tistory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TistoryConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
