package br.com.opala.EstudeX.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_DISCIPLINA")
public class Disciplina
{
    @Id
    @Column(name = "idDisciplina")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDisciplina;

    @Column(name = "Nome")
    private String nome;

}
