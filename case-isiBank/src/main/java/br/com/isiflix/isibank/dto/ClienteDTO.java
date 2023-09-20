package br.com.isiflix.isibank.dto;

import br.com.isiflix.isibank.model.Cliente;

public record ClienteDTO(String nome, String email, String cpf, String telefone, String senha) {

    public Cliente toCliente(){
        Cliente cliente =  new Cliente();
        cliente.setCpf(cpf);
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setSenha(senha);
        return cliente;
    }
}
