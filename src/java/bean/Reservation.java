/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.Size;

/**
 *
 * @author asus
 */
@Entity
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Chambre chambre;

//    private boolean occupe;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date entreedate;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date sortiedate;
    private long prix;

    public Reservation() {

    }

    public Reservation(Long id) {
        this.id = id;
    }

    public Reservation(Long id, Chambre chambre, Date entreedate, Date sortiedate, long prix) {
        this.id = id;
        this.chambre = chambre;
        this.entreedate = entreedate;
        this.sortiedate = sortiedate;
        this.prix = prix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chambre getChambre() {
        if (chambre == null) {
            chambre = new Chambre();
        }
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public Date getEntreedate() {
        return entreedate;
    }

    public void setEntreedate(Date entreedate) {
        this.entreedate = entreedate;
    }

    public Date getSortiedate() {
        return sortiedate;
    }

    public void setSortiedate(Date sortiedate) {
        this.sortiedate = sortiedate;
    }

    public long getPrix() {
        return prix;
    }

    public void setPrix(long prix) {
        this.prix = prix;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", chambre=" + chambre + ", entreedate=" + entreedate + ", sortiedate=" + sortiedate + ", prix=" + prix + '}';
    }

    

}
