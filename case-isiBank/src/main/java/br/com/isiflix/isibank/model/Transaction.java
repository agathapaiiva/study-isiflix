package br.com.isiflix.isibank.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tbl_transacao")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_seq")
    private Long numberSeq;
    @Column(name = "dataHora", nullable = false)
    private LocalDateTime dateTime;
    @Column(name = "tipo")
    private Integer type;
    @Column(name = "valor")
    private Double value;
    @Column(name = "saldo_inicial")
    private Double initBalance;
    @Column(name = "saldo_final")
    private Double finalBalance;
    @Column(name = "numero_doc", length = 100)
    private String numberDoc;
    @Column(name = "descricao", length = 255)
    private String description;
    @ManyToOne
    @JoinColumn(name = "tbl_conta_numero_conta")
    private Account account;
}
