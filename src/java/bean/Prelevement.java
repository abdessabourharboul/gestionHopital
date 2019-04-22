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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author asus
 */
@Entity
public class Prelevement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Medecin medecin;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date prelevementDate;
    @OneToOne
    private PrelevementResultat prelevementResultat;

    public Prelevement() {
    }

    public Prelevement(Long id) {
        this.id = id;
    }

    public Prelevement(Long id, Client client, Medecin medecin, Date prelevementDate, PrelevementResultat prelevementResultat) {
        this.id = id;
        this.client = client;
        this.medecin = medecin;
        this.prelevementDate = prelevementDate;
        this.prelevementResultat = prelevementResultat;
    }

    

    public Client getClient() {
        if (client == null) {
            client = new Client();
        }
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Medecin getMedecin() {
        if (medecin == null) {
            medecin = new Medecin();
        }
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Date getPrelevementDate() {
        return prelevementDate;
    }

    public void setPrelevementDate(Date prelevementDate) {
        this.prelevementDate = prelevementDate;
    }

    public PrelevementResultat getPrelevementResultat() {
        if (prelevementResultat == null) {
            prelevementResultat = new PrelevementResultat();
        }
        return prelevementResultat;
    }

    public void setPrelevementResultat(PrelevementResultat prelevementResultat) {
        this.prelevementResultat = prelevementResultat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Prelevement)) {
            return false;
        }
        Prelevement other = (Prelevement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Prelevement[ id=" + id + " ]";
    }

}
