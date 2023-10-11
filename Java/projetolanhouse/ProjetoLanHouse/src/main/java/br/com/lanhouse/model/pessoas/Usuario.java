package br.com.lanhouse.model.pessoas;

import br.com.lanhouse.model.e.ETipoCliente;

public abstract class Usuario {

    private int idUsuario;
    private String nome, login, senha;
    private ETipoCliente tipoCliente;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nome, String login, String senha, ETipoCliente tipoCliente) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.tipoCliente = tipoCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ETipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(ETipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
}
