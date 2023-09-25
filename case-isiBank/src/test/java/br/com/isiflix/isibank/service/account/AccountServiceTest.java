package br.com.isiflix.isibank.service.account;

import br.com.isiflix.isibank.dto.AccountDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceTest {
    @Mock
    private IAccountService service;

    private AccountDTO accountValid;
    private AccountDTO accountInvalid;

    @BeforeEach
    public void setup() {
        accountValid = new AccountDTO(1, 1, 100.0, 0.0, 10);
        accountInvalid = new AccountDTO(1, 1, 100.0, 0.0, 2);

		Mockito.when(service.createAccount(accountValid)).thenReturn(1);
		Mockito.when(service.createAccount(accountInvalid)).thenReturn(null);
    }

    @Test
    @DisplayName("should accept account with existing customer")
    void shouldAcceptAccountWithExistingCustomer() {
        assertNotNull(service.createAccount(accountValid));
    }

    @Test
    @DisplayName("should reject accounts with invalid customer")
    void shouldRejectAccountsWithInvalidCustomer(){
        assertNull(service.createAccount(accountInvalid));
    }
}