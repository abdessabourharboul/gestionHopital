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
import javax.validation.constraints.Future;

/**
 *
 * @author asus
 */
@Entity
public class Appointement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String objet;

//    @Future
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date rendezvous;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Medecin medecin;

    public Appointement() {
    }

    public Appointement(Long id) {
        this.id = id;
    }

    public Appointement(Long id, String objet, Date rendezvous, Client client, Medecin medecin) {
        this.id = id;
        this.objet = objet;
        this.rendezvous = rendezvous;
        this.client = client;
        this.medecin = medecin;
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

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
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

    public Date getRendezvous() {
        return rendezvous;
    }

    public void setRendezvous(Date rendezvous) {
        this.rendezvous = rendezvous;
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
        if (!(object instanceof Appointement)) {
            return false;
        }
        Appointement other = (Appointement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Appointement[ id=" + id + " ]";
    }

}
