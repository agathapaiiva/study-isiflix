package br.com.isiflix.isibank.service.client;

import br.com.isiflix.isibank.dto.ClientDTO;

public interface IClientService {

    Integer createClient(ClientDTO client);

    Integer updateClient(ClientDTO client);

}
