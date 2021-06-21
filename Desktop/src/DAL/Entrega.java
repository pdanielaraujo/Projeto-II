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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pedro
 */
@Entity
@Table(name = "ENTREGA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entrega.findAll", query = "SELECT e FROM Entrega e")
    , @NamedQuery(name = "Entrega.findByIdEntrega", query = "SELECT e FROM Entrega e WHERE e.idEntrega = :idEntrega")
    , @NamedQuery(name = "Entrega.findByDataEntrega", query = "SELECT e FROM Entrega e WHERE e.dataEntrega = :dataEntrega")})
@SequenceGenerator(name="entrega_seq_pk", sequenceName = "entrega_seq_pk", allocationSize = 1, initialValue = 1)
public class Entrega implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ENTREGA")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="entrega_seq_pk")
    private BigDecimal idEntrega;
    @Basic(optional = false)
    @Column(name = "DATA_ENTREGA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntrega;
    @JoinColumn(name = "BIBLIOTECARIO_ID", referencedColumnName = "ID_BIBLIOTECARIO")
    @ManyToOne(optional = false)
    private Bibliotecario bibliotecarioId;
    @JoinColumn(name = "LEITOR_ID", referencedColumnName = "ID_LEITOR")
    @ManyToOne(optional = false)
    private Leitor leitorId;
    @JoinColumn(name = "REQUISICAO_ID", referencedColumnName = "ID_REQUISICAO")
    @ManyToOne(optional = false)
    private Requisicao requisicaoId;
    @OneToMany(mappedBy = "entregaId")
    private List<Requisicao> requisicaoList;

    public Entrega() {
    }

    public Entrega(BigDecimal idEntrega) {
        this.idEntrega = idEntrega;
    }

    public Entrega(BigDecimal idEntrega, Date dataEntrega) {
        this.idEntrega = idEntrega;
        this.dataEntrega = dataEntrega;
    }

    public BigDecimal getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(BigDecimal idEntrega) {
        this.idEntrega = idEntrega;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Bibliotecario getBibliotecarioId() {
        return bibliotecarioId;
    }

    public void setBibliotecarioId(Bibliotecario bibliotecarioId) {
        this.bibliotecarioId = bibliotecarioId;
    }

    public Leitor getLeitorId() {
        return leitorId;
    }

    public void setLeitorId(Leitor leitorId) {
        this.leitorId = leitorId;
    }

    public Requisicao getRequisicaoId() {
        return requisicaoId;
    }

    public void setRequisicaoId(Requisicao requisicaoId) {
        this.requisicaoId = requisicaoId;
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
        hash += (idEntrega != null ? idEntrega.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entrega)) {
            return false;
        }
        Entrega other = (Entrega) object;
        if ((this.idEntrega == null && other.idEntrega != null) || (this.idEntrega != null && !this.idEntrega.equals(other.idEntrega))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ID: " + idEntrega + " Entregue";
    }
    
}
