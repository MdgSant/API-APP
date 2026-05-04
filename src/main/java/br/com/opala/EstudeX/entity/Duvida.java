package br.com.opala.EstudeX.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import br.com.opala.EstudeX.entity.Conteudo;

@Getter
@Setter
@Entity
@Table(name = "TBL_DUVIDA")
public class Duvida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDuvida")
    private Integer idDuvida;

    @Column(name = "Titulo")
    private String titulo;

    @Column(name = "Descricao")
    private String descricao;

    @Column(name = "Momento")
    private LocalDateTime momento;

    @Column(name = "StatusDuvida")
    private String statusDuvida;

    @ManyToOne
    @JoinColumn(name = "idUtilizador")
    @JsonIgnoreProperties({"tipoUtilizador", "senha", "foto",
            "hibernateLazyInitializer", "handler"})
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "idConteudo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Conteudo conteudo;
}