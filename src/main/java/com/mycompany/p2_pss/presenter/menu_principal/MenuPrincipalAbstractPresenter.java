package com.mycompany.p2_pss.presenter.menu_principal;

import com.mycompany.p2_pss.model.Usuario;
import com.mycompany.p2_pss.view.MenuPrincipalView;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class MenuPrincipalAbstractPresenter {

    protected MenuPrincipalView view;
    protected static MenuPrincipalAbstractPresenter instancia;
    protected Usuario usuarioLogado;

    public void setUsuario(Usuario usuario) throws Exception {
        if (instancia == null) {
            throw new Exception("Não há instância criada.");
        }

        this.usuarioLogado = usuario;

        view.getLabelNomeUsuario().setText(usuarioLogado.getNomeUsuario());
        view.getLabelTipoUsuario().setText(usuarioLogado.isAdministrador() ? "Administrador" : "Comum");

    }
    
    protected void configFecharTela() {
        this.view.addWindowListener(new WindowAdapter() {
            @SuppressWarnings("override")
            public void windowClosing(WindowEvent evt) {
                fecharTela();
            }
        });
    }
    
    protected void fecharTela() {
        instancia = null;
        view.dispose();
    }

}
