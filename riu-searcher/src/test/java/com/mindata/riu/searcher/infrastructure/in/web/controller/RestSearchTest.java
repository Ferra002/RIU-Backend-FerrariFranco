package com.mindata.riu.searcher.infrastructure.in.web.controller;

import com.mindata.riu.searcher.application.port.out.event.SearchEventPublisher;
import com.mindata.riu.searcher.domain.repository.SearchRepository;
import com.mindata.riu.searcher.factory.TestClassBuilder;
import com.mindata.riu.searcher.infrastructure.in.web.dto.request.SearchRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

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
                        jsonPath("$.search.checkIn").value(expected.checkIn().format(formatter)),
                        jsonPath("$.search.checkOut").value(expected.checkOut().format(formatter)),
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

    @Test
    void testSearchInvalidDatesFormat() throws Exception{
        String body = """
                {
                    "hotelId": "valid-hotel",
                    "checkIn": "3000-01-01",
                    "checkOut": "3100-01-01",
                    "ages": [1, 2, 3]
                }
                """;

        mockMvc.perform(
                        post("/search")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(body)
                )
                .andExpectAll(
                        status().is4xxClientError(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                );
    }

    @Test
    void testSearchDateFromPast() throws Exception{
        SearchRequestDTO body = new SearchRequestDTO(
                "valid-hotel",
                LocalDate.of(2000,1,1),
                LocalDate.of(3000,1,1),
                List.of(1,2,3)
        );

        mockMvc.perform(
                        post("/search")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(body))
                )
                .andExpectAll(
                        status().is4xxClientError(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                );
    }

}