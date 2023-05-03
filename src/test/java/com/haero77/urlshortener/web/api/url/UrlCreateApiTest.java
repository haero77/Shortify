package com.haero77.urlshortener.web.api.url;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haero77.urlshortener.domain.url.dto.UrlCreateRequest;
import com.haero77.urlshortener.domain.url.repository.UrlRepository;
import com.haero77.urlshortener.restdocs.ApiDocumentUtils;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
@Transactional
class UrlCreateApiTest {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UrlRepository urlRepository;

	@Test
	@DisplayName("단축 URL 생성")
	void create() throws Exception {
		// given
		String originUrl = "https://github.com/haero77";
		boolean expirationOption = false;
		UrlCreateRequest urlCreateRequest = new UrlCreateRequest(originUrl, expirationOption);

		// when & then
		ResultActions resultActions = mockMvc.perform(post("/api/urls")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(urlCreateRequest)))
			.andExpect(status().isCreated());

		// REST Docs
		resultActions
			.andDo(print())
			.andDo(document("short-url-create",
				ApiDocumentUtils.getDocumentRequest(),
				ApiDocumentUtils.getDocumentResponse(),
				requestFields(
					fieldWithPath("url").description("단축할 URL"),
					fieldWithPath("expiration").description("만료 옵션 지정 여부")
				),
				responseFields(
					fieldWithPath("id").description("단축된 URL ID"),
					fieldWithPath("shortenedUrl").description("단축된 URL"),
					fieldWithPath("originUrl").description("원본 URL")
				)));
	}
}