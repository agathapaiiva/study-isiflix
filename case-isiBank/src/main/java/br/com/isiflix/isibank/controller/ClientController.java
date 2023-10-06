package br.com.isiflix.isibank.controller;

import br.com.isiflix.isibank.dto.ClientDTO;
import br.com.isiflix.isibank.dto.ResponseDTO;
import br.com.isiflix.isibank.service.client.IClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

@Controller
@CrossOrigin("*")
public class ClientController {

    @Autowired
    private IClientService service;

    @PostMapping("/cliente")
    public ResponseEntity<ResponseDTO> createClient(@Valid @RequestBody ClientDTO request){
        try {

            Integer res = service.createClient(request);
            if(Objects.nonNull(res)){
                return ResponseEntity.status(201).body(new ResponseDTO("Cliente cadastrado com sucesso "+res));
            }
            return ResponseEntity.badRequest().body(new ResponseDTO("Impossivel cadastrar cliente com dados existentes"));

        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ResponseDTO("Dados do cliente Imcompletos."));
        }
    }
}
