package com.mydeveloperplanet.mymapstructplanet.adapter;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenCreateCustomer_thenReturnOk() throws Exception {
        String body = """
                {
                  "firstName": "John",
                  "lastName": "Doe",
                  "street": "street",
                  "number": "42",
                  "postalCode": "1234",
                  "city": "city"
                }
                """;


        mockMvc.perform(post("/customer")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", equalTo("John")))
                .andExpect(jsonPath("lastName", equalTo("Doe")))
                .andExpect(jsonPath("customerId", equalTo(0)))
                .andExpect(jsonPath("street", equalTo("street")))
                .andExpect(jsonPath("number", equalTo("42")))
                .andExpect(jsonPath("postalCode", equalTo("1234")))
                .andExpect(jsonPath("city", equalTo("city")));

    }

}
