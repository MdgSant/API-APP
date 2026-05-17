package br.com.opala.EstudeX.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "TBL_ATIVIDADE")
public class Atividade
{
    @Id
    @Column(name = "idAtividade")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAtividade;

    @Column(name = "Titulo")
    private String titulo;

    @Column(name = "DataCriacao")
    private Timestamp dataCriacao;

    @Column(name = "PontuacaoMaxima")
    private Integer pontuacaoMaxima;

    @Column(name = "idOrientador") // liga com a coluna do banco
    private Integer idOrientador; // guarda o ID do professor

    @ManyToOne
    @JoinColumn(name = "idNivelDificuldade")
    private NivelDificuldade nivelDificuldade;

}
