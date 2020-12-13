package com.mycompany.p2_pss.presenter.usuario;

import com.mycompany.p2_pss.view.usuario.ManterUsuarioView;
import java.awt.event.ActionEvent;
import com.mycompany.p2_pss.dao.UsuarioDAO;
import com.mycompany.p2_pss.model.Usuario;
import com.mycompany.p2_pss.presenter.menu_principal.MenuPrincipalAdministradorPresenter;
import com.mycompany.p2_pss.presenter.menu_principal.MenuPrincipalUsuarioPresenter;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class LoginUsuarioPresenter extends UsuarioAbstractPresenter {

    private LoginUsuarioPresenter() {
        this.view = new ManterUsuarioView();

        configFecharTela();
        configBotaoCancelar();
        configBotaoConfirmar();

        view.getCheckBoxAdministrador().setVisible(false);
        view.getBotaoConfirmar().setText("Entrar");

        this.view.setVisible(true);
    }

    public static LoginUsuarioPresenter getInstancia() {
        if (instancia == null) {
            instancia = new LoginUsuarioPresenter();
        }
        return (LoginUsuarioPresenter) instancia;
    }

    @Override
    protected void configBotaoConfirmar() {
        view.getBotaoConfirmar().addActionListener((ActionEvent e) -> {

            if (validarCampos()) {

                String nomeUsuario = view.getCampoNomeUsuario().getText();
                String senha = String.valueOf(view.getCampoSenha().getPassword());

                UsuarioDAO dao = UsuarioDAO.getInstancia();
                if (dao.existeUsuarioCadastrado()) {

                    Usuario usuario = dao.buscar(nomeUsuario, senha);

                    if (usuario == null) {
                        setMensagem("Pelo menos um dos dados está incorreto.");
                    } else {
                        if (usuario.isAdministrador()) {

                            MenuPrincipalAdministradorPresenter menu = MenuPrincipalAdministradorPresenter.getInstancia();
                            try {
                                menu.setUsuario(usuario);
                            } catch (Exception ex) {
                                Logger.getLogger(LoginUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        } else {

                            MenuPrincipalUsuarioPresenter menu = MenuPrincipalUsuarioPresenter.getInstancia();
                            try {
                                menu.setUsuario(usuario);
                            } catch (Exception ex) {
                                Logger.getLogger(LoginUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                        fecharTela();
                    }

                } else {

                    Usuario usuario = new Usuario(
                            nomeUsuario,
                            senha,
                            true
                    );

                    dao.salvar(usuario);
                    MenuPrincipalUsuarioPresenter menu = MenuPrincipalUsuarioPresenter.getInstancia();

                    try {
                        menu.setUsuario(usuario);
                    } catch (Exception ex) {
                        Logger.getLogger(LoginUsuarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    fecharTela();

                }
                
            } else {

                setMensagem("Favor preencher todos os campos.");

            }

        });
    }

}
