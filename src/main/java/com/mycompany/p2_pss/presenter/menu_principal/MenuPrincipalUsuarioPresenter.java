
package com.mycompany.p2_pss.presenter.menu_principal;

import com.mycompany.p2_pss.view.MenuPrincipalView;

public class MenuPrincipalUsuarioPresenter extends MenuPrincipalAbstractPresenter {
    
    private MenuPrincipalUsuarioPresenter() {
        this.view = new MenuPrincipalView();
        
        view.setVisible(true);
    }
    
    public static MenuPrincipalUsuarioPresenter getInstancia() {
        if (instancia == null) {
            instancia = new MenuPrincipalUsuarioPresenter();
        }
        return (MenuPrincipalUsuarioPresenter)instancia;
    }
    
}
