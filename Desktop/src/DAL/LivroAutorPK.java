/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Pedro
 */
@Embeddable
public class LivroAutorPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ID_LIVRO")
    private BigInteger idLivro;
    @Basic(optional = false)
    @Column(name = "ID_AUTOR")
    private BigInteger idAutor;

    public LivroAutorPK() {
    }

    public LivroAutorPK(BigInteger idLivro, BigInteger idAutor) {
        this.idLivro = idLivro;
        this.idAutor = idAutor;
    }

    public BigInteger getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(BigInteger idLivro) {
        this.idLivro = idLivro;
    }

    public BigInteger getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(BigInteger idAutor) {
        this.idAutor = idAutor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLivro != null ? idLivro.hashCode() : 0);
        hash += (idAutor != null ? idAutor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LivroAutorPK)) {
            return false;
        }
        LivroAutorPK other = (LivroAutorPK) object;
        if ((this.idLivro == null && other.idLivro != null) || (this.idLivro != null && !this.idLivro.equals(other.idLivro))) {
            return false;
        }
        if ((this.idAutor == null && other.idAutor != null) || (this.idAutor != null && !this.idAutor.equals(other.idAutor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.LivroAutorPK[ idLivro=" + idLivro + ", idAutor=" + idAutor + " ]";
    }
    
}
