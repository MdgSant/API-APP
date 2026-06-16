package br.com.opala.EstudeX.dto;

import br.com.opala.EstudeX.entity.NivelDificuldade;
import br.com.opala.EstudeX.entity.Disciplina;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
public class AtividadeResumoDTO
{
    private Integer idAtividade;
    private String titulo;
    private Timestamp dataCriacao;
    private Integer pontuacaoMaxima;
    private NivelDificuldade nivelDificuldade;
    private Disciplina disciplina;

    public AtividadeResumoDTO(Integer idAtividade, String titulo, Timestamp dataCriacao,
                              Integer pontuacaoMaxima, NivelDificuldade nivelDificuldade,
                              Disciplina disciplina)
    {
        this.idAtividade = idAtividade;
        this.titulo = titulo;
        this.dataCriacao = dataCriacao;
        this.pontuacaoMaxima = pontuacaoMaxima;
        this.nivelDificuldade = nivelDificuldade;
        this.disciplina = disciplina;
    }
}