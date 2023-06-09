package com.haero77.urlshortener.domain.url.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.haero77.urlshortener.domain.url.type.BaseTime;
import com.haero77.urlshortener.domain.url.util.TimeUtil;

@Entity
public class UrlCallHistory extends BaseTime {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "url_id")
	private Url url;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, updatable = false)
	private Referer referer;

	@Column(nullable = false, updatable = false)
	private LocalDateTime callTime;

	protected UrlCallHistory() {
	}

	public UrlCallHistory(Url url, Referer referer, LocalDateTime callTime) {
		this.url = url;
		this.referer = referer;
		this.callTime = callTime;
	}

	public boolean isCallTimeWithin(LocalDateTime startDate, LocalDateTime endDate) {
		return TimeUtil.isDateWithin(this.callTime, startDate, endDate);
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

	public LocalDateTime callTime() {
		return callTime;
	}
}
