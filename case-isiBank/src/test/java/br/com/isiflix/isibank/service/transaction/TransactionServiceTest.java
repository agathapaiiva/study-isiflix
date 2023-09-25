package br.com.isiflix.isibank.service.transaction;

import br.com.isiflix.isibank.dto.ExtractDTO;
import br.com.isiflix.isibank.dto.PaymentDTO;
import br.com.isiflix.isibank.dto.TransactionDTO;
import br.com.isiflix.isibank.exceptions.InvalidAccountException;
import br.com.isiflix.isibank.exceptions.InvalidDateIntervalException;
import br.com.isiflix.isibank.exceptions.NotEnoughBalanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private ITransactionService service;

    private PaymentDTO paymentValid;
    private PaymentDTO pgtoBalanceInsufficient;
    private PaymentDTO pgtoAccountInvalid;

    private TransactionDTO transfValid;
    private TransactionDTO transfOriginInvalid;
    private TransactionDTO transfDestinyInvalid;
    private TransactionDTO transfBalanceInsufficient;

    private ExtractDTO extractValid;
    private ExtractDTO extractAccountInvalid;
    private ExtractDTO extractDateInvalid;

    @BeforeEach
    public void setup(){
        paymentValid = new PaymentDTO(10, LocalDateTime.parse("2023-09-11T21:00:00"), "123456", "Boleto", 100.00);
        pgtoBalanceInsufficient = new PaymentDTO(20, LocalDateTime.parse("2023-09-11T21:00:00"), "123456", "Boleto", 100000.00);
        pgtoAccountInvalid = new PaymentDTO(100, LocalDateTime.parse("2023-09-11T21:00:00"), "123456", "Boleto", 100.00);

        transfValid = new TransactionDTO(10, 20, LocalDateTime.parse("2023-09-11T21:00:00"), 100.00, "Manda o PIX");
        transfOriginInvalid  = new TransactionDTO(100, 20, LocalDateTime.parse("2023-09-11T21:00:00"), 100.00, "Manda o PIX");
        transfDestinyInvalid = new TransactionDTO(10, 100, LocalDateTime.parse("2023-09-11T21:00:00"), 100.00, "Manda o PIX");
        transfBalanceInsufficient = new TransactionDTO(10, 20, LocalDateTime.parse("2023-09-11T21:00:00"), 1000000.00, "Manda o PIX");

        extractValid = new ExtractDTO(10, LocalDateTime.parse("2023-01-01T00:00:00"), LocalDateTime.parse("2023-09-11T23:59:59"));
        extractAccountInvalid = new ExtractDTO(100, LocalDateTime.parse("2023-01-01T00:00:00"), LocalDateTime.parse("2023-09-11T23:59:59"));
        extractDateInvalid = new ExtractDTO(10, LocalDateTime.parse("2023-09-11T23:59:59"), LocalDateTime.parse("2023-01-01T00:00:00"));
    }

    @Test
    public void shouldEffectiveDoPayment() {
        assertTrue(service.makeThePayment(paymentValid));
    }

    @Test
    public void shouldCheckInsufficientBalance() {
        assertThrows(NotEnoughBalanceException.class, ()-> service.makeThePayment(pgtoBalanceInsufficient));
    }

    @Test
    public void shouldCheckInvalidAccount() {
        assertThrows(InvalidAccountException.class, () -> service.makeThePayment(pgtoAccountInvalid));
    }

    @Test
    public void shouldEffectiveTransfer() {
        assertTrue(service.makeTransfer(transfValid));
    }

    @Test
    public void shouldCheckInvalidSourceAccount() {
        assertThrows(InvalidAccountException.class, () -> service.makeTransfer(transfOriginInvalid));
    }

    @Test
    public void shouldCheckInvalidDestinatinoAccount() {
        assertThrows(InvalidAccountException.class, () -> service.makeTransfer(transfDestinyInvalid));
    }

    @Test
    public void shouldCheckAccountBallanceInsuficient() {
        assertThrows(NotEnoughBalanceException.class, () -> service.makeTransfer(transfBalanceInsufficient));
    }


    @Test
    public void shouldRetrieveExtrato() {
        assertNotNull(service.getExtract(extractValid));
    }

    @Test
    public void shouldCheckInvalidAccountNoExtrato() {
        assertThrows(InvalidAccountException.class, () -> service.getExtract(extractAccountInvalid));
    }

    @Test
    public void shouldCheckDateInterval() {
        assertThrows(InvalidDateIntervalException.class, () -> service.getExtract(extractDateInvalid));
    }


}
