package br.com.isiflix.isibank.service.account;

import br.com.isiflix.isibank.dto.AccountDTO;
import br.com.isiflix.isibank.model.Account;
import br.com.isiflix.isibank.model.Client;
import br.com.isiflix.isibank.repo.AccountRepo;
import br.com.isiflix.isibank.repo.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountServiceImpl implements IAccountService {

    @Value("${isibank.banknumber}")
    private Integer bankNumber;

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private ClientRepo clientRepo;

    @Override
    public Integer createAccount(AccountDTO account) {

        Client client = clientRepo.findById(account.idCliente()).orElse(null);

        if(Objects.isNull(client)){
            System.out.println("Client is null.");
            return null;
        }

        Account accounts = account.toAccount();
        accounts.setNumberBank(bankNumber);
        return accountRepo.save(accounts).getNumberAccount();
    }
}
