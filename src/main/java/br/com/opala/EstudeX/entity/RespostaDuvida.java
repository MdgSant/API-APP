package br.com.opala.EstudeX.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TBL_RESPOSTADUVIDA")
public class RespostaDuvida
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDuvida")
    private Integer idDuvida;

    @Column(name = "Momento")
    private LocalDateTime momento;

    @Column(name = "ConteudoResposta")
    private String ConteudoResposta;

    @Transient
    @JsonProperty("duvida")
    private Duvida duvida;

    @ManyToOne
    @JoinColumn(name = "idUtilizador")
    private Utilizador utilizador;

}
