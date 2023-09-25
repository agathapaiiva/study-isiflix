package br.com.isiflix.isibank.service.account;

import br.com.isiflix.isibank.dto.AccountDTO;

public interface IAccountService {

    Integer createAccount(AccountDTO account);
}
