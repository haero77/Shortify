package com.haero77.urlshortener.domain.url.dto;

public record ShortUrlCreateRequest(
	String url,
	boolean expiration
) {

	public boolean expirationExists() {
		return this.expiration;
	}
}
