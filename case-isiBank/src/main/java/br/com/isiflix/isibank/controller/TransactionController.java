package br.com.isiflix.isibank.controller;

import br.com.isiflix.isibank.dto.PaymentDTO;
import br.com.isiflix.isibank.dto.ResponseDTO;
import br.com.isiflix.isibank.service.transaction.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin("*")
public class TransactionController {

    @Autowired
    private ITransactionService service;

    @PostMapping("/pagamentos")
    public ResponseEntity<ResponseDTO> makePayment(@RequestBody PaymentDTO request){
        try {
            if(service.makeThePayment(request)){
                return ResponseEntity.ok(new ResponseDTO("Pagamento efetuado com sucesso."));
            }
            return ResponseEntity.badRequest().body(new ResponseDTO("Não foi possível realizar a transação."));

        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ResponseDTO(ex.getMessage()));
        }
    }
}
