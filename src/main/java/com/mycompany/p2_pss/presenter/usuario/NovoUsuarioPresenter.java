package com.mycompany.p2_pss.presenter.usuario;

import com.mycompany.p2_pss.dao.UsuarioDAO;
import com.mycompany.p2_pss.model.Usuario;
import static com.mycompany.p2_pss.presenter.usuario.UsuarioAbstractPresenter.instancia;
import com.mycompany.p2_pss.view.usuario.ManterUsuarioView;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public final class NovoUsuarioPresenter extends UsuarioAbstractPresenter {

    private NovoUsuarioPresenter() {
        this.view = new ManterUsuarioView();

        configFecharTela();
        configBotaoCancelar();
        configBotaoConfirmar();
        
        view.getBotaoConfirmar().setText("Cadastrar");

        this.view.setVisible(true);
    }

    public static NovoUsuarioPresenter getInstancia() {
        if (instancia == null) {
            instancia = new NovoUsuarioPresenter();
        }
        return (NovoUsuarioPresenter) instancia;
    }

    @Override
    protected void configBotaoConfirmar() {
         view.getBotaoConfirmar().addActionListener((ActionEvent e) -> {

            if (validarCampos()) {
                
                String nomeUsuario = view.getCampoNomeUsuario().getText();
                
                if (UsuarioDAO.getInstancia().existeUsuarioCadastado(nomeUsuario)) {
                    
                    setMensagem("Este nome de usuário já está cadastrado.");
                    
                } else {
                    
                    UsuarioDAO.getInstancia().salvar(new Usuario(
                            nomeUsuario,
                            String.valueOf(view.getCampoSenha().getPassword()),
                            view.getCheckBoxAdministrador().isSelected()
                    ));
                    
                    ListaUsuariosPresenter.getInstancia().preencherTabelaTodosUsuarios();
                    JOptionPane.showMessageDialog(view, "Usuário cadastrado com sucesso.");
                    fecharTela();
                    
                }
                
            }

        });
    }

}
