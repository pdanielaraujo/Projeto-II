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
@Table(name = "UTILIZADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilizador.findAll", query = "SELECT u FROM Utilizador u")
    , @NamedQuery(name = "Utilizador.findByIdUtilizador", query = "SELECT u FROM Utilizador u WHERE u.idUtilizador = :idUtilizador")
    , @NamedQuery(name = "Utilizador.findByUsername", query = "SELECT u FROM Utilizador u WHERE u.username = :username")
    , @NamedQuery(name = "Utilizador.findByEmail", query = "SELECT u FROM Utilizador u WHERE u.email = :email")
    , @NamedQuery(name = "Utilizador.findByPassword", query = "SELECT u FROM Utilizador u WHERE u.password = :password")})
@SequenceGenerator(name="utilizador_seq_pk", sequenceName = "utilizador_seq_pk", allocationSize = 1, initialValue = 1)
public class Utilizador implements Serializable {

    @Column(name = "TIPO_UTILIZADOR")
    private Integer tipoUtilizador;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_UTILIZADOR")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="utilizador_seq_pk")
    private BigDecimal idUtilizador;
    @Basic(optional = false)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilizadorId")
    private List<Leitor> leitorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilizadorId")
    private List<Bibliotecario> bibliotecarioList;

    public Utilizador() {
    }

    public Utilizador(BigDecimal idUtilizador) {
        this.idUtilizador = idUtilizador;
    }

    public Utilizador(BigDecimal idUtilizador, String username, String email, String password) {
        this.idUtilizador = idUtilizador;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public BigDecimal getIdUtilizador() {
        return idUtilizador;
    }

    public void setIdUtilizador(BigDecimal idUtilizador) {
        this.idUtilizador = idUtilizador;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public List<Leitor> getLeitorList() {
        return leitorList;
    }

    public void setLeitorList(List<Leitor> leitorList) {
        this.leitorList = leitorList;
    }

    @XmlTransient
    public List<Bibliotecario> getBibliotecarioList() {
        return bibliotecarioList;
    }

    public void setBibliotecarioList(List<Bibliotecario> bibliotecarioList) {
        this.bibliotecarioList = bibliotecarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUtilizador != null ? idUtilizador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilizador)) {
            return false;
        }
        Utilizador other = (Utilizador) object;
        if ((this.idUtilizador == null && other.idUtilizador != null) || (this.idUtilizador != null && !this.idUtilizador.equals(other.idUtilizador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Utilizador[ idUtilizador=" + idUtilizador + " ]";
    }

    public Integer getTipoUtilizador() {
        return tipoUtilizador;
    }

    public void setTipoUtilizador(Integer tipoUtilizador) {
        this.tipoUtilizador = tipoUtilizador;
    }
    
}
