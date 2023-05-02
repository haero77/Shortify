package com.haero77.urlshortener.domain.url.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haero77.urlshortener.domain.url.entity.UrlCall;

public interface UrlCallRepository extends JpaRepository<UrlCall, Long> {
}
