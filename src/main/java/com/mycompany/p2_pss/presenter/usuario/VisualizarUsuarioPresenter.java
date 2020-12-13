
package com.mycompany.p2_pss.presenter.usuario;

import com.mycompany.p2_pss.model.Usuario;
import com.mycompany.p2_pss.view.usuario.VisualizarUsuarioView;

public class VisualizarUsuarioPresenter {
    
    private VisualizarUsuarioView view;
    private static VisualizarUsuarioPresenter instancia;
    
    private VisualizarUsuarioPresenter() {
        view = new VisualizarUsuarioView();
        
        
        
        view.setVisible(true);
    }
    
    public VisualizarUsuarioPresenter getInstancia() {
        if (instancia == null) {
            instancia = new VisualizarUsuarioPresenter();
        }
        return instancia;
    }
    
    public void setUsuario(Usuario usuario) {
        view.getCampoNomeUsuario().setText(usuario.getNomeUsuario());
        view.getCampoTipo().setText(usuario.isAdministrador() ? "Administrador" : "Comum");
    }
    
}
