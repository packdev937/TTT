package dev.pack.ttt.tistory.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("tistory")
public record TistoryConfigProperties(String apiUrl, String blogName) {

}
