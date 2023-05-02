package com.haero77.urlshortener.domain.url.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haero77.urlshortener.domain.url.entity.Url;
import com.haero77.urlshortener.domain.url.exception.ShortUrlInvalidStatusException;
import com.haero77.urlshortener.domain.url.exception.ShortUrlNotFoundException;
import com.haero77.urlshortener.domain.url.repository.UrlRepository;

@Service
@Transactional(readOnly = true)
public class UrlReader {

	private final UrlRepository urlRepository;

	public UrlReader(UrlRepository urlRepository) {
		this.urlRepository = urlRepository;
	}

	public String getOriginUrlIfValid(String shortenedUrl) {
		Url findUrl = findByShortenedUrl(shortenedUrl);

		if (!findUrl.hasValidStatus()) {
			throw new ShortUrlInvalidStatusException(
				"ShortUrl '%d' has invalid status=%s".formatted(findUrl.id(), findUrl.status()));
		}

		return findUrl.originUrl();
	}

	public Url findByShortenedUrl(String shortenedUrl) {
		return urlRepository.findUrlByShortenedUrl(shortenedUrl)
			.orElseThrow(() -> new ShortUrlNotFoundException(
				"Cannot find ShortUrl for shortenedUrl=%s".formatted(shortenedUrl)));
	}
}
