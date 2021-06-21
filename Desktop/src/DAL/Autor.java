/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pedro
 */
@Entity
@Table(name = "AUTOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autor.findAll", query = "SELECT a FROM Autor a")
    , @NamedQuery(name = "Autor.findByIdAutor", query = "SELECT a FROM Autor a WHERE a.idAutor = :idAutor")
    , @NamedQuery(name = "Autor.findByNome", query = "SELECT a FROM Autor a WHERE a.nome = :nome")
    , @NamedQuery(name = "Autor.findByDataNascimento", query = "SELECT a FROM Autor a WHERE a.dataNascimento = :dataNascimento")})
public class Autor implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "autor")
    private List<LivroAutor> livroAutorList;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_AUTOR")
    private BigDecimal idAutor;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @Basic(optional = false)
    @Column(name = "DATA_NASCIMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNascimento;
    @ManyToMany(
            fetch = FetchType.LAZY,  
            cascade = {
                    CascadeType.PERSIST,        
                    CascadeType.MERGE
            },
            mappedBy = "autorList")
    private List<Livro> livroList;

    public Autor() {
    }

    public Autor(BigDecimal idAutor) {
        this.idAutor = idAutor;
    }

    public Autor(BigDecimal idAutor, String nome, Date dataNascimento) {
        this.idAutor = idAutor;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }
    
    public Autor(BigDecimal idAutor, String nome) {
        this.idAutor = idAutor;
        this.nome = nome;
    }
    
    public Autor(String nome) {
        this.nome = nome;
    }

    public BigDecimal getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(BigDecimal idAutor) {
        this.idAutor = idAutor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @XmlTransient
    public List<Livro> getLivroList() {
        return livroList;
    }

    public void setLivroList(List<Livro> livroList) {
        this.livroList = livroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAutor != null ? idAutor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autor)) {
            return false;
        }
        Autor other = (Autor) object;
        if ((this.idAutor == null && other.idAutor != null) || (this.idAutor != null && !this.idAutor.equals(other.idAutor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getNome();
    }

    @XmlTransient
    public List<LivroAutor> getLivroAutorList() {
        return livroAutorList;
    }

    public void setLivroAutorList(List<LivroAutor> livroAutorList) {
        this.livroAutorList = livroAutorList;
    }
    
}
