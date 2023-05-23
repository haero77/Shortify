package com.haero77.urlshortener.domain.shorturl.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.haero77.urlshortener.domain.shorturl.dto.ShortUrlCreateRequest;
import com.haero77.urlshortener.domain.shorturl.entity.ShortUrl;
import com.haero77.urlshortener.domain.shorturl.entity.ShortUrlStatus;
import com.haero77.urlshortener.domain.shorturl.repository.ShortUrlRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class ShortUrlCreatorTest {

	@Autowired
	private ShortUrlRepository shortUrlRepository;

	@Autowired
	private ShortUrlCreator shortUrlCreator;

	@AfterEach
	void tearDown() {
		shortUrlRepository.deleteAll();
	}

	@Test
	@DisplayName("ShortUrl이 생성된 뒤 상태는 ACTIVE이다.")
	void create() {
		// given
		ShortUrlCreateRequest createRequest =
			new ShortUrlCreateRequest("https://github.com/haero77", false);

		// when
		Long savedId = shortUrlCreator.create1(createRequest);

		// then
		ShortUrl findShortUrl = shortUrlRepository.findById(savedId).get();

		ShortUrlStatus expected = ShortUrlStatus.ACTIVE;
		ShortUrlStatus actual = findShortUrl.status();

		assertThat(actual).isSameAs(expected);
	}

	@Test
	@DisplayName("ShortUrl이 생성된 뒤 단축된 URL이 존재한다.")
	void testMethodNameHere() {
		// given
		ShortUrlCreateRequest createRequest =
			new ShortUrlCreateRequest("https://github.com/haero77", false);

		// when
		Long savedId = shortUrlCreator.create1(createRequest);

		// then
		ShortUrl findShortUrl = shortUrlRepository.findById(savedId).get();

		String actual = findShortUrl.shortenedUrl();

		assertThat(actual).isNotNull();
	}
}