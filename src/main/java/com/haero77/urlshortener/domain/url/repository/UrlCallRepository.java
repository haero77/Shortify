package com.haero77.urlshortener.domain.url.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haero77.urlshortener.domain.url.entity.Url;
import com.haero77.urlshortener.domain.url.entity.UrlCall;

public interface UrlCallRepository extends JpaRepository<UrlCall, Long> {

	List<UrlCall> findUrlCallByUrl(Url url);
}
