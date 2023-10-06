package br.com.isiflix.isibank.controller;

import br.com.isiflix.isibank.dto.PaymentDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTest {

    @Autowired
    MockMvc mockMvc;

    private PaymentDTO paymentValid;
    private PaymentDTO pgtoBalanceInsufficient;
    private PaymentDTO pgtoAccountInvalid;

    @BeforeEach
    public void setup() {
        paymentValid = new PaymentDTO(10, LocalDateTime.parse("2023-09-11T21:00:00"), "123456", "Boleto", 100.00);
        pgtoBalanceInsufficient = new PaymentDTO(20, LocalDateTime.parse("2023-09-11T21:00:00"), "123456", "Boleto", 100000.00);
        pgtoAccountInvalid = new PaymentDTO(100, LocalDateTime.parse("2023-09-11T21:00:00"), "123456", "Boleto", 100.00);
    }

    @Test
    void shouldPerformPayment() throws Exception{
        ObjectMapper request = new ObjectMapper();
        request.registerModule(new JavaTimeModule());
        String str = request.writeValueAsString(paymentValid);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/pagamentos")
                .contentType("application/json")
                .content(str)).andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldInvalidPaymentDueToBalance() throws Exception{
        ObjectMapper request = new ObjectMapper();
        request.registerModule(new JavaTimeModule());
        String str = request.writeValueAsString(pgtoBalanceInsufficient);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/pagamentos")
                .contentType("application/json")
                .content(str)).andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void shouldInvalidPaymentDueToDestinationAccount() throws Exception{
        ObjectMapper request = new ObjectMapper();
        request.registerModule(new JavaTimeModule());
        String str = request.writeValueAsString(pgtoAccountInvalid);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/pagamentos")
                .contentType("application/json")
                .content(str)).andExpect(MockMvcResultMatchers.status().is(400));
    }
}