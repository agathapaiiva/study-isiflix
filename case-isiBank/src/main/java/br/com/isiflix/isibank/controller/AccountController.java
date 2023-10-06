package br.com.isiflix.isibank.controller;

import br.com.isiflix.isibank.dto.AccountDTO;
import br.com.isiflix.isibank.dto.ResponseDTO;
import br.com.isiflix.isibank.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

@Controller
@CrossOrigin("*")
public class AccountController {

    @Autowired
    private IAccountService service;

    @PostMapping("/conta")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody AccountDTO request){

        Integer numAccount = service.createAccount(request);

        if (Objects.nonNull(numAccount)){
            return ResponseEntity.status(201).body(new ResponseDTO("Conta " +numAccount+ " criada com sucesso."));
        }
        return ResponseEntity.badRequest().body(new ResponseDTO("Dados inválidos para conta."));
    }
}
