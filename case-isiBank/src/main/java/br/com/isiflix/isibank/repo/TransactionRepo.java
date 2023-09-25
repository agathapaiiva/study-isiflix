package br.com.isiflix.isibank.repo;

import br.com.isiflix.isibank.model.Account;
import br.com.isiflix.isibank.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepo extends CrudRepository<Transaction, Long> {

    List<Transaction> findByAccountAndDateTimeBetween(Account account, LocalDateTime dateInit, LocalDateTime dateFinal);
}
