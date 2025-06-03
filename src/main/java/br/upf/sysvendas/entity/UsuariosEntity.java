package br.upf.sysvendas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class UsuariosEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nome")
    private String nome;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email", unique = true)
    private String email;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "senha")
    private String senha;

    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "data_cadastro")
    private Date dataCadastro;

    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datahoraReg")
    private Date datahoraReg;

    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private Boolean ativo;

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public Date getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(Date dataCadastro) { this.dataCadastro = dataCadastro; }

    public Date getDatahoraReg() { return datahoraReg; }
    public void setDatahoraReg(Date datahoraReg) { this.datahoraReg = datahoraReg; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final UsuariosEntity other = (UsuariosEntity) obj;
        return Objects.equals(this.id, other.id);
    }
}
