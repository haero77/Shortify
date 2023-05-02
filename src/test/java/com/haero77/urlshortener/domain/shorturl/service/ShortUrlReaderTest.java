package com.haero77.urlshortener.domain.shorturl.service;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Period;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.haero77.urlshortener.config.TestConfig;
import com.haero77.urlshortener.domain.shorturl.entity.ShortUrl;
import com.haero77.urlshortener.domain.shorturl.exception.ShortUrlInvalidStatusException;
import com.haero77.urlshortener.domain.shorturl.repository.ShortUrlRepository;
import com.haero77.urlshortener.domain.shorturl.util.Base62Encoder;

@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
@DataJpaTest
class ShortUrlReaderTest {

	@Autowired
	private ShortUrlRepository shortUrlRepository;

	@Autowired
	private ShortUrlReader shortUrlReader;

	@Test
	@DisplayName("만료된 url에 대해 origin url 조회 시 예외가 발생한다.")
	void should_throw_exception_when_query_url_which_has_been_expired() {
		// given
		String originUrl = "https://github.com/haero77";
		LocalDateTime currentDateTime = LocalDateTime.now().minusDays(10);
		ShortUrl savedShortUrl = shortUrlRepository.save(
			ShortUrl.createWithoutShortenedUrl(originUrl, Period.ofDays(1), currentDateTime)
		);
		String shortenedUrl = Base62Encoder.encode(savedShortUrl.id());
		savedShortUrl.assignShortenedUrl(shortenedUrl);

		// when & then
		assertThatThrownBy(() -> {
			shortUrlReader.getOriginUrlIfValid(shortenedUrl);
		}).isInstanceOf(ShortUrlInvalidStatusException.class);
	}
}