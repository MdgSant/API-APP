package br.com.opala.EstudeX.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class LoginRequest {
    private String email;
    private String senha;

    public String getEmail() { return email; }
    public String getSenha() { return senha; }
}
