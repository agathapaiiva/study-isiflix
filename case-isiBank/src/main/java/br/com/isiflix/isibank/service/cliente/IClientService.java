package br.com.isiflix.isibank.service.cliente;

import br.com.isiflix.isibank.dto.ClientDTO;

public interface IClientService {

    public Integer createClient(ClientDTO client);

    public Integer updateClient(ClientDTO client);

}
