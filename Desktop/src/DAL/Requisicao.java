/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "REQUISICAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Requisicao.findAll", query = "SELECT r FROM Requisicao r")
    , @NamedQuery(name = "Requisicao.findByIdRequisicao", query = "SELECT r FROM Requisicao r WHERE r.idRequisicao = :idRequisicao")
    , @NamedQuery(name = "Requisicao.findByTempReq", query = "SELECT r FROM Requisicao r WHERE r.tempReq = :tempReq")
    , @NamedQuery(name = "Requisicao.findByDataReq", query = "SELECT r FROM Requisicao r WHERE r.dataReq = :dataReq")
    , @NamedQuery(name = "Requisicao.findByDataPrevEntrega", query = "SELECT r FROM Requisicao r WHERE r.dataPrevEntrega = :dataPrevEntrega")})
@SequenceGenerator(name="requisicao_seq_pk", sequenceName = "requisicao_seq_pk", allocationSize = 1, initialValue = 1)
public class Requisicao implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REQUISICAO")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="requisicao_seq_pk")
    private BigDecimal idRequisicao;
    @Basic(optional = false)
    @Column(name = "TEMP_REQ")
    private long tempReq;
    @Basic(optional = false)
    @Column(name = "DATA_REQ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataReq;
    @Basic(optional = false)
    @Column(name = "DATA_PREV_ENTREGA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPrevEntrega;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requisicaoId")
    private List<Entrega> entregaList;
    @JoinColumn(name = "ENTREGA_ID", referencedColumnName = "ID_ENTREGA")
    @ManyToOne
    private Entrega entregaId;
    @JoinColumn(name = "EXEMPLAR_ID", referencedColumnName = "ID_EXEMPLAR")
    @ManyToOne(optional = false)
    private ExemplarLivro exemplarId;
    @JoinColumn(name = "LEITOR_ID", referencedColumnName = "ID_LEITOR")
    @ManyToOne(optional = false)
    private Leitor leitorId;

    public Requisicao() {
    }

    public Requisicao(BigDecimal idRequisicao) {
        this.idRequisicao = idRequisicao;
    }

    public Requisicao(BigDecimal idRequisicao, long tempReq, Date dataReq, Date dataPrevEntrega) {
        this.idRequisicao = idRequisicao;
        this.tempReq = tempReq;
        this.dataReq = dataReq;
        this.dataPrevEntrega = dataPrevEntrega;
    }
    
    public Requisicao(BigDecimal idRequisicao, Leitor leitorId, ExemplarLivro exemplarId, Date dataReq, long tempReq, Date dataPrevEntrega, Entrega entregaId) {
        this.idRequisicao = idRequisicao;
        this.leitorId = leitorId;
        this.exemplarId = exemplarId;
        this.dataReq = dataReq;
        this.tempReq = tempReq;
        this.dataPrevEntrega = dataPrevEntrega;
        this.entregaId = entregaId;
    }
    
    public Requisicao(Leitor leitorId, ExemplarLivro exemplarId, Date dataReq, long tempReq, Date dataPrevEntrega, Entrega entregaId) {
        this.leitorId = leitorId;
        this.exemplarId = exemplarId;
        this.dataReq = dataReq;
        this.tempReq = tempReq;
        this.dataPrevEntrega = dataPrevEntrega;
        this.entregaId = entregaId;
    }

    public BigDecimal getIdRequisicao() {
        return idRequisicao;
    }

    public void setIdRequisicao(BigDecimal idRequisicao) {
        this.idRequisicao = idRequisicao;
    }

    public long getTempReq() {
        return tempReq;
    }

    public void setTempReq(long tempReq) {
        this.tempReq = tempReq;
    }

    public Date getDataReq() {
        return dataReq;
    }

    public void setDataReq(Date dataReq) {
        this.dataReq = dataReq;
    }

    public Date getDataPrevEntrega() {
        return dataPrevEntrega;
    }

    public void setDataPrevEntrega(Date dataPrevEntrega) {
        this.dataPrevEntrega = dataPrevEntrega;
    }

    @XmlTransient
    public List<Entrega> getEntregaList() {
        return entregaList;
    }

    public void setEntregaList(List<Entrega> entregaList) {
        this.entregaList = entregaList;
    }
    
    public Entrega getEntregaId() {
        return entregaId;
    }

    public void setEntregaId(Entrega entregaId) {
        this.entregaId = entregaId;
    }

    public ExemplarLivro getExemplarId() {
        return exemplarId;
    }

    public void setExemplarId(ExemplarLivro exemplarId) {
        this.exemplarId = exemplarId;
    }

    public Leitor getLeitorId() {
        return leitorId;
    }

    public void setLeitorId(Leitor leitorId) {
        this.leitorId = leitorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRequisicao != null ? idRequisicao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Requisicao)) {
            return false;
        }
        Requisicao other = (Requisicao) object;
        if ((this.idRequisicao == null && other.idRequisicao != null) || (this.idRequisicao != null && !this.idRequisicao.equals(other.idRequisicao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Requisicao[ idRequisicao=" + idRequisicao + " ]";
    }
    
}
