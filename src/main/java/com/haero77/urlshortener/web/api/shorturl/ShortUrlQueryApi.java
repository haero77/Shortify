package com.haero77.urlshortener.web.api.shorturl;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.haero77.urlshortener.domain.shorturl.service.ShortUrlReader;

@RestController
public class ShortUrlQueryApi {

	private final ShortUrlReader shortUrlReader;

	public ShortUrlQueryApi(ShortUrlReader shortUrlReader) {
		this.shortUrlReader = shortUrlReader;
	}

	@GetMapping("/{shortenedUrl}")
	public ResponseEntity<?> query(
		@PathVariable String shortenedUrl
	) {
		// TODO: +가 있는 경우 통계 return
		return redirect(shortenedUrl);
	}

	private ResponseEntity<HttpHeaders> redirect(String shortenedUrl) {
		String originUrl = shortUrlReader.getOriginUrl(shortenedUrl);

		HttpHeaders headers = new HttpHeaders();
		URI redirectLocation = UriComponentsBuilder.fromHttpUrl(originUrl)
			.build()
			.toUri();
		headers.setLocation(redirectLocation);

		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}
}
