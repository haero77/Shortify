package com.haero77.urlshortener.domain.url.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haero77.urlshortener.domain.url.dto.UrlStatisticsResponse;
import com.haero77.urlshortener.domain.url.entity.Url;
import com.haero77.urlshortener.domain.url.entity.UrlCallHistory;
import com.haero77.urlshortener.domain.url.repository.UrlCallRepository;
import com.haero77.urlshortener.domain.url.util.TimeUtil;

@Transactional(readOnly = true)
@Service
public class UrlStatisticsProvider {

	private static final int STATISTICS_QUERY_DAYS = 7;

	private final UrlCallRepository urlCallRepository;
	private final UrlReader urlReader;

	public UrlStatisticsProvider(UrlCallRepository urlCallRepository, UrlReader urlReader) {
		this.urlCallRepository = urlCallRepository;
		this.urlReader = urlReader;
	}

	public UrlStatisticsResponse getStatistics(String shortenedUrl) {
		Url findUrl = urlReader.findByShortenedUrl(shortenedUrl);
		List<UrlCallHistory> urlCallHistories = urlCallRepository.findUrlCallByUrl(findUrl);

		List<UrlCallHistory> urlCallsWithinDayHistories = filterWithinDaysFromCurrentDate(urlCallHistories,
			STATISTICS_QUERY_DAYS);
		return UrlStatisticsResponse.of(findUrl, urlCallsWithinDayHistories);
	}

	private List<UrlCallHistory> filterWithinDaysFromCurrentDate(List<UrlCallHistory> urlCallHistories, int days) {
		LocalDateTime currentDateTime = TimeUtil.getCurrentSeoulTime();
		return filterByDuration(urlCallHistories, currentDateTime.minusDays(days), currentDateTime);
	}

	private List<UrlCallHistory> filterByDuration(List<UrlCallHistory> urlCallHistories, LocalDateTime startDate,
		LocalDateTime endDate) {
		return urlCallHistories.stream()
			.filter(urlCall -> urlCall.isCallTimeWithin(startDate, endDate))
			.toList();
	}
}
