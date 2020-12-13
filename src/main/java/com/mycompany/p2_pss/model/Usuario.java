
package com.mycompany.p2_pss.model;

public class Usuario {
    
    private int idUsuario;
    private String nomeUsuario;
    private String senha;
    private boolean administrador;

    public Usuario(int idUsuario, String nomeUsuario, String senha, boolean administrador) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.administrador = administrador;
    }

    public Usuario(String nomeUsuario, String senha, boolean administrador) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.administrador = administrador;
    }
    
    public Object[] getObjectArray() {
        return new Object[]{
            nomeUsuario,
            administrador ? "Administrador" : "Comum"
        };
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isAdministrador() {
        return administrador;
    }
    
}
