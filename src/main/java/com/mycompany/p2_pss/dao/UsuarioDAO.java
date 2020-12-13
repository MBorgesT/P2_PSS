package com.mycompany.p2_pss.dao;

import com.mycompany.p2_pss.model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO implements InterfaceDAO<Usuario> {

    private static UsuarioDAO instancia;

    private UsuarioDAO() {
    }

    public static UsuarioDAO getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioDAO();
        }
        return instancia;
    }

    @Override
    public void salvar(Usuario elem) {
        try {
            ConectorBanco conector = ConectorBanco.getInstancia();
            String query = "INSERT INTO Usuario(nomeUsuario, senha, administrador) VALUES (?, ?, ?)";
            PreparedStatement ps = conector.prepareStatement(query);

            ps.setString(1, elem.getNomeUsuario());
            ps.setString(2, elem.getSenha());
            ps.setBoolean(3, elem.isAdministrador());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void excluir(int id) {
        try {
            ConectorBanco conector = ConectorBanco.getInstancia();
            String query = "DELETE FROM Usuario WHERE idUsuario = ?";
            PreparedStatement ps = conector.prepareStatement(query);

            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void alterar(Usuario elem) {
        try {
            ConectorBanco conector = ConectorBanco.getInstancia();
            String query = "UPDATE Usuario SET nomeUsuario = ?, senha = ?, administrador = ? WHERE idUsuario = ?";
            PreparedStatement ps = conector.prepareStatement(query);

            ps.setString(1, elem.getNomeUsuario());
            ps.setString(2, elem.getSenha());
            ps.setBoolean(3, elem.isAdministrador());
            ps.setInt(4, elem.getIdUsuario());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Usuario buscar(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Usuario> buscar() {
        try {
            ConectorBanco conector = ConectorBanco.getInstancia();
            String query = "SELECT * FROM Usuario";
            Statement stmt = conector.getStatment();

            ResultSet rs = stmt.executeQuery(query);

            ArrayList<Usuario> listaUsuarios = new ArrayList<>();
            while (rs.next()) {
                listaUsuarios.add(new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("nomeUsuario"),
                        rs.getString("senha"),
                        rs.getBoolean("administrador")
                ));
            }

            return listaUsuarios;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Usuario buscar(String nomeUsuario, String senha) {
        try {
            ConectorBanco conector = ConectorBanco.getInstancia();
            String query = "SELECT * FROM Usuario WHERE nomeUsuario = ? AND senha = ?";
            PreparedStatement ps = conector.prepareStatement(query);

            ps.setString(1, nomeUsuario);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("nomeUsuario"),
                        rs.getString("senha"),
                        rs.getBoolean("administrador")
                );
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean existeUsuarioCadastrado() {
        try {
            ConectorBanco conector = ConectorBanco.getInstancia();
            String query = "SELECT idUsuario FROM Usuario";
            PreparedStatement ps = conector.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean existeUsuarioCadastado(String nomeUsuario) {
        try {
            ConectorBanco conector = ConectorBanco.getInstancia();
            String query = "SELECT * FROM Usuario WHERE nomeUsuario = ?";
            PreparedStatement ps = conector.prepareStatement(query);

            ps.setString(1, nomeUsuario);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
