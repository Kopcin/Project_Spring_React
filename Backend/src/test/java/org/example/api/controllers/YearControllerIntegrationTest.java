package org.example.api.controllers;

import java.util.*;

import org.example.api.repository.YearRepository;
import org.example.models.Year;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class YearControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private YearRepository repository;

    @Test
    public void contextLoads() {
        // This test is just to check if the Spring context loads successfully
        System.out.println("Context loads test executed successfully.");
    }

    @Test
    public void testGetYearAndPopulation() throws Exception {
        Year year = new Year(2022);
        year.setPopulation(1000000L);

        repository.saveAndFlush(year);

        mvc.perform(get("/api/years/population")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].year", is(2022)))
                .andExpect(jsonPath("$[0].column", is(1000000)));
    }

    @Test
    public void testGetYearAndGDP() throws Exception {
        Year year = new Year(1980);
        year.setGdp(424242444L);

        repository.saveAndFlush(year);

        mvc.perform(get("/api/years/gdp")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].year", is(1980)))
                .andExpect(jsonPath("$[0].column", is(424242444)));
    }

    @Test
    public void testGetYearAndInflation() throws Exception {
        Year year = new Year(2019);
        year.setInflation(16.2F);

        repository.saveAndFlush(year);

        mvc.perform(get("/api/years/inflation")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].year", is(2019)))
                .andExpect(jsonPath("$[0].column", is(16.2)));
    }

    @Test
    public void givenYears_whenGetYears_thenStatus200() throws Exception {
        List<Integer> yearsList = Arrays.asList(2020, 2023, 1920, 1978);

        for (Integer year : yearsList) {
            createTestYear(year);
        }

        mvc.perform(get("/api/years/population")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[*].year", containsInAnyOrder(yearsList.toArray())));
    }

    @AfterEach
    @DirtiesContext
    public void tearDown() {
        repository.deleteAll();
    }

    private void createTestYear(int year) {
        Year testYear = new Year(year);
        repository.saveAndFlush(testYear);
    }
}
