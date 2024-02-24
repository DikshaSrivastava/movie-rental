package com.movie.movierentals.controller;

import com.movie.movierentals.model.Customer;
import com.movie.movierentals.model.MovieAmount;
import com.movie.movierentals.model.RentalRecord;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        RentalRecord result = new RentalRecord();
        result.setCustomerName("C. U. Stomer");
        result.setFrequentPoints(2);
        result.setMovieAmountList(List.of(new MovieAmount(
                "You've Got Mail",
                3.5
        ),
               new MovieAmount("Matrix",
                       2.0) ));
        result.setOwedAmount(5.5);
        when(service.getStatement(Mockito.any(Customer.class))).thenReturn(result);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/rentalRecord")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder).andExpect(status().isOk());
    }


    @Test
    public void getRentalRecordsTest() throws Exception {
        RentalRecord result = new RentalRecord();
        result.setCustomerName("C. U. Stomer");
        result.setFrequentPoints(2);
        result.setMovieAmountList(List.of(new MovieAmount(
                        "You've Got Mail",
                        3.5
                ),
                new MovieAmount("Matrix",
                        2.0) ));
        result.setOwedAmount(5.5);
        when(service.getRentalRecords()).thenReturn(List.of(result));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/rentalRecords")
                .contentType(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    public void getRentalRecordTest() throws Exception {
        RentalRecord result = new RentalRecord();
        result.setCustomerName("C. U. Stomer");
        result.setFrequentPoints(2);
        result.setMovieAmountList(List.of(new MovieAmount(
                        "You've Got Mail",
                        3.5
                ),
                new MovieAmount("Matrix",
                        2.0) ));
        result.setOwedAmount(5.5);
        when(service.getRentalRecord(Mockito.anyString())).thenReturn(result);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/rentalRecord/{customerName}", "C. U. Stomer")
                .contentType(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder).andExpect(status().isOk());
    }
}
