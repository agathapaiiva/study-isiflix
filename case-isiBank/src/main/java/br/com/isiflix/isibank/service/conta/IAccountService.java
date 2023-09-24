package br.com.isiflix.isibank.service.conta;

import br.com.isiflix.isibank.dto.AccountDTO;

public interface IAccountService {

    Integer createAccount(AccountDTO account);
}
