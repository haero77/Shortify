package com.haero77.urlshortener.web.api.shorturl;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haero77.urlshortener.domain.shorturl.dto.ShortUrlCreateRequest;
import com.haero77.urlshortener.domain.shorturl.repository.ShortUrlRepository;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
class ShortUrlCreateApiTest {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ShortUrlRepository shortUrlRepository;

	@Test
	@DisplayName("단축 URL 생성")
	void create() throws Exception {
		// given
		String originUrl = "https://github.com/haero77";
		boolean expirationOption = false;
		ShortUrlCreateRequest shortUrlCreateRequest = new ShortUrlCreateRequest(originUrl, expirationOption);

		// when & then
		ResultActions resultActions = mockMvc.perform(post("/api/urls")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(shortUrlCreateRequest)))
			.andExpect(status().isCreated());

		// REST Docs
		resultActions
			.andDo(print())
			.andDo(document("short-url-create2",
				requestFields(
					fieldWithPath("url").description("단축할 URL"),
					fieldWithPath("expiration").description("만료 옵션 지정 여부")
				)));
	}
}