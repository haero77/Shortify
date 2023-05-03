package com.haero77.urlshortener.domain.url.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haero77.urlshortener.domain.url.entity.Url;
import com.haero77.urlshortener.domain.url.exception.UrlInvalidStatusException;
import com.haero77.urlshortener.domain.url.exception.UrlNotFoundException;
import com.haero77.urlshortener.domain.url.repository.UrlRepository;

@Service
@Transactional(readOnly = true)
public class UrlReader {

	private final UrlRepository urlRepository;

	public UrlReader(UrlRepository urlRepository) {
		this.urlRepository = urlRepository;
	}

	public Url getUrlIfValid(String shortenedUrl) {
		Url findUrl = findByShortenedUrl(shortenedUrl);

		if (!findUrl.hasValidStatus()) {
			throw new UrlInvalidStatusException(
				"Url '%d' has invalid status=%s".formatted(findUrl.id(), findUrl.status()));
		}

		return findUrl;
	}

	public Url findById(Long urlId) {
		return urlRepository.findById(urlId)
			.orElseThrow(() -> new UrlNotFoundException(
				"Cannot find Url for id=%d".formatted(urlId)));
	}

	public Url findByShortenedUrl(String shortenedUrl) {
		return urlRepository.findUrlByShortenedUrl(shortenedUrl)
			.orElseThrow(() -> new UrlNotFoundException(
				"Cannot find Url for shortenedUrl=%s".formatted(shortenedUrl)));
	}

	public Url findByOriginUrl(String originUrl) {
		return urlRepository.findUrlByOriginUrl(originUrl)
			.orElseThrow(() -> new UrlNotFoundException(
				"Cannot find Url for originUrl=%s".formatted(originUrl)));
	}

	public boolean existsByOriginUrl(String originUrl) {
		return urlRepository.existsByOriginUrl(originUrl);
	}
}
