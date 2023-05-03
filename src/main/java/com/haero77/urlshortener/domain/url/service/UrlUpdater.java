package com.haero77.urlshortener.domain.url.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haero77.urlshortener.domain.url.entity.Url;
import com.haero77.urlshortener.domain.url.util.TimeUtil;

@Service
@Transactional
public class UrlUpdater {

	private final UrlReader urlReader;

	public UrlUpdater(UrlReader urlReader) {
		this.urlReader = urlReader;
	}

	public void updateExpirationDate(Long urlId, boolean hasExpirationOption) {
		Url findUrl = urlReader.findById(urlId);

		if (hasExpirationOption) {
			findUrl.updateExpirationDate(TimeUtil.getCurrentSeoulTime(), Url.MAX_EXPIRATION_PERIOD);
			return;
		}
		findUrl.updateExpirationDate(TimeUtil.getCurrentSeoulTime(), Url.DEFAULT_EXPIRATION_PERIOD);
	}
}
