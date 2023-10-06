package br.com.isiflix.isibank.controller;

import br.com.isiflix.isibank.dto.AccountDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private AccountDTO accountValid;
    private AccountDTO accountInvalid;

    @BeforeEach
    public void setup() {
        accountValid = new AccountDTO(1, 1, 100.0, 0.0, 10);
        accountInvalid = new AccountDTO(1, 1, 100.0, 0.0, 150);
    }

    @Test
    void shouldCallAPIToCreateAccount() throws Exception{
        ObjectMapper request = new ObjectMapper();
        String str = request.writeValueAsString(accountValid);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/conta")
                .contentType("application/json")
                .content(str)).andExpect(MockMvcResultMatchers.status().is(201));
    }


    @Test
    void shouldNotCallAPIToCreateAccount() throws Exception{
        ObjectMapper request = new ObjectMapper();
        String str = request.writeValueAsString(accountInvalid);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/conta")
                .contentType("application/json")
                .content(str)).andExpect(MockMvcResultMatchers.status().is(400));
    }
}