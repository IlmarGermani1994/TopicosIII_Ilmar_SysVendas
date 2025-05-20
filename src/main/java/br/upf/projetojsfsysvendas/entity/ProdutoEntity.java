package br.upf.projetojsfprimefaces.entity;

import br.upf.projetojsfsysvendas.entity.CategoriaEntity;
import br.upf.projetojsfsysvendas.entity.UnidadeEntity;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class ProdutoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String codigo;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(name = "valor_compra", nullable = false)
    private BigDecimal valorCompra;

    @Column(name = "valor_venda", nullable = false)
    private BigDecimal valorVenda;

    @Column(length = 10, nullable = false)
    private String unidade;

    @Column(name = "estoque_atual")
    private BigDecimal estoqueAtual;

    @Column(nullable = false)
    private Boolean ativo = true;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaEntity categoria;

    @ManyToOne
    @JoinColumn(name = "unidade_id", nullable = false)
    private UnidadeEntity unidadeObj;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public BigDecimal getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(BigDecimal estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public UnidadeEntity getUnidadeObj() {
        return unidadeObj;
    }

    public void setUnidadeObj(UnidadeEntity unidadeObj) {
        this.unidadeObj = unidadeObj;
    }
}