package br.com.opala.EstudeX.dto;

public class LoginResponse {
    private Integer idUtilizador;
    private String nome;
    private String token;
    private Integer tipoUtilizador;

    public LoginResponse(Integer idUtilizador, String nome, String token, Integer tipoUtilizador) {
        this.idUtilizador = idUtilizador;
        this.nome = nome;
        this.token = token;
        this.tipoUtilizador = tipoUtilizador;
    }

    public Integer getIdUtilizador() { return idUtilizador; }
    public String getNome() { return nome; }
    public String getToken() { return token; }
    public Integer getTipoUtilizador() { return tipoUtilizador; }
}
