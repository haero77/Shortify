package com.haero77.urlshortener.global.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtil {

	private static final ZoneId SEOUL_ZONE = ZoneId.of("Asia/Seoul");

	private TimeUtil() {
	}

	public static LocalDateTime getCurrentSeoulTime() {
		return ZonedDateTime.now(SEOUL_ZONE)
			.toLocalDateTime();
	}
}
