package com.mindata.riu.infrastructure.web.controller;

import com.mindata.riu.application.port.out.event.SearchEventPublisher;
import com.mindata.riu.application.port.out.repository.SearchRepository;
import com.mindata.riu.factory.TestClassBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class RestSearchTest {

    private static final String BASE_PATH = "/api/hotel-search/v1";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private SearchRepository searchRepository;

    @MockitoBean
    private SearchEventPublisher searchEventPublisher;

    @Test
    void testCountOk() throws Exception{
        var body = TestClassBuilder.COUNT_REQUEST_DTO;
        var expected = TestClassBuilder.SEARCH_REPOSITORY_DTO;

        given(searchRepository.findBySearchId(body.searchId()))
                .willReturn(Optional.of(expected));

        mockMvc.perform(
                get(BASE_PATH + "/count")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                )
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.searchId").value(expected.searchId()),
                        jsonPath("$.search.hotelId").value(expected.hotelId()),
                        jsonPath("$.search.checkIn").value(expected.checkIn().toString()),
                        jsonPath("$.search.checkOut").value(expected.checkOut().toString()),
                        jsonPath("$.search.ages").isArray()

                );
    }

    @Test
    void testCountInvalidBody() throws Exception {
        mockMvc.perform(
                get(BASE_PATH + "/count")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{}")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCountNotFound() throws Exception{
        var body = TestClassBuilder.COUNT_REQUEST_DTO;

        mockMvc.perform(
                get(BASE_PATH + "/count")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void testSearchOk() throws Exception{
        var body = TestClassBuilder.SEARCH_REQUEST_DTO;

        mockMvc.perform(
                post(BASE_PATH + "/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                )
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.searchId").exists(),
                        jsonPath("$.searchId").isNotEmpty()
                );
    }

    @Test
    void testSearchInvalidBody() throws Exception{
        mockMvc.perform(
                        post(BASE_PATH + "/search")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content("{}")
                )
                .andExpect(status().isBadRequest());
    }

}