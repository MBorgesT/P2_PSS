package com.mycompany.p2_pss.presenter.usuario;

import com.mycompany.p2_pss.view.usuario.ManterUsuarioView;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class UsuarioAbstractPresenter {

    protected ManterUsuarioView view;
    protected static UsuarioAbstractPresenter instancia;

    protected abstract void configBotaoConfirmar();

    protected void configBotaoCancelar() {
        view.getBotaoCancelar().addActionListener((ActionEvent e) -> {
            fecharTela();
        });
    }

    protected void configFecharTela() {
        this.view.addWindowListener(new WindowAdapter() {
            @SuppressWarnings("override")
            public void windowClosing(WindowEvent evt) {
                fecharTela();
            }
        });
    }

    protected boolean validarCampos() {
        String nomeUsuario = view.getCampoNomeUsuario().getText();
        String senha = String.valueOf(view.getCampoSenha().getPassword());

        if (nomeUsuario.isEmpty() || senha.isEmpty()) {
            setMensagem("Favor preencher todos os campos.");
            return false;
        } else {
            return true;
        }
    }

    protected void fecharTela() {
        instancia = null;
        view.dispose();
    }

    protected void setMensagem(String mensagem) {
        view.getLabelMensagem().setText(mensagem);
    }

}
