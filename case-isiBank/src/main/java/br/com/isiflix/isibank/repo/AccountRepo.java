package br.com.isiflix.isibank.repo;

import br.com.isiflix.isibank.model.Account;
import br.com.isiflix.isibank.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepo extends CrudRepository<Account, Integer> {

    List<Account> findByClient(Client client);
}
