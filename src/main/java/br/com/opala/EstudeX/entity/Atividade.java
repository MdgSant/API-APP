package br.com.opala.EstudeX.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

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
    @JoinColumn(name = "idDisciplina")
    private Disciplina disciplina;

    @Transient
    @JsonProperty("idDisciplina")
    private Integer idDisciplina;

    @ManyToOne
    @JoinColumn(name = "idNivelDificuldade")
    private NivelDificuldade nivelDificuldade;

    @Transient
    @JsonProperty("idNivelDificuldade")
    private Integer idNivelDificuldade;

    @JsonIgnoreProperties("atividade")
    @OneToMany(mappedBy = "atividade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AtividadePergunta> perguntas;

}
