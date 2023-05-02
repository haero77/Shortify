package com.haero77.urlshortener.web.api.url;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.haero77.urlshortener.domain.url.dto.UrlCreateRequest;
import com.haero77.urlshortener.domain.url.dto.UrlCreateResponse;
import com.haero77.urlshortener.domain.url.service.UrlCreator;
import com.haero77.urlshortener.web.util.ServerAddressParser;

@RestController
public class UrlCreateApi {

	private final UrlCreator urlCreator;

	public UrlCreateApi(UrlCreator urlCreator) {
		this.urlCreator = urlCreator;
	}

	@PostMapping("/api/urls")
	public ResponseEntity<UrlCreateResponse> create(
		HttpServletRequest servletRequest,
		@RequestBody UrlCreateRequest urlCreateRequest
	) {
		String serverAddress = ServerAddressParser.parseServerAddress(servletRequest);
		UrlCreateResponse urlCreateResponse = urlCreator.create(serverAddress, urlCreateRequest);

		URI location = UriComponentsBuilder.fromPath("/api/urls/{shortUrlId}")
			.buildAndExpand(urlCreateResponse.id())
			.toUri();

		return ResponseEntity.created(location)
			.body(urlCreateResponse);
	}
}
