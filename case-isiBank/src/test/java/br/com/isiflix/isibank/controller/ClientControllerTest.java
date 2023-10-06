package br.com.isiflix.isibank.controller;

import br.com.isiflix.isibank.dto.ClientDTO;
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
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ClientDTO reqValid;
    private ClientDTO reqInvalid;
    private ClientDTO reqEmailDuplicate;
    private ClientDTO reqCpfDuplicate;
    private ClientDTO reqPhoneDuplicate;

    @BeforeEach
    public void setup() {
        reqValid = new ClientDTO("dev Valid API", "clienteApi@email.com", "01234567891", "11852741961", "abc12347");
        reqEmailDuplicate = new ClientDTO("dev email duplicate", "clienteApi@email.com", "12345678009", "11123456789", "abc12345");
        reqCpfDuplicate = new ClientDTO("dev cpf duplicate", "email@outroemail.com", "01234567891", "987584126", "abc12345");
        reqPhoneDuplicate = new ClientDTO("dev phone duplicate", "email@novoemail.com", "12378945600", "11852741961", "abc12345");

        reqInvalid = new ClientDTO("dev Invalid", null, null, null, null);
    }

    @Test
    void shouldCallAPIForValidClient() throws Exception {

        ObjectMapper request = new ObjectMapper();
        String str = request.writeValueAsString(reqValid);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/cliente")
                .contentType("application/json")
                .content(str)).andExpect(MockMvcResultMatchers.status().is(201));
    }

    @Test
    void shouldNotCreateClientWithDuplicateEmail() throws Exception {
        ObjectMapper request = new ObjectMapper();
        String str = request.writeValueAsString(reqEmailDuplicate);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/cliente")
                .contentType("application/json")
                .content(str)).andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void shouldNotCreateClientWithDuplicateCPF() throws Exception {
        ObjectMapper request = new ObjectMapper();
        String str = request.writeValueAsString(reqCpfDuplicate);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/cliente")
                .contentType("application/json")
                .content(str)).andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void shouldNotCreateClientWithDuplicatePhone() throws Exception {
        ObjectMapper request = new ObjectMapper();
        String str = request.writeValueAsString(reqPhoneDuplicate);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/cliente")
                .contentType("application/json")
                .content(str)).andExpect(MockMvcResultMatchers.status().is(400));
    }
}