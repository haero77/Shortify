package com.haero77.urlshortener.domain.url.service;

import org.springframework.stereotype.Service;

import com.haero77.urlshortener.domain.url.entity.Referer;
import com.haero77.urlshortener.domain.url.entity.Url;

@Service
public class UrlCaller {

	private final UrlReader urlReader;
	private final UrlCallCreator urlCallCreator;

	public UrlCaller(UrlReader urlReader, UrlCallCreator urlCallCreator) {
		this.urlReader = urlReader;
		this.urlCallCreator = urlCallCreator;
	}

	public String getOriginUrlAndRecordCall(Referer referer, String shortenedUrl) {
		Url validUrl = urlReader.getUrlIfValid(shortenedUrl);
		urlCallCreator.create(validUrl, referer);
		return validUrl.originUrl();
	}
}
