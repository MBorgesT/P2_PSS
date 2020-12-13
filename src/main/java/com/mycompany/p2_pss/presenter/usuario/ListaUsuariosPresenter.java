package com.mycompany.p2_pss.presenter.usuario;

import com.mycompany.p2_pss.dao.UsuarioDAO;
import com.mycompany.p2_pss.model.Usuario;
import com.mycompany.p2_pss.view.usuario.ListaUsuariosView;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ListaUsuariosPresenter {

    private ListaUsuariosView view;
    private static ListaUsuariosPresenter instancia;

    private ListaUsuariosPresenter() {
        view = new ListaUsuariosView();

        configFecharTela();
        configBotaoNovo();

        preencherTabelaTodosUsuarios();

        view.setVisible(true);
    }

    public static ListaUsuariosPresenter getInstancia() {
        if (instancia == null) {
            instancia = new ListaUsuariosPresenter();
        }
        return instancia;
    }

    private void configFecharTela() {
        this.view.addWindowListener(new WindowAdapter() {
            @SuppressWarnings("override")
            public void windowClosing(WindowEvent evt) {
                fecharTela();
            }
        });
    }
    
    private void configBotaoNovo() {
        view.getBotaoNovo().addActionListener((ActionEvent e) -> {
            NovoUsuarioPresenter.getInstancia();
        });
    }

    public void preencherTabelaTodosUsuarios() {
        ArrayList<Usuario> listaUsuarios = UsuarioDAO.getInstancia().buscar();

        DefaultTableModel model = (DefaultTableModel) view.getTabelaUsuarios().getModel();
        model.setRowCount(0);
        listaUsuarios.forEach(usuario -> model.addRow(usuario.getObjectArray()));
    }

    private void fecharTela() {
        instancia = null;
        view.dispose();
    }

}
