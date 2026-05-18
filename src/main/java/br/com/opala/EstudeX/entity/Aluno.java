package br.com.opala.EstudeX.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_ALUNO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Aluno extends Utilizador
{
    // ❌ Remova o @Id daqui — o ID já vem do Utilizador pai
    // private Integer id;

    @Column(name = "xp")
    private Integer xp;

    @ManyToOne
    @JoinColumn(name = "idSerie")
    @JsonIgnoreProperties({"alunos", "comunicados"})
    private Serie serie;
}