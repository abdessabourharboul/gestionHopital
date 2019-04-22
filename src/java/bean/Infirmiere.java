/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author asus
 */
@Entity
public class Infirmiere implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullnom;
    private Long telephone;
    private String adresse;
    private String type;
    @OneToMany(mappedBy = "infirmiere")
    private List<Chirurgie> chirurgies;
    @OneToMany(mappedBy = "infirmiere")
    private List<Accouchement> accouchements;
    @OneToMany(mappedBy = "infirmiere")
    private List<Deces> decess;

    public Infirmiere() {
    }

    public Infirmiere(Long id) {
        this.id = id;
    }

    public Infirmiere(Long id, String fullnom, Long telephone, String adresse, String type, List<Chirurgie> chirurgies, List<Accouchement> accouchements, List<Deces> decess) {
        this.id = id;
        this.fullnom = fullnom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.type = type;
        this.chirurgies = chirurgies;
        this.accouchements = accouchements;
        this.decess = decess;
    }

    public List<Chirurgie> getChirurgies() {
        if (chirurgies == null) {
            chirurgies = new ArrayList<>();
        }
        return chirurgies;
    }

    public void setChirurgies(List<Chirurgie> chirurgies) {
        this.chirurgies = chirurgies;
    }

    public List<Accouchement> getAccouchements() {
        if (accouchements == null) {
            accouchements = new ArrayList<>();
        }
        return accouchements;
    }

    public void setAccouchements(List<Accouchement> accouchements) {
        this.accouchements = accouchements;
    }

    public List<Deces> getDecess() {
        if (decess == null) {
            decess = new ArrayList<>();
        }
        return decess;
    }

    public void setDecess(List<Deces> decess) {
        this.decess = decess;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getFullnom() {
        return fullnom;
    }

    public void setFullnom(String fullnom) {
        this.fullnom = fullnom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        if (!(object instanceof Infirmiere)) {
            return false;
        }
        Infirmiere other = (Infirmiere) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Infirmiere[ id=" + id + " ]";
    }

}
