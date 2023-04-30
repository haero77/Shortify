package com.haero77.urlshortener.domain.shorturl.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.haero77.urlshortener.domain.shorturl.type.BaseTime;

@Entity
public class ShortUrlViewCount extends BaseTime {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "short_url_id")
	private ShortUrl shortUrl;

	private LocalDate localDate;

	private int viewCount;

	protected ShortUrlViewCount() {
	}

	public ShortUrlViewCount(ShortUrl shortUrl) {
		this.shortUrl = shortUrl;
	}
}
