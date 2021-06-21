/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pedro
 */
@Entity
@Table(name = "LIVRO_AUTOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LivroAutor.findAll", query = "SELECT l FROM LivroAutor l")
    , @NamedQuery(name = "LivroAutor.findByIdLivro", query = "SELECT l FROM LivroAutor l WHERE l.livroAutorPK.idLivro = :idLivro")
    , @NamedQuery(name = "LivroAutor.findByIdAutor", query = "SELECT l FROM LivroAutor l WHERE l.livroAutorPK.idAutor = :idAutor")})
public class LivroAutor implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LivroAutorPK livroAutorPK;
    @JoinColumn(name = "ID_AUTOR", referencedColumnName = "ID_AUTOR", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Autor autor;

    public LivroAutor() {
    }

    public LivroAutor(LivroAutorPK livroAutorPK) {
        this.livroAutorPK = livroAutorPK;
    }

    public LivroAutor(BigInteger idLivro, BigInteger idAutor) {
        this.livroAutorPK = new LivroAutorPK(idLivro, idAutor);
    }

    public LivroAutorPK getLivroAutorPK() {
        return livroAutorPK;
    }

    public void setLivroAutorPK(LivroAutorPK livroAutorPK) {
        this.livroAutorPK = livroAutorPK;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (livroAutorPK != null ? livroAutorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LivroAutor)) {
            return false;
        }
        LivroAutor other = (LivroAutor) object;
        if ((this.livroAutorPK == null && other.livroAutorPK != null) || (this.livroAutorPK != null && !this.livroAutorPK.equals(other.livroAutorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAL.LivroAutor[ livroAutorPK=" + livroAutorPK + " ]";
    }
    
}
