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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pedro
 */
@Entity
@Table(name = "EXEMPLAR_LIVRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExemplarLivro.findAll", query = "SELECT e FROM ExemplarLivro e")
    , @NamedQuery(name = "ExemplarLivro.findByIdExemplar", query = "SELECT e FROM ExemplarLivro e WHERE e.idExemplar = :idExemplar")
    , @NamedQuery(name = "ExemplarLivro.findByNumPaginas", query = "SELECT e FROM ExemplarLivro e WHERE e.numPaginas = :numPaginas")
    , @NamedQuery(name = "ExemplarLivro.updateEstado", query = "UPDATE ExemplarLivro e SET e.estadoId = :estadoId WHERE e.idExemplar = :idExemplar")})
@SequenceGenerator(name="exemplarLivro_seq_pk", sequenceName = "exemplarLivro_seq_pk", allocationSize = 1, initialValue = 1)
public class ExemplarLivro implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_EXEMPLAR")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="exemplarLivro_seq_pk")
    private BigDecimal idExemplar;
    @Basic(optional = false)
    @Column(name = "NUM_PAGINAS")
    private Integer numPaginas;
    @JoinColumn(name = "EDICAO_ID", referencedColumnName = "ID_EDICAO")
    @ManyToOne(optional = false)
    private Edicao edicaoId;
    @JoinColumn(name = "ESTADO_ID", referencedColumnName = "ID_ESTADO")
    @ManyToOne(optional = false)
    private Estado estadoId;
    @JoinColumn(name = "LINGUA_ID", referencedColumnName = "ID_LINGUA")
    @ManyToOne(optional = false)
    private Lingua linguaId;
    @JoinColumn(name = "LIVRO_ID", referencedColumnName = "ID_LIVRO")
    @ManyToOne(optional = false)
    private Livro livroId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exemplarId")
    private List<Requisicao> requisicaoList;

    public ExemplarLivro() {
    }

    public ExemplarLivro(BigDecimal idExemplar) {
        this.idExemplar = idExemplar;
    }

    public ExemplarLivro(BigDecimal idExemplar, Integer numPaginas) {
        this.idExemplar = idExemplar;
        this.numPaginas = numPaginas;
    }
    
    public ExemplarLivro(BigDecimal idExemplar, Livro livroId, Integer numPaginas, Lingua linguaId, Edicao edicaoId, Estado estadoId) {
        this.idExemplar = idExemplar;
        this.livroId = livroId;
        this.numPaginas = numPaginas;
        this.linguaId = linguaId;
        this.edicaoId = edicaoId;
        this.estadoId = estadoId;
    }
    
    public ExemplarLivro(Livro livroId, Integer numPaginas, Lingua linguaId, Edicao edicaoId, Estado estadoId) {
        this.idExemplar = idExemplar;
        this.livroId = livroId;
        this.numPaginas = numPaginas;
        this.linguaId = linguaId;
        this.edicaoId = edicaoId;
        this.estadoId = estadoId;
    }

    public BigDecimal getIdExemplar() {
        return idExemplar;
    }

    public void setIdExemplar(BigDecimal idExemplar) {
        this.idExemplar = idExemplar;
    }

    public Integer getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(Integer numPaginas) {
        this.numPaginas = numPaginas;
    }

    public Edicao getEdicaoId() {
        return edicaoId;
    }

    public void setEdicaoId(Edicao edicaoId) {
        this.edicaoId = edicaoId;
    }

    public Estado getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Estado estadoId) {
        this.estadoId = estadoId;
    }

    public Lingua getLinguaId() {
        return linguaId;
    }

    public void setLinguaId(Lingua linguaId) {
        this.linguaId = linguaId;
    }

    public Livro getLivroId() {
        return livroId;
    }

    public void setLivroId(Livro livroId) {
        this.livroId = livroId;
    }

    @XmlTransient
    public List<Requisicao> getRequisicaoList() {
        return requisicaoList;
    }

    public void setRequisicaoList(List<Requisicao> requisicaoList) {
        this.requisicaoList = requisicaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExemplar != null ? idExemplar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExemplarLivro)) {
            return false;
        }
        ExemplarLivro other = (ExemplarLivro) object;
        if ((this.idExemplar == null && other.idExemplar != null) || (this.idExemplar != null && !this.idExemplar.equals(other.idExemplar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getLivroId().getTitulo();
    }
    
}
