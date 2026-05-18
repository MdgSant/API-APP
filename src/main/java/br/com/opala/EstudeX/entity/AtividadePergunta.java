package br.com.opala.EstudeX.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @JsonIgnoreProperties("atividadePergunta")
    @OneToMany(mappedBy = "atividadePergunta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PerguntasOpcoes> opcoes;
}
