package com.haero77.urlshortener.domain.shorturl.service;

import java.time.Period;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haero77.urlshortener.domain.shorturl.dto.ShortUrlCreateRequest;
import com.haero77.urlshortener.domain.shorturl.entity.ShortUrl;
import com.haero77.urlshortener.domain.shorturl.repository.ShortUrlRepository;
import com.haero77.urlshortener.domain.shorturl.util.Base62Encoder;

@Service
@Transactional
public class ShortUrlCreator {

	private final ShortUrlRepository shortUrlRepository;

	public ShortUrlCreator(ShortUrlRepository shortUrlRepository) {
		this.shortUrlRepository = shortUrlRepository;
	}

	public Long create(ShortUrlCreateRequest request) {
		if (request.expirationExists()) {
			return create(request.url(), ShortUrl.DEFAULT_EXPIRATION_PERIOD);
		}
		return create(request.url(), ShortUrl.MAX_EXPIRATION_PERIOD);
	}

	private Long create(String originUrl, Period expirationPeriod) {
		ShortUrl shortUrl = ShortUrl.createWithoutShortenedUrl(originUrl, expirationPeriod);
		shortUrlRepository.save(shortUrl);
		shortUrl.assignShortenedUrl(Base62Encoder.encode(shortUrl.id()));
		return shortUrl.id();
	}
}
