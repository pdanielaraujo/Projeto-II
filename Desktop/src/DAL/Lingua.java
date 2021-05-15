/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "LINGUA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lingua.findAll", query = "SELECT l FROM Lingua l")
    , @NamedQuery(name = "Lingua.findByIdLingua", query = "SELECT l FROM Lingua l WHERE l.idLingua = :idLingua")
    , @NamedQuery(name = "Lingua.findByNome", query = "SELECT l FROM Lingua l WHERE l.nome = :nome")})
public class Lingua implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_LINGUA")
    private BigDecimal idLingua;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "linguaId")
    private List<ExemplarLivro> exemplarLivroList;

    public Lingua() {
    }

    public Lingua(BigDecimal idLingua) {
        this.idLingua = idLingua;
    }

    public Lingua(BigDecimal idLingua, String nome) {
        this.idLingua = idLingua;
        this.nome = nome;
    }

    public BigDecimal getIdLingua() {
        return idLingua;
    }

    public void setIdLingua(BigDecimal idLingua) {
        this.idLingua = idLingua;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        hash += (idLingua != null ? idLingua.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lingua)) {
            return false;
        }
        Lingua other = (Lingua) object;
        if ((this.idLingua == null && other.idLingua != null) || (this.idLingua != null && !this.idLingua.equals(other.idLingua))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
    
}
