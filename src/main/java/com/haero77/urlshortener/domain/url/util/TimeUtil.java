package com.haero77.urlshortener.domain.url.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class TimeUtil {

	private static final ZoneId SEOUL_ZONE = ZoneId.of("Asia/Seoul");

	private TimeUtil() {
	}

	public static LocalDateTime getCurrentSeoulTime() {
		return ZonedDateTime.now(SEOUL_ZONE)
			.toLocalDateTime();
	}

	public static boolean isDateWithin(LocalDateTime target, LocalDateTime startDate, LocalDateTime endDate) {
		return isEqualOrAfter(target, startDate) && target.isBefore(dayAfterDay(endDate));
	}

	private static boolean isEqualOrAfter(LocalDateTime target, LocalDateTime criterion) {
		return target.equals(criterion) || target.isAfter(criterion);
	}

	private static LocalDateTime dayAfterDay(LocalDateTime endDate) {
		return endDate.plusDays(1).truncatedTo(ChronoUnit.DAYS);
	}
}
