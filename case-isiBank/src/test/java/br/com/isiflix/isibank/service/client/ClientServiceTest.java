package br.com.isiflix.isibank.service.client;

import br.com.isiflix.isibank.dto.ClientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientServiceTest {

    private ClientDTO reqValid;
    private ClientDTO reqInvalid;
    private ClientDTO reqEmailDuplicate;
    private ClientDTO reqCpfDuplicate;
    private ClientDTO reqPhoneDuplicate;
    private Integer idValid;

    @Autowired
    private IClientService service;

    @BeforeEach
    public void setup(){
        reqValid = new ClientDTO("dev Valid", "cliente@email.com", "01234567890", "11852741963", "abc12345");
        reqEmailDuplicate = new ClientDTO("dev email duplicate","email@email.com","12345678009","11123456789", "abc12345");
        reqCpfDuplicate = new ClientDTO("dev cpf duplicate","email@outroemail.com","12345678900","987584126", "abc12345");
        reqPhoneDuplicate = new ClientDTO("dev phone duplicate","email@novoemail.com","12378945600","11987654321", "abc12345");

        reqInvalid  = new ClientDTO("dev Invalid", null, null, null, null);
        idValid = (1);

//        Mockito.when(iClientService.createClient(reqValid)).thenReturn(idValid);
//        Mockito.when(iClientService.createClient(reqInvalid)).thenThrow(new ConstraintViolationException(null));
//        Mockito.when(iClientService.createClient(reqEmailDuplicate)).thenReturn(null);
//        Mockito.when(iClientService.createClient(reqCpfDuplicate)).thenReturn(null);
//        Mockito.when(iClientService.createClient(reqPhoneDuplicate)).thenReturn(null);
    }

    @Test
    @DisplayName("Should create a valid client.")
    void ShouldCreateValidClient() {
        assertNotNull(service.createClient(reqValid));
    }

    @Test
    @DisplayName("Should deny a invalid client.")
    void ShouldDenyInvalidClient() {
        assertThrows(DataIntegrityViolationException.class, () -> service.createClient(reqInvalid));
    }

    @Test
    @DisplayName("Should return null when duplicate | EMAIL")
    void shouldReturnNullWhenDuplicateEmail(){
        assertNull(service.createClient(reqEmailDuplicate));
    }

    @Test
    @DisplayName("Should return null when duplicate | CPF")
    void shouldReturnNullWhenDuplicateCpf(){
        assertNull(service.createClient(reqCpfDuplicate));
    }

    @Test
    @DisplayName("Should return null when duplicate | PHONE")
    void shouldReturnNullWhenDuplicatePhone(){
        assertNull(service.createClient(reqPhoneDuplicate));
    }
}