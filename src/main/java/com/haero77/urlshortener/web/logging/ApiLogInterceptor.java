package com.haero77.urlshortener.web.logging;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiLogInterceptor implements HandlerInterceptor {

	public static final String LOG_ID = "logId";

	@Override
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler
	) {
		String method = request.getMethod();
		String requestUrl = request.getRequestURL().toString();
		String logId = UUID.randomUUID()
			.toString()
			.substring(0, 8);

		request.setAttribute(LOG_ID, logId);

		log.info("[>>> REQUEST] {} {} ({})", method, requestUrl, logId);
		return true;
	}

	@Override
	public void afterCompletion(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler,
		Exception ex
	) {
		String method = request.getMethod();
		String requestUrl = request.getRequestURL().toString();
		String logId = (String)request.getAttribute(LOG_ID);

		log.info("[<<< RESPONSE] {} {} ({})", method, requestUrl, logId);
	}
}
