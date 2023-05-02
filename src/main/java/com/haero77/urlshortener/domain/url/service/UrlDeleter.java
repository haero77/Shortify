package com.haero77.urlshortener.domain.url.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haero77.urlshortener.domain.url.entity.Url;

@Service
@Transactional
public class UrlDeleter {

	private final UrlReader urlReader;

	public UrlDeleter(UrlReader urlReader) {
		this.urlReader = urlReader;
	}

	public void delete(Long urlId) {
		Url findUrl = urlReader.findById(urlId);
		findUrl.delete();
	}
}
