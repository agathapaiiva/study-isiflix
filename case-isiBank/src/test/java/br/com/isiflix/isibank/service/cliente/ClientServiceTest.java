package br.com.isiflix.isibank.service.cliente;

import br.com.isiflix.isibank.dto.ClientDTO;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientServiceTest {

    private ClientDTO reqValid;
    private ClientDTO reqInvalid;
    private Integer idValid;

    @Mock
    private IClientService iClientService;

    @BeforeEach
    public void setup(){
        reqValid = new ClientDTO("dev","email@email.com.br","52052356998","25685947", "123456");
        reqInvalid  = new ClientDTO("dev", "dev@email.com.br", "52052356998", "25685947", null);
        idValid = (1);

        Mockito.when(iClientService.createClient(reqValid)).thenReturn(idValid);
        Mockito.when(iClientService.createClient(reqInvalid)).thenThrow(new ConstraintViolationException(null));
    }

    @Test
    @DisplayName("Should create a valid client.")
    void ShouldCreateValidClient() {
        assertEquals(iClientService.createClient(reqValid), idValid);
    }

    @Test
    @DisplayName("Should deny a invalid client.")
    void ShouldDenyInvalidClient() {
        assertThrows(ConstraintViolationException.class, () -> iClientService.createClient(reqInvalid));
    }
}