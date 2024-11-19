package com.myapp.playground.infrastructure.google.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.http.codec.LoggingCodecSupport;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
public class GoogleConfig {

    private static final String GOOGLE_OAUTH_BASE_URL = "https://oauth2.googleapis.com";
    private static final String GOOGLE_API_BASE_URL = "https://www.googleapis.com";

    @Bean
    public WebClient oauthWebClient() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(new SnakeCaseStrategy());

        ExchangeStrategies strategies = ExchangeStrategies.builder()
            .codecs(configurer -> {
                configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON));
                configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON));
            }).build();

        strategies.messageWriters().stream()
            .filter(LoggingCodecSupport.class::isInstance)
            .forEach(writer -> ((LoggingCodecSupport) writer).setEnableLoggingRequestDetails(true));

        return WebClient.builder()
            .baseUrl(GOOGLE_OAUTH_BASE_URL)
            .exchangeStrategies(strategies)
            .build();
    }

    @Bean
    public WebClient calendarWebClient() {
        ExchangeStrategies strategies = ExchangeStrategies.builder()
            .codecs(ClientCodecConfigurer::defaultCodecs)
            .build();

        strategies.messageWriters().stream()
            .filter(LoggingCodecSupport.class::isInstance)
            .forEach(writer -> ((LoggingCodecSupport) writer).setEnableLoggingRequestDetails(true));

        return WebClient.builder()
            .baseUrl(GOOGLE_API_BASE_URL)
            .exchangeStrategies(strategies)
            .build();
    }

}
