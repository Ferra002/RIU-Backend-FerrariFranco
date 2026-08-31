package com.mindata.riu.searcher.infrastructure.in.web.controller;

import com.mindata.riu.searcher.application.port.out.event.SearchEventPublisher;
import com.mindata.riu.searcher.domain.repository.SearchRepository;
import com.mindata.riu.searcher.factory.TestClassBuilder;
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
        String searchId = "search-id";
        var expected = TestClassBuilder.SEARCH_REPOSITORY_DTO;

        given(searchRepository.findBySearchId(searchId))
                .willReturn(Optional.of(expected));

        mockMvc.perform(
                get("/count")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("searchId", searchId)
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
                get("/count")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{}")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCountNotFound() throws Exception{
        mockMvc.perform(
                get("/count")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("searchId", "search-id")
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void testSearchOk() throws Exception{
        var body = TestClassBuilder.SEARCH_REQUEST_DTO;

        mockMvc.perform(
                post("/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                )
                .andExpectAll(
                        status().isCreated(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.searchId").exists(),
                        jsonPath("$.searchId").isNotEmpty()
                );
    }

    @Test
    void testSearchInvalidBody() throws Exception{
        mockMvc.perform(
                        post("/search")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content("{}")
                )
                .andExpect(status().isBadRequest());
    }

}