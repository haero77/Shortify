package com.haero77.urlshortener.web.api.url;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.haero77.urlshortener.domain.url.entity.Url;
import com.haero77.urlshortener.domain.url.repository.UrlRepository;
import com.haero77.urlshortener.domain.url.util.TimeUtil;
import com.haero77.urlshortener.restdocs.ApiDocumentUtils;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
@Transactional
class UrlDeleteApiTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UrlRepository urlRepository;

	@Test
	@DisplayName("단축 URL 삭제")
	void url_delete() throws Exception {

		LocalDateTime currentDate = TimeUtil.getCurrentSeoulTime();
		Url url = Url.defaultOf(
			"https://github.com/haero77", Url.DEFAULT_EXPIRATION_PERIOD, currentDate.minusDays(7));
		String shortenedUrl = "shortenedUrl";
		url.assignShortenedUrl(shortenedUrl);

		Url savedUrl = urlRepository.save(url);

		// when & then
		ResultActions resultActions = mockMvc.perform(
				delete("/api/urls/{urlId}", savedUrl.id()))
			.andExpect(status().isNoContent());

		// REST Docs
		resultActions
			.andDo(print())
			.andDo(document("short-url-delete",
				ApiDocumentUtils.getDocumentRequest(),
				ApiDocumentUtils.getDocumentResponse(),
				pathParameters(
					parameterWithName("urlId").description("단축된 URL ID")
				)));
	}
}