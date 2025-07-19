package com.sree.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;


@SpringBootTest
@AutoConfigureMockMvc
public class FlightBookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testBookingEndpoint() throws Exception {
        String passengerJson = """
        {
            "name": "Alice",
            "email": "alice@example.com",
            "Source": "Delhi",
            "Destination": "Mumbai",
            "travelDate": "20-07-2025",
            "pickUpTime": "10:00",
            "arrivaltime": "12:00",
            "fare": 800.0
        }
        """;

        mockMvc.perform(post("/api/book")
                .content(passengerJson)
                .param("paymentId", "TXN125")
                .param("accountNo", "789012")
                .param("amount", "700.0")
                .param("cardType", "Rupay")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Flight booked successfully!"));
    }
}

