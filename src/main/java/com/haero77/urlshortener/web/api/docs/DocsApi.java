package com.haero77.urlshortener.web.api.docs;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocsApi {

	@GetMapping("/docs/api")
	public ResponseEntity<String> docs() {
		URI docsPath = URI.create("/docs/index.html");

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(docsPath);

		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}
}
