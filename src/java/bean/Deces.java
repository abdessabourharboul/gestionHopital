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
public class Deces implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToOne
    private Medecin medecin;
    @ManyToOne
    private Infirmiere infirmiere;
    @OneToOne
    private Client client;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date rapport;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date deces;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date delivranceCorp;
    private String nomRecuperateur;
    private String delivreur;
    private String cause;

    public Deces() {
    }

    public Deces(Long id) {
        this.id = id;
    }

    public Deces(Long id, String description, Medecin medecin, Infirmiere infirmiere, Client client, Date rapport, Date deces, Date delivranceCorp, String nomRecuperateur, String delivreur, String cause) {
        this.id = id;
        this.description = description;
        this.medecin = medecin;
        this.infirmiere = infirmiere;
        this.client = client;
        this.rapport = rapport;
        this.deces = deces;
        this.delivranceCorp = delivranceCorp;
        this.nomRecuperateur = nomRecuperateur;
        this.delivreur = delivreur;
        this.cause = cause;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Infirmiere getInfirmiere() {
        if (infirmiere == null) {
            infirmiere = new Infirmiere();
        }
        return infirmiere;
    }

    public void setInfirmiere(Infirmiere infirmiere) {
        this.infirmiere = infirmiere;
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

    public Date getRapport() {
        return rapport;
    }

    public void setRapport(Date rapport) {
        this.rapport = rapport;
    }

    public Date getDeces() {
        return deces;
    }

    public void setDeces(Date deces) {
        this.deces = deces;
    }

    public Date getDelivranceCorp() {
        return delivranceCorp;
    }

    public void setDelivranceCorp(Date delivranceCorp) {
        this.delivranceCorp = delivranceCorp;
    }

    public String getNomRecuperateur() {
        return nomRecuperateur;
    }

    public void setNomRecuperateur(String nomRecuperateur) {
        this.nomRecuperateur = nomRecuperateur;
    }

    public String getDelivreur() {
        return delivreur;
    }

    public void setDelivreur(String delivreur) {
        this.delivreur = delivreur;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
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
        if (!(object instanceof Deces)) {
            return false;
        }
        Deces other = (Deces) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Deces[ id=" + id + " ]";
    }

}
