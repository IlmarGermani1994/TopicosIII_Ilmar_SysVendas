package br.upf.projetojsfsysvendas.controller;

import br.upf.projetojsfsysvendas.entity.CategoriaEntity;
import br.upf.projetojsfsysvendas.entity.UnidadeEntity;
import br.upf.projetojsfsysvendas.facade.ProdutoFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class ProdutoController implements Serializable {

    private ProdutoEntity objeto;
    private List<ProdutoEntity> lista;

    private List<CategoriaEntity> categorias;
    private List<UnidadeEntity> unidades;

    @EJB
    private ProdutoFacade dao;

    @EJB
    private br.upf.projetojfsysvendas.facade.CategoriaFacade categoriaFacade;

    @EJB
    private br.upf.projetojfsysvendas.facade.UnidadeFacade unidadeFacade;

    @PostConstruct
    public void init() {
        objeto = new ProdutoEntity();
        lista = dao.listarTodos();
        categorias = categoriaFacade.listarTodos();
        unidades = unidadeFacade.listarTodos();
    }

    public void salvar() {
        dao.salvar(objeto);
        listar();
        novo();
    }

    public void novo() {
        objeto = new ProdutoEntity();
    }

    public void editar(ProdutoEntity item) {
        this.objeto = item;
    }

    public void remover(ProdutoEntity item) {
        dao.remover(item);
        listar();
    }

    public void listar() {
        lista = dao.listarTodos();
    }

    // Getters e Setters

    public ProdutoEntity getObjeto() {
        return objeto;
    }

    public void setObjeto(ProdutoEntity objeto) {
        this.objeto = objeto;
    }

    public List<ProdutoEntity> getLista() {
        return lista;
    }

    public void setLista(List<ProdutoEntity> lista) {
        this.lista = lista;
    }

    public List<CategoriaEntity> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaEntity> categorias) {
        this.categorias = categorias;
    }

    public List<UnidadeEntity> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<UnidadeEntity> unidades) {
        this.unidades = unidades;
    }

    private static class ProdutoEntity {

        public ProdutoEntity() {
        }
    }
}