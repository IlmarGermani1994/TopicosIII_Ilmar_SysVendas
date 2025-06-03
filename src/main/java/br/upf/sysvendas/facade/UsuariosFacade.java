package br.upf.sysvendas.facade;

import br.upf.sysvendas.entity.UsuariosEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por realizar operações de persistência com a entidade UsuariosEntity.
 */
@Stateless // utilizados para outras chamadas de qualquer cliente.
public class UsuariosFacade extends AbstractFacade<UsuariosEntity> {

    @PersistenceContext(unitName = "SysVendasPU")
    private EntityManager em;

    private List<UsuariosEntity> entityList;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(UsuariosEntity.class);
    }

    /**
     * Método que retorna todos os usuários ordenados por nome
     */
    public List<UsuariosEntity> buscarTodos() {
        entityList = new ArrayList<>();
        try {
            Query query = getEntityManager().createQuery("SELECT u FROM UsuariosEntity u ORDER BY u.nome");
            if (!query.getResultList().isEmpty()) {
                entityList = (List<UsuariosEntity>) query.getResultList();
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }

        return entityList;
    }

    /**
     * Buscar um usuário pelo e-mail e senha
     * 
     * @param email
     * @param senha
     * @return usuário encontrado ou null
     */
    public UsuariosEntity buscarPorEmail(String email, String senha) {
        UsuariosEntity usuario = new UsuariosEntity();
        try {
            Query query = getEntityManager()
                    .createQuery("SELECT u FROM UsuariosEntity u WHERE u.email = :email AND u.senha = :senha");
            query.setParameter("email", email);
            query.setParameter("senha", senha);

            if (!query.getResultList().isEmpty()) {
                usuario = (UsuariosEntity) query.getSingleResult();
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }

        return usuario;
    }
}
