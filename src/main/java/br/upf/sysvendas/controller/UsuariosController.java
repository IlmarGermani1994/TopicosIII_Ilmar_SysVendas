package br.upf.sysvendas.controller;

import br.upf.sysvendas.entity.UsuariosEntity;
import br.upf.sysvendas.facade.UsuariosFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named(value = "usuariosController")
@SessionScoped
public class UsuariosController implements Serializable {

    @EJB
    private UsuariosFacade ejbFacade;

    private UsuariosEntity usuario;
    private UsuariosEntity selected;

    /**
     * Lista de usuários obtida do banco.
     */
    public List<UsuariosEntity> getUsuarioList() {
        return ejbFacade.buscarTodos();
    }

    public UsuariosEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuariosEntity usuario) {
        this.usuario = usuario;
    }

    public UsuariosEntity getSelected() {
        return selected;
    }

    public void setSelected(UsuariosEntity selected) {
        this.selected = selected;
    }

    /**
     * Método utilizado para preparar um novo usuário.
     * @return 
     */
    public UsuariosEntity prepareAdicionar() {
        usuario = new UsuariosEntity();
        return usuario;
    }

    /**
     * Adiciona um novo usuário no banco.
     */
    public void adicionarUsuario() {
        usuario.setDataCadastro(new Date());
        usuario.setDatahoraReg(new Date());
        persist(PersistAction.CREATE, "Registro incluído com sucesso!");
        usuario = new UsuariosEntity();
    }

    /**
     * Atualiza o usuário selecionado.
     */
    public void editarUsuario() {
        persist(PersistAction.UPDATE, "Registro alterado com sucesso!");
        selected = null;
    }

    /**
     * Exclui o usuário selecionado.
     */
    public void excluirUsuario() {
        persist(PersistAction.DELETE, "Registro excluído com sucesso!");
        selected = null;
    }

    /**
     * Executa a ação de persistência definida.
     * @param persistAction Tipo da ação (CREATE, UPDATE, DELETE)
     * @param successMessage Mensagem de sucesso
     */
    private void persist(PersistAction persistAction, String successMessage) {
        try {
            if (persistAction != null) {
                switch (persistAction) {
                    case CREATE:
                        ejbFacade.createReturn(usuario);
                        break;
                    case UPDATE:
                        ejbFacade.edit(selected);
                        break;
                    case DELETE:
                        ejbFacade.remove(selected);
                        break;
                }
                addSuccessMessage(successMessage);
            }
        } catch (Exception ex) {
            String msg = (ex.getCause() != null) ? ex.getCause().getLocalizedMessage() : ex.getLocalizedMessage();
            if (msg != null && !msg.isBlank()) {
                addErrorMessage(msg);
            } else {
                addErrorMessage("Erro na persistência!");
            }
        }
    }

    /**
     * Exibe uma mensagem de erro.
     * @param msg Mensagem
     */
    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    /**
     * Exibe uma mensagem de sucesso.
     * @param msg Mensagem
     */
    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    /**
     * Enum interna usada para definir tipo de operação persistente.
     */
    public static enum PersistAction {
        CREATE,
        DELETE,
        UPDATE
    }
}
