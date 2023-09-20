package br.com.isiflix.isibank.model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "tbl_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int idCliente;
    @Column(name = "nome_cliente", nullable = false, length = 150)
    private String nome;
    @Column(name = "cpf_cliente", nullable = false, unique = true, length = 11)
    private String cpf;
    @Column(name = "email_cliente", nullable = false, unique = true, length = 10)
    private String email;
    @Column(name = "telefone_cliente", nullable = false, unique = true, length = 20)
    private String telefone;
    @Column(name = "senha_cliente", nullable = false, unique = true, length = 255)
    private String senha;
}
