package com.haero77.urlshortener.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.haero77.urlshortener.domain.shorturl.repository.ShortUrlRepository;
import com.haero77.urlshortener.domain.shorturl.service.ShortUrlCreator;
import com.haero77.urlshortener.domain.shorturl.service.ShortUrlReader;

@TestConfiguration
public class TestConfig {

	@Bean
	public ShortUrlCreator shortUrlCreator(
		ShortUrlRepository shortUrlRepository
	) {
		return new ShortUrlCreator(shortUrlRepository);
	}

	@Bean
	public ShortUrlReader shortUrlReader(
		ShortUrlRepository shortUrlRepository
	) {
		return new ShortUrlReader(shortUrlRepository);
	}
}
