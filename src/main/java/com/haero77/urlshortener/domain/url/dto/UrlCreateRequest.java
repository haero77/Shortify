package com.haero77.urlshortener.domain.url.dto;

public record UrlCreateRequest(
	String url,
	boolean expiration
) {

	public boolean hasExpirationOption() {
		return this.expiration;
	}
}
