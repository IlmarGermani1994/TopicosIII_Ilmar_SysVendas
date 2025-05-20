package br.upf.projetojsfsysvendas.facade;

import br.upf.projetojsfprimefaces.entity.ProdutoEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProdutoFacade {

    @PersistenceContext(unitName = "ConexaoPU")
    private EntityManager em;

    public void salvar(ProdutoEntity entity) {
        if (entity.getId() == null) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
    }

    public void remover(ProdutoEntity entity) {
        ProdutoEntity obj = em.find(ProdutoEntity.class, entity.getId());
        if (obj != null) {
            em.remove(obj);
        }
    }

    public ProdutoEntity buscarPorId(Long id) {
        return em.find(ProdutoEntity.class, id);
    }

    public List<ProdutoEntity> listarTodos() {
        return em.createQuery("FROM ProdutoEntity", ProdutoEntity.class).getResultList();
    }
}