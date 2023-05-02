package com.haero77.urlshortener.web.api.url;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.haero77.urlshortener.domain.url.dto.ShortUrlCreateRequest;
import com.haero77.urlshortener.domain.url.service.UrlCreator;

@RestController
public class UrlCreateApi {

	private final UrlCreator urlCreator;

	public UrlCreateApi(UrlCreator urlCreator) {
		this.urlCreator = urlCreator;
	}

	@PostMapping("/api/urls")
	public ResponseEntity<Void> create(
		@RequestBody ShortUrlCreateRequest request
	) {
		Long shortUrlId = urlCreator.create(request);

		URI location = UriComponentsBuilder.fromPath("/api/urls/{shortUrlId}")
			.buildAndExpand(shortUrlId)
			.toUri();

		return ResponseEntity.created(location)
			.build();
	}
}
