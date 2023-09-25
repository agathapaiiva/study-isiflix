package br.com.isiflix.isibank.service.transaction;

import br.com.isiflix.isibank.dto.ExtractDTO;
import br.com.isiflix.isibank.dto.PaymentDTO;
import br.com.isiflix.isibank.dto.TransactionDTO;
import br.com.isiflix.isibank.model.Transaction;

import java.util.List;

public interface ITransactionService {

    Boolean makeThePayment(PaymentDTO payment);
    Boolean makeTransfer(TransactionDTO transaction);
    List<Transaction> getExtract(ExtractDTO extract);
}
