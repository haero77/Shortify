package com.haero77.urlshortener.web.util;

import javax.servlet.http.HttpServletRequest;

import com.haero77.urlshortener.domain.url.entity.Referer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RefererParser {

	private static final String REFERER = "Referer";

	private RefererParser() {
	}

	public static Referer parseReferer(HttpServletRequest request) {
		String referer = request.getHeader(REFERER);
		log.info("referer={}", referer);
		return Referer.parseReferer(referer);
	}
}
