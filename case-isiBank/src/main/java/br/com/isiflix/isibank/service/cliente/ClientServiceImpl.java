package br.com.isiflix.isibank.service.cliente;

import br.com.isiflix.isibank.dto.ClientDTO;
import br.com.isiflix.isibank.model.Client;
import br.com.isiflix.isibank.repo.ClientRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private ClientRepo repo;

    @Override
    public Integer createClient(@Valid ClientDTO client) {
        Client newClient = repo.findByEmailOrCpfOrPhone(client.email(), client.cpf(), client.phone());

        if(newClient != null){
            return null;
        }
        return repo.save(client.toClient()).getIdClient();
    }

    @Override
    public Integer updateClient(ClientDTO updateClient) {
        return repo.save(updateClient.toClient()).getIdClient();
    }
}
