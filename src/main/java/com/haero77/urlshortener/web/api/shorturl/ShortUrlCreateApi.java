package com.haero77.urlshortener.web.api.shorturl;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.haero77.urlshortener.domain.shorturl.dto.ShortUrlCreateRequest;
import com.haero77.urlshortener.domain.shorturl.service.ShortUrlCreator;

@RestController
public class ShortUrlCreateApi {

	private final ShortUrlCreator shortUrlCreator;

	public ShortUrlCreateApi(ShortUrlCreator shortUrlCreator) {
		this.shortUrlCreator = shortUrlCreator;
	}

	@PostMapping("/api/urls")
	public ResponseEntity<Void> create(
		@RequestBody ShortUrlCreateRequest request
	) {
		Long shortUrlId = shortUrlCreator.create(request);

		URI location = UriComponentsBuilder.fromPath("/api/urls/{shortUrlId}")
			.buildAndExpand(shortUrlId)
			.toUri();

		return ResponseEntity.created(location)
			.build();
	}
}
