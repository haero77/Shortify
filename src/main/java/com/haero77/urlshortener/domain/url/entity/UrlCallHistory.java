package com.haero77.urlshortener.domain.url.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.haero77.urlshortener.domain.url.type.BaseTime;

@Entity
public class UrlCallHistory extends BaseTime {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "url_id")
	private Url url;

	@Column(nullable = false, updatable = false)
	private Referer referer;

	protected UrlCallHistory() {
	}

	public UrlCallHistory(Url url) {
		this.url = url;
	}

	public Long id() {
		return id;
	}

	public Url url() {
		return url;
	}

	public Referer referer() {
		return referer;
	}
}
