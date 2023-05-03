package com.haero77.urlshortener.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.haero77.urlshortener.domain.url.repository.UrlCallRepository;
import com.haero77.urlshortener.domain.url.repository.UrlRepository;
import com.haero77.urlshortener.domain.url.service.UrlCreator;
import com.haero77.urlshortener.domain.url.service.UrlReader;
import com.haero77.urlshortener.domain.url.service.UrlStatisticsProvider;
import com.haero77.urlshortener.domain.url.service.UrlUpdater;

@TestConfiguration
public class TestConfig {

	@Bean
	public UrlCreator urlCreator(
		UrlRepository urlRepository,
		UrlReader urlReader,
		UrlUpdater urlUpdater
	) {
		return new UrlCreator(urlRepository, urlReader, urlUpdater);
	}

	@Bean
	public UrlReader urlReader(
		UrlRepository urlRepository
	) {
		return new UrlReader(urlRepository);
	}

	@Bean
	public UrlUpdater urlUpdater(
		UrlReader urlReader
	) {
		return new UrlUpdater(urlReader);
	}

	@Bean
	public UrlStatisticsProvider urlStatisticsProvider(
		UrlCallRepository urlCallRepository,
		UrlReader urlReader
	) {
		return new UrlStatisticsProvider(urlCallRepository, urlReader);
	}
}
