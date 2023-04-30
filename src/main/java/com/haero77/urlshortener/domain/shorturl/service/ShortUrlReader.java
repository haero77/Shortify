package com.haero77.urlshortener.domain.shorturl.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haero77.urlshortener.domain.shorturl.entity.ShortUrl;
import com.haero77.urlshortener.domain.shorturl.exception.ShortUrlNotFoundException;
import com.haero77.urlshortener.domain.shorturl.repository.ShortUrlRepository;

@Service
@Transactional(readOnly = true)
public class ShortUrlReader {

	private final ShortUrlRepository shortUrlRepository;

	public ShortUrlReader(ShortUrlRepository shortUrlRepository) {
		this.shortUrlRepository = shortUrlRepository;
	}

	public String getOriginUrl(String shortenedUrl) {
		ShortUrl findShortUrl = findByShortenedUrl(shortenedUrl);
		return findShortUrl.originUrl();
	}

	public ShortUrl findByShortenedUrl(String shortenedUrl) {
		return shortUrlRepository.findShortUrlByShortenedUrl(shortenedUrl)
			.orElseThrow(() -> new ShortUrlNotFoundException(
				"Cannot find ShortUrl for shortenedUrl=%s".formatted(shortenedUrl)));
	}
}
