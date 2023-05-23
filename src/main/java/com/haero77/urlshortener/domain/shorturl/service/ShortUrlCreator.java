package com.haero77.urlshortener.domain.shorturl.service;

import java.time.Period;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haero77.urlshortener.domain.shorturl.dto.ShortUrlCreateRequest;
import com.haero77.urlshortener.domain.shorturl.entity.ShortUrl;
import com.haero77.urlshortener.domain.shorturl.repository.ShortUrlRepository;
import com.haero77.urlshortener.domain.shorturl.util.Base62Encoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShortUrlCreator {

	private final ShortUrlRepository shortUrlRepository;

	public ShortUrlCreator(ShortUrlRepository shortUrlRepository) {
		this.shortUrlRepository = shortUrlRepository;
	}

	public Long create1(ShortUrlCreateRequest request) {
		if (request.expirationExists()) {
			return create2(request.url(), ShortUrl.DEFAULT_EXPIRATION_PERIOD);
		}
		return create2(request.url(), ShortUrl.MAX_EXPIRATION_PERIOD);
	}

	@Transactional
	public Long create2(String originUrl, Period expirationPeriod) {
		ShortUrl shortUrl = ShortUrl.createWithoutShortenedUrl(originUrl, expirationPeriod);
		shortUrlRepository.save(shortUrl);
		shortUrl.assignShortenedUrl(Base62Encoder.encode(shortUrl.id())); // 변경 감지로 update 문이 나갈 것으로 기대
		return shortUrl.id();
	}
}
