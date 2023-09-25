package br.com.isiflix.isibank.service.transaction;

import br.com.isiflix.isibank.dto.ExtractDTO;
import br.com.isiflix.isibank.dto.PaymentDTO;
import br.com.isiflix.isibank.dto.TransactionDTO;
import br.com.isiflix.isibank.exceptions.InvalidAccountException;
import br.com.isiflix.isibank.exceptions.InvalidDateIntervalException;
import br.com.isiflix.isibank.exceptions.NotEnoughBalanceException;
import br.com.isiflix.isibank.model.Account;
import br.com.isiflix.isibank.model.Transaction;
import br.com.isiflix.isibank.repo.AccountRepo;
import br.com.isiflix.isibank.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public Boolean makeThePayment(PaymentDTO payment) {

        Account account = accountRepo.findById(payment.account()).orElse(null);

        if(Objects.isNull(account)){
            throw new InvalidAccountException("Account "+payment.account()+" does not exist");
        }

        if (account.getBalance()+ account.getLimit() < payment.value()){
            throw new NotEnoughBalanceException("Not enough balance available for Account #"+payment.account());
        }

        Transaction transaction = new Transaction();
        Double balanceIni;
        Double balanceFim;

        balanceIni = account.getBalance();
        balanceFim = account.getBalance() - payment.value();
        transaction.setAccount(account);
        transaction.setValue(payment.value());
        transaction.setInitBalance(balanceIni);
        transaction.setFinalBalance(balanceFim);
        transaction.setDateTime(payment.dateTime());
        transaction.setDescription(payment.description());
        transaction.setNumberDoc(payment.numberDoc());
        transaction.setType(-1);
        transaction.setFinalBalance(balanceFim);
        transactionRepo.save(transaction);

        account.setBalance(balanceFim);
        accountRepo.save(account);
        return true;
    }

    @Override
    public Boolean makeTransfer(TransactionDTO transaction) {

        Account accountOrigin;
        Account accountDestiny;

        accountOrigin = accountRepo.findById(transaction.accountOrigin()).orElse(null);
        accountDestiny = accountRepo.findById(transaction.accountDestiny()).orElse(null);

        if(Objects.isNull(accountOrigin) || Objects.isNull(accountDestiny)){
            throw new InvalidAccountException("Invalid Source or Destination Account");
        }

        if(accountOrigin.getBalance() + accountOrigin.getLimit() < transaction.value()){
            throw new NotEnoughBalanceException("Not Enough Balance for Account #"+accountOrigin.getNumberAccount());
        }

        Transaction trDebit, trCredit;
        double balanceInitO, balanceInitD, balanceFinalO, balanceFinalD;

        balanceInitO = accountOrigin.getBalance();
        balanceFinalO = accountOrigin.getBalance() - transaction.value();

        balanceInitD = accountDestiny.getBalance();
        balanceFinalD = accountDestiny.getBalance() - transaction.value();

        trDebit = new Transaction();
        trDebit.setAccount(accountOrigin);
        trDebit.setDateTime(transaction.dateTime());
        trDebit.setValue(transaction.value());
        trDebit.setInitBalance(balanceInitO);
        trDebit.setFinalBalance(balanceFinalO);
        trDebit.setType(-1);
        trDebit.setDescription(transaction.description());
        trDebit.setNumberDoc(UUID.randomUUID().toString());

        trCredit = new Transaction();
        trCredit.setAccount(accountDestiny);
        trCredit.setDateTime(transaction.dateTime());
        trCredit.setValue(transaction.value());
        trCredit.setInitBalance(balanceInitD);
        trCredit.setFinalBalance(balanceFinalD);
        trCredit.setType(1);
        trCredit.setDescription(transaction.description());
        trCredit.setNumberDoc(UUID.randomUUID().toString());

        transactionRepo.save(trCredit);
        transactionRepo.save(trDebit);

        accountOrigin.setBalance(balanceFinalO);
        accountOrigin.setBalance(balanceFinalD);

        accountRepo.save(accountOrigin);
        accountRepo.save(accountDestiny);

        return true;
    }

    @Override
    public List<Transaction> getExtract(ExtractDTO extract) {
        Account account = accountRepo.findById(extract.numberAccount()).orElse(null);

        if(Objects.isNull(account)){
            throw new InvalidAccountException("Invalid Account number #"+extract.numberAccount());
        }

        if(extract.dateInit().isAfter(extract.dateFinal())){
            throw new InvalidDateIntervalException("Invalid Date Interval");
        }
        return transactionRepo.findByAccountAndDateTimeBetween(account, extract.dateInit(), extract.dateFinal());
    }
}
