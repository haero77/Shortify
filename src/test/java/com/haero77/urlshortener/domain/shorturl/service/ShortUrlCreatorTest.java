
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
	@DisplayName("ShortUrl을 생성하고 나면 상태는 ACTIVE이다.")
	void create() {
		// given
		ShortUrlCreateRequest createRequest =
			new ShortUrlCreateRequest("https://github.com/haero77", false);

		Long savedId = shortUrlCreator.create(createRequest);

		// when
		ShortUrl findShortUrl = shortUrlRepository.findById(savedId).get();

		// then
		assertThat(findShortUrl.status()).isSameAs(ShortUrlStatus.ACTIVE);
	}
}