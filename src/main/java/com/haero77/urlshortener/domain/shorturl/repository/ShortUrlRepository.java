package com.haero77.urlshortener.domain.shorturl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haero77.urlshortener.domain.shorturl.entity.ShortUrl;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

	Optional<ShortUrl> findShortUrlByShortenedUrl(String shortenedUrl);
}
