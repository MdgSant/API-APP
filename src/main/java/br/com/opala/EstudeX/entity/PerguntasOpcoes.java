package br.com.opala.EstudeX.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_PERGUNTASOPCOES")
public class PerguntasOpcoes
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOpcao")
    private Integer idOpcao;

    @Column(name = "Descricao")
    private String descricao;

    @Column(name = "Correta")
    private boolean correta;

    @ManyToOne
    @JoinColumn(name = "idPergunta")
    private AtividadePergunta atividadePergunta;
}