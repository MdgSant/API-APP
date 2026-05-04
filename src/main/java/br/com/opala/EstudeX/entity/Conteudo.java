package br.com.opala.EstudeX.entity;

import br.com.opala.EstudeX.entity.Disciplina;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_CONTEUDO")
public class Conteudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConteudo")
    private Integer idConteudo;

    @Column(name = "Titulo")
    private String titulo;

    @Column(name = "Texto")
    private String texto;

    @ManyToOne
    @JoinColumn(name = "idDisciplina")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Disciplina disciplina;

    // ⚠️ NÃO deve ter List<Duvida> aqui — causaria loop
}