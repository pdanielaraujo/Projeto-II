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
@Table(name = "BIBLIOTECARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bibliotecario.findAll", query = "SELECT b FROM Bibliotecario b")
    , @NamedQuery(name = "Bibliotecario.findByIdBibliotecario", query = "SELECT b FROM Bibliotecario b WHERE b.idBibliotecario = :idBibliotecario")
    , @NamedQuery(name = "Bibliotecario.findByNome", query = "SELECT b FROM Bibliotecario b WHERE b.nome = :nome")
    , @NamedQuery(name = "Bibliotecario.findByDataNascimento", query = "SELECT b FROM Bibliotecario b WHERE b.dataNascimento = :dataNascimento")
    , @NamedQuery(name = "Bibliotecario.findByMorada", query = "SELECT b FROM Bibliotecario b WHERE b.morada = :morada")
    , @NamedQuery(name = "Bibliotecario.findByUtilizador", query = "SELECT b FROM Bibliotecario b INNER JOIN b.utilizadorId u WHERE u.idUtilizador = :idUtilizador")})
@SequenceGenerator(name="bibliotecario_seq_pk", sequenceName = "bibliotecario_seq_pk", allocationSize = 1, initialValue = 1)
public class Bibliotecario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_BIBLIOTECARIO")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="bibliotecario_seq_pk")
    private BigDecimal idBibliotecario;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bibliotecarioId")
    private List<Entrega> entregaList;
    @JoinColumn(name = "UTILIZADOR_ID", referencedColumnName = "ID_UTILIZADOR")
    @ManyToOne(optional = false)
    private Utilizador utilizadorId;

    public Bibliotecario() {
    }

    public Bibliotecario(BigDecimal idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    public Bibliotecario(BigDecimal idBibliotecario, String nome, Date dataNascimento, String morada) {
        this.idBibliotecario = idBibliotecario;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.morada = morada;
    }

    public BigDecimal getIdBibliotecario() {
        return idBibliotecario;
    }

    public void setIdBibliotecario(BigDecimal idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
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

    public Utilizador getUtilizadorId() {
        return utilizadorId;
    }

    public void setUtilizadorId(Utilizador utilizadorId) {
        this.utilizadorId = utilizadorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBibliotecario != null ? idBibliotecario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bibliotecario)) {
            return false;
        }
        Bibliotecario other = (Bibliotecario) object;
        if ((this.idBibliotecario == null && other.idBibliotecario != null) || (this.idBibliotecario != null && !this.idBibliotecario.equals(other.idBibliotecario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Bibliotecario[ idBibliotecario=" + idBibliotecario + " ]";
    }
    
}
