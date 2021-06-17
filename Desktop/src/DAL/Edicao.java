/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pedro
 */
@Entity
@Table(name = "EDICAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Edicao.findAll", query = "SELECT e FROM Edicao e")
    , @NamedQuery(name = "Edicao.findByIdEdicao", query = "SELECT e FROM Edicao e WHERE e.idEdicao = :idEdicao")
    , @NamedQuery(name = "Edicao.findByNome", query = "SELECT e FROM Edicao e WHERE e.nome = :nome")
    , @NamedQuery(name = "Edicao.findByQtd", query = "SELECT e FROM Edicao e WHERE e.qtd = :qtd")})
public class Edicao implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_EDICAO")
    private BigDecimal idEdicao;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @Basic(optional = false)
    @Column(name = "QTD")
    private BigInteger qtd;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "edicaoId")
    private List<ExemplarLivro> exemplarLivroList;

    public Edicao() {
    }

    public Edicao(BigDecimal idEdicao) {
        this.idEdicao = idEdicao;
    }
    
    public Edicao(BigDecimal idEdicao, String nome) {
        this.idEdicao = idEdicao;
        this.nome = nome;
    }

    public Edicao(BigDecimal idEdicao, String nome, BigInteger qtd) {
        this.idEdicao = idEdicao;
        this.nome = nome;
        this.qtd = qtd;
    }

    public BigDecimal getIdEdicao() {
        return idEdicao;
    }

    public void setIdEdicao(BigDecimal idEdicao) {
        this.idEdicao = idEdicao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigInteger getQtd() {
        return qtd;
    }

    public void setQtd(BigInteger qtd) {
        this.qtd = qtd;
    }

    @XmlTransient
    public List<ExemplarLivro> getExemplarLivroList() {
        return exemplarLivroList;
    }

    public void setExemplarLivroList(List<ExemplarLivro> exemplarLivroList) {
        this.exemplarLivroList = exemplarLivroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEdicao != null ? idEdicao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Edicao)) {
            return false;
        }
        Edicao other = (Edicao) object;
        if ((this.idEdicao == null && other.idEdicao != null) || (this.idEdicao != null && !this.idEdicao.equals(other.idEdicao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
    
}
