package com.haero77.urlshortener.domain.url.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haero77.urlshortener.domain.url.entity.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {

	Optional<Url> findUrlByShortenedUrl(String shortenedUrl);
}
