package com.movie.movierentals.controller;

import com.movie.movierentals.model.Customer;
import com.movie.movierentals.service.MovieRentalService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieRentalControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MovieRentalService service;

    @Test
    public void calculateRentalRecordTest() throws Exception {
        String inputJson = "{\n\"name\": \"C. U. Stomer\",\n\"rentals\": [\n {\n\"movieId\": \"F001\",\n\"days\": 3\n},\n {\n\"movieId\": \"F002\",\n\"days\": 1\n}\n]\n}";
        String result = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";
        when(service.getStatement(Mockito.any(Customer.class))).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/rentalRecord")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(result, response.getContentAsString());
    }
}
