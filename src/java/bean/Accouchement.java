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

/**
 *
 * @author asus
 */
@Entity
public class Accouchement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String description;
    @ManyToOne
    private Medecin medecin;
    @ManyToOne
    private Infirmiere infirmiere;
    @ManyToOne
    private Client client;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date childbirth;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date rapport;
    private String sexEnfant;
    private float poidsEnfant;

    public Accouchement() {
    }

    public Accouchement(Long id) {
        this.id = id;
    }

    public Accouchement(Long id, String type, String description, Medecin medecin, Infirmiere infirmiere, Client client, Date childbirth, Date rapport, String sexEnfant, float poidsEnfant) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.medecin = medecin;
        this.infirmiere = infirmiere;
        this.client = client;
        this.childbirth = childbirth;
        this.rapport = rapport;
        this.sexEnfant = sexEnfant;
        this.poidsEnfant = poidsEnfant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Date getChildbirth() {
        return childbirth;
    }

    public void setChildbirth(Date childbirth) {
        this.childbirth = childbirth;
    }

    public Date getRapport() {
        return rapport;
    }

    public void setRapport(Date rapport) {
        this.rapport = rapport;
    }

    public String getSexEnfant() {
        return sexEnfant;
    }

    public void setSexEnfant(String sexEnfant) {
        this.sexEnfant = sexEnfant;
    }

    public float getPoidsEnfant() {
        return poidsEnfant;
    }

    public void setPoidsEnfant(float poidsEnfant) {
        this.poidsEnfant = poidsEnfant;
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
        if (!(object instanceof Accouchement)) {
            return false;
        }
        Accouchement other = (Accouchement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Accouchement[ id=" + id + " ]";
    }

}
