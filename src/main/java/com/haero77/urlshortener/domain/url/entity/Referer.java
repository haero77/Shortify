package com.haero77.urlshortener.domain.url.entity;

public enum Referer {

	DIRECT,
	GOOGLE,
	OTHERS;

	public static Referer parseReferer(String referer) {
		if (referer == null) {
			return DIRECT;
		}
		if (referer.contains("google")) {
			return GOOGLE;
		}
		return OTHERS;
	}
}
