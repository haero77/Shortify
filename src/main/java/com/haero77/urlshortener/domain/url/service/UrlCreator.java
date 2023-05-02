package com.haero77.urlshortener.domain.url.service;

import java.time.Period;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haero77.urlshortener.domain.url.dto.ShortUrlCreateRequest;
import com.haero77.urlshortener.domain.url.entity.Url;
import com.haero77.urlshortener.domain.url.repository.UrlRepository;
import com.haero77.urlshortener.domain.url.util.Base62Encoder;
import com.haero77.urlshortener.domain.url.util.TimeUtil;

@Service
@Transactional
public class UrlCreator {

	private final UrlRepository urlRepository;

	public UrlCreator(UrlRepository urlRepository) {
		this.urlRepository = urlRepository;
	}

	public Long create(ShortUrlCreateRequest request) {
		if (request.expirationExists()) {
			return create(request.url(), Url.DEFAULT_EXPIRATION_PERIOD);
		}
		return create(request.url(), Url.MAX_EXPIRATION_PERIOD);
	}

	private Long create(String originUrl, Period expirationPeriod) {
		Url url = Url.createWithoutShortenedUrl(
			originUrl,
			expirationPeriod,
			TimeUtil.getCurrentSeoulTime()
		);
		urlRepository.save(url);
		url.assignShortenedUrl(Base62Encoder.encode(url.id()));
		return url.id();
	}
}
