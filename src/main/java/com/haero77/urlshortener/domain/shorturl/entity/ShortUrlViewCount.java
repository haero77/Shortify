package com.haero77.urlshortener.domain.shorturl.entity;

import static javax.persistence.GenerationType.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ShortUrlViewCount {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
}
