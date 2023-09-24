package br.com.isiflix.isibank.dto;

import br.com.isiflix.isibank.model.Account;
import br.com.isiflix.isibank.model.Client;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record AccountDTO(@NotNull Integer numberBank,
                         @NotNull Integer numberAgency,
                         @NotNull @PositiveOrZero Double balance,
                         @NotNull @PositiveOrZero Double limit,
                         @NotNull Integer idCliente) {


    public Account toAccount() {
        Account account = new Account();
        account.setNumberBank(numberBank);
        account.setNumberAgency(numberAgency);
        account.setBalance(balance);
        account.setLimit(limit);
        account.setActive(1);
        Client client = new Client();
        client.setIdClient(idCliente);
        client.setCpf("111");
        client.setEmail("email@abc.com");
        client.setPhone("1234");
        account.setClient(client);
        return account;
    }
}
