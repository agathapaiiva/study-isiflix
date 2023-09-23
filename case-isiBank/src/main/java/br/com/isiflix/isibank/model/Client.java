package br.com.isiflix.isibank.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_cliente")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int idClient;
    @Column(name = "nome_cliente", nullable = false, length = 150)
    private String name;
    @Column(name = "cpf_cliente", nullable = false, unique = true, length = 11)
    private String cpf;
    @Column(name = "email_cliente", nullable = false, unique = true, length = 100)
    private String email;
    @Column(name = "telefone_cliente", nullable = false, unique = true, length = 20)
    private String phone;
    @Column(name = "senha_cliente", nullable = false, length = 255)
    private String password;
}
