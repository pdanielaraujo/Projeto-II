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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "LEITOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Leitor.findAll", query = "SELECT l FROM Leitor l")
    , @NamedQuery(name = "Leitor.findByIdLeitor", query = "SELECT l FROM Leitor l WHERE l.idLeitor = :idLeitor")
    , @NamedQuery(name = "Leitor.findByNome", query = "SELECT l FROM Leitor l WHERE l.nome = :nome")
    , @NamedQuery(name = "Leitor.findByDataNascimento", query = "SELECT l FROM Leitor l WHERE l.dataNascimento = :dataNascimento")
    , @NamedQuery(name = "Leitor.findByMorada", query = "SELECT l FROM Leitor l WHERE l.morada = :morada")})
public class Leitor implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_LEITOR")
    private BigDecimal idLeitor;
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    @Basic(optional = false)
    @Column(name = "DATA_NASCIMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNascimento;
    @Basic(optional = false)
    @Column(name = "MORADA")
    private String morada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "leitorId")
    private List<Entrega> entregaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "leitorId")
    private List<Requisicao> requisicaoList;
    @JoinColumn(name = "UTILIZADOR_ID", referencedColumnName = "ID_UTILIZADOR")
    @ManyToOne(optional = false)
    private Utilizador utilizadorId;

    public Leitor() {
    }

    public Leitor(BigDecimal idLeitor) {
        this.idLeitor = idLeitor;
    }

    public Leitor(BigDecimal idLeitor, String nome, Date dataNascimento, String morada) {
        this.idLeitor = idLeitor;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.morada = morada;
    }

    public BigDecimal getIdLeitor() {
        return idLeitor;
    }

    public void setIdLeitor(BigDecimal idLeitor) {
        this.idLeitor = idLeitor;
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

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    @XmlTransient
    public List<Entrega> getEntregaList() {
        return entregaList;
    }

    public void setEntregaList(List<Entrega> entregaList) {
        this.entregaList = entregaList;
    }

    @XmlTransient
    public List<Requisicao> getRequisicaoList() {
        return requisicaoList;
    }

    public void setRequisicaoList(List<Requisicao> requisicaoList) {
        this.requisicaoList = requisicaoList;
    }

    public Utilizador getUtilizadorId() {
        return utilizadorId;
    }

    public void setUtilizadorId(Utilizador utilizadorId) {
        this.utilizadorId = utilizadorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLeitor != null ? idLeitor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Leitor)) {
            return false;
        }
        Leitor other = (Leitor) object;
        if ((this.idLeitor == null && other.idLeitor != null) || (this.idLeitor != null && !this.idLeitor.equals(other.idLeitor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Leitor[ idLeitor=" + idLeitor + " ]";
    }
    
}
