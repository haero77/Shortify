package com.haero77.urlshortener.domain.shorturl.dto;

public record ShortUrlCreateRequest(
	String url,
	boolean expiration
) {

	public boolean expirationExists() {
		return this.expiration;
	}
}
