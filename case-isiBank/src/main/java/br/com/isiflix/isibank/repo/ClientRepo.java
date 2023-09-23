package br.com.isiflix.isibank.repo;

import br.com.isiflix.isibank.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepo extends CrudRepository<Client, Integer> {

    Client findByEmailOrCpfOrPhone(String email, String cpf, String phone);
}
