package br.upf.sysvendas.controller;

import br.upf.sysvendas.entity.UsuariosEntity;
import br.upf.sysvendas.facade.UsuariosFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;

@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private UsuariosEntity usuario;

    @EJB
    private UsuariosFacade ejbFacade;

    public LoginController() {
    }

    @PostConstruct
    public void init() {
        prepareAutenticarUsuario();
    }

    /**
     * Método para inicializar o objeto de autenticação
     */
    public void prepareAutenticarUsuario() {
        usuario = new UsuariosEntity();
    }

    public UsuariosEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuariosEntity usuario) {
        this.usuario = usuario;
    }

    /**
     * Método utilizado para validar login e senha.
     * @return redirecionamento ou null
     */
    public String validarLogin() {
        UsuariosEntity usuarioDB = ejbFacade.buscarPorEmail(usuario.getEmail(), usuario.getSenha());

        if (usuarioDB != null && usuarioDB.getId() != null) {
            return "/usuarios.xhtml?faces-redirect=true";
        } else {
            FacesMessage fm = new FacesMessage(
                FacesMessage.SEVERITY_ERROR,
                "Falha no Login!",
                "Email ou senha incorreto!");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return null;
        }
    }
}
