package br.com.opala.EstudeX.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "TBL_ATIVIDADERESPOSTA")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AtividadeResposta
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAtividadeAluno")
    private Integer idAtividadeAluno;

    @ManyToOne
    @JoinColumn(name = "idAluno")
    @JsonIgnoreProperties({"serie", "senha", "cpf", "foto"})
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "idAtividade")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Atividade atividade;

    @Column(name = "momentoInicio")
    private Timestamp momentoInicio;

    @Column(name = "momentoFim")
    private Timestamp momentoFim;

    @Column(name = "pontuacao")
    private Float pontuacao;
}
