package br.com.opala.EstudeX.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_UTILIZADOR")
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilizador
{
    @Id
    @Column(name = "idUtilizador")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Nome")
    private String nome;
    @Column(name = "Cpf", columnDefinition = "CHAR(14)")
    private String cpf;
    @Column(name = "UF", length = 2, columnDefinition = "CHAR(2)")
    private String uf;
    @Column(name = "Cidade")
    private String cidade;
    @Column(name = "Foto")
    private Boolean foto;
    @Column(name = "SenhaHash", length = 25)
    private String senha;


    @ManyToOne
    @JoinColumn(name = "idTipoUtilizador")
    private TipoUtilizador tipoUtilizador;
}
