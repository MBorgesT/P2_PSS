
package com.mycompany.p2_pss.presenter.menu_principal;

import com.mycompany.p2_pss.presenter.usuario.ListaUsuariosPresenter;
import com.mycompany.p2_pss.view.MenuPrincipalView;
import java.awt.event.ActionEvent;

public class MenuPrincipalAdministradorPresenter extends MenuPrincipalAbstractPresenter {
    
    private MenuPrincipalAdministradorPresenter() {
        this.view = new MenuPrincipalView();
        
        configFecharTela();
        configMenuItemManterUsuarios();
        
        view.setVisible(true);
    }
    
     public static MenuPrincipalAdministradorPresenter getInstancia() {
        if (instancia == null) {
            instancia = new MenuPrincipalAdministradorPresenter();
        }
        return (MenuPrincipalAdministradorPresenter)instancia;
    }
     
    private void configMenuItemManterUsuarios() {
        view.getMenuItemManterUsuarios().addActionListener((ActionEvent e) -> {
            ListaUsuariosPresenter listaUsuarios = ListaUsuariosPresenter.getInstancia();
        });
    }
    
}
