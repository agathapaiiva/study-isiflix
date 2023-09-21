package br.com.isiflix.isibank.dto;

import br.com.isiflix.isibank.model.Client;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ClientDTO(@NotNull String name,
                        @NotNull @Email String email,
                        @NotNull String cpf,
                        @NotNull @Min(11) String phone,
                        @NotNull @Min(8) String password) {

    public Client toClient(){
        Client client =  new Client();
        client.setName(name);
        client.setEmail(email);
        client.setCpf(cpf);
        client.setPhone(phone);
        client.setPassword(password);
        return client;
    }
}
