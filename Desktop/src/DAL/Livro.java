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
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "LIVRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Livro.findAll", query = "SELECT l FROM Livro l")
    , @NamedQuery(name = "Livro.findByIdLivro", query = "SELECT l FROM Livro l WHERE l.idLivro = :idLivro")
    , @NamedQuery(name = "Livro.findByTitulo", query = "SELECT l FROM Livro l WHERE l.titulo = :titulo")
    , @NamedQuery(name = "Livro.findByDataPublicacao", query = "SELECT l FROM Livro l WHERE l.dataPublicacao = :dataPublicacao")
    , @NamedQuery(name = "Livro.findByEditora", query = "SELECT l FROM Livro l WHERE l.editora = :editora")
    , @NamedQuery(name = "Livro.findByLinguaOficial", query = "SELECT l FROM Livro l WHERE l.linguaOficial = :linguaOficial")})
public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_LIVRO")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_pk")
    private BigDecimal idLivro;
    @Basic(optional = false)
    @Column(name = "TITULO")
    private String titulo;
    @Basic(optional = false)
    @Column(name = "DATA_PUBLICACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPublicacao;
    @Basic(optional = false)
    @Column(name = "EDITORA")
    private String editora;
    @Basic(optional = false)
    @Column(name = "LINGUA_OFICIAL")
    private String linguaOficial;
    @ManyToMany(mappedBy = "livroList")
    private List<Autor> autorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "livroId")
    private List<ExemplarLivro> exemplarLivroList;
    @JoinColumn(name = "GENERO_ID", referencedColumnName = "ID_GENERO")
    @ManyToOne(optional = false)
    private Genero generoId;

    public Livro() {
    }

    public Livro(BigDecimal idLivro) {
        this.idLivro = idLivro;
    }

    public Livro(BigDecimal idLivro, String titulo, Date dataPublicacao, String editora, String linguaOficial) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.dataPublicacao = dataPublicacao;
        this.editora = editora;
        this.linguaOficial = linguaOficial;
    }

    public BigDecimal getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(BigDecimal idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getLinguaOficial() {
        return linguaOficial;
    }

    public void setLinguaOficial(String linguaOficial) {
        this.linguaOficial = linguaOficial;
    }

    @XmlTransient
    public List<Autor> getAutorList() {
        return autorList;
    }

    public void setAutorList(List<Autor> autorList) {
        this.autorList = autorList;
    }

    @XmlTransient
    public List<ExemplarLivro> getExemplarLivroList() {
        return exemplarLivroList;
    }

    public void setExemplarLivroList(List<ExemplarLivro> exemplarLivroList) {
        this.exemplarLivroList = exemplarLivroList;
    }

    public Genero getGeneroId() {
        return generoId;
    }

    public void setGeneroId(Genero generoId) {
        this.generoId = generoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLivro != null ? idLivro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livro)) {
            return false;
        }
        Livro other = (Livro) object;
        if ((this.idLivro == null && other.idLivro != null) || (this.idLivro != null && !this.idLivro.equals(other.idLivro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Livro[ idLivro=" + idLivro + " ]";
    }
    
}
