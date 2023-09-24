package br.com.isiflix.isibank.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_conta")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_conta")
    private Integer numberAccount;
    @Column(name = "numero_banco")
    private Integer numberBank;
    @Column(name = "numero_agencia")
    private Integer numberAgency;
    @Column(name = "saldo")
    private Double balance;
    @Column(name = "limite")
    private Double limit;
    @Column(name = "ativa")
    private Integer active;
    @ManyToOne
    @JoinColumn(name = "tbl_cliente_id_cliente")
    private Client client;

    @Override
    public String toString() {
        return "Conta [numeroConta=" + numberAccount + ", numeroBanco=" + numberBank + ", numeroAgencia=" + numberAgency
                + ", saldo=" + balance + ", limite=" + limit + ", ativa=" + active + ", cliente=" + client + "]";
    }
}
