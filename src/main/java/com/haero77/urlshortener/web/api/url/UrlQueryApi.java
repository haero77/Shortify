package com.haero77.urlshortener.web.api.url;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.haero77.urlshortener.domain.url.service.UrlCaller;
import com.haero77.urlshortener.domain.url.service.UrlReader;
import com.haero77.urlshortener.web.util.RefererParser;

@RestController
public class UrlQueryApi {

	private final UrlCaller urlCaller;

	public UrlQueryApi(UrlReader urlReader, UrlCaller urlCaller) {
		this.urlCaller = urlCaller;
	}

	@GetMapping("/{shortenedUrl}")
	public ResponseEntity<?> query(
		HttpServletRequest request,
		@PathVariable String shortenedUrl
	) {
		// TODO: +가 있는 경우 통계 return
		// if (lastCharacter(shortenedUrl).equals('+')) {
		// 	return getStatistics(shortenedUrl);
		// }
		return redirect(request, shortenedUrl);
	}

	private ResponseEntity<HttpHeaders> redirect(HttpServletRequest request, String shortenedUrl) {
		String originUrl = urlCaller.getOriginUrlAndRecordCall(RefererParser.parseReferer(request), shortenedUrl);

		HttpHeaders headers = new HttpHeaders();
		URI redirectLocation = UriComponentsBuilder.fromHttpUrl(originUrl)
			.build()
			.toUri();
		headers.setLocation(redirectLocation);

		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}
}
