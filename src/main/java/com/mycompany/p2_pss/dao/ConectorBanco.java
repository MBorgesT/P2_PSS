package com.mycompany.p2_pss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConectorBanco {

    private static final String url = "jdbc:sqlite:banco.db";
    private static ConectorBanco instancia = null;
    private static Connection conexao = null;
    private static Statement stmt = null;

    private ConectorBanco() throws SQLException {
        Connection con = DriverManager.getConnection(url);
        stmt = con.createStatement();
        conexao = con;
    }
    
    public static ConectorBanco getInstancia() throws SQLException {
        if (instancia == null) {
            instancia = new ConectorBanco();
        }
        return instancia;
    }

    public Connection getConexao() {
        return conexao;
    }
    
    public PreparedStatement prepareStatement(String query) throws SQLException {
        return conexao.prepareStatement(query);
    }

    public Statement getStatment() {
        return stmt;
    }

}
