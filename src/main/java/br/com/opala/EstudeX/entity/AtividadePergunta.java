package br.com.opala.EstudeX.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_ATIVIDADEPERGUNTA")
public class AtividadePergunta
{
    @Id
    @Column(name = "idPergunta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPergunta;

    @Column(name = "Enunciado")
    private String enunciado;

    @ManyToOne
    @JoinColumn(name = "idAtividade")
    private Atividade atividade;

}
