package com.haero77.urlshortener.domain.url.dto;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.haero77.urlshortener.domain.url.entity.Referer;
import com.haero77.urlshortener.domain.url.entity.Url;
import com.haero77.urlshortener.domain.url.entity.UrlCallHistory;

/*
	shortenedUrl
	originUrl
	viewCount: {
		totalViewCount
		viewCountPerDate: []
		viewCountPerReferer: []
	}
 */

public record UrlStatisticsResponse(
	String shortenedUrl,
	String originUrl,
	ViewCount viewCount
) {

	public static UrlStatisticsResponse of(Url url, List<UrlCallHistory> urlCallHistories) {
		ViewCount viewCount = ViewCount.from(urlCallHistories);
		return new UrlStatisticsResponse(url.shortenedUrl(), url.originUrl(), viewCount);
	}

	private record ViewCount(
		int totalViewCount,
		List<ViewCountPerDate> viewCountPerDate,
		List<ViewCountPerReferer> viewCountPerReferer
	) {

		public static ViewCount from(List<UrlCallHistory> urlCallHistories) {
			int totalViewCount = urlCallHistories.size();
			List<ViewCountPerDate> viewCountPerDate = ViewCountPerDate.from(urlCallHistories);
			List<ViewCountPerReferer> viewCountPerReferer = ViewCountPerReferer.from(urlCallHistories);

			return new ViewCount(totalViewCount, viewCountPerDate, viewCountPerReferer);
		}
	}

	private record ViewCountPerDate(
		LocalDate date,
		int viewCount
	) {

		public static List<ViewCountPerDate> from(List<UrlCallHistory> urlCallHistories) {
			return sortByDateAsc(calcViewCountPerDate(urlCallHistories));
		}

		private static Map<LocalDate, Long> calcViewCountPerDate(List<UrlCallHistory> urlCallHistories) {
			return urlCallHistories.stream()
				.collect(groupingBy(urlCall -> urlCall.callTime().toLocalDate(), counting()));
		}

		private static List<ViewCountPerDate> sortByDateAsc(Map<LocalDate, Long> viewCountPerDate) {
			return viewCountPerDate.entrySet()
				.stream()
				.map(entry -> new ViewCountPerDate(entry.getKey(), Math.toIntExact(entry.getValue())))
				.sorted(comparing(ViewCountPerDate::date))
				.toList();
		}
	}

	private record ViewCountPerReferer(
		Referer referer,
		int viewCount
	) {

		public static List<ViewCountPerReferer> from(List<UrlCallHistory> urlCallHistories) {
			return convertToList(calcViewCountPerReferer(urlCallHistories));
		}

		private static Map<Referer, Long> calcViewCountPerReferer(List<UrlCallHistory> urlCallHistories) {
			return urlCallHistories.stream()
				.collect(groupingBy(UrlCallHistory::referer, counting()));
		}

		private static List<ViewCountPerReferer> convertToList(Map<Referer, Long> calcViewCountPerReferer) {
			return calcViewCountPerReferer.entrySet()
				.stream()
				.map(entry -> new ViewCountPerReferer(entry.getKey(), Math.toIntExact(entry.getValue())))
				.toList();
		}
	}
}
