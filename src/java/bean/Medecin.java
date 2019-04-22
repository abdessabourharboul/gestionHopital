/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
public class Medecin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullname;
    private String adresse;
    private Long telephone;
    private String type;
    @OneToMany(mappedBy = "medecin")
    private List<Appointement> appointements;
    @OneToMany(mappedBy = "medecin")
    private List<Prelevement> prelevements;
    @OneToMany(mappedBy = "medecin")
    private List<Chirurgie> chirurgies;
    @OneToMany(mappedBy = "medecin")
    private List<Accouchement> accouchements;
    @OneToMany(mappedBy = "medecin")
    private List<Deces> decess;

    public Medecin() {
    }

    public Medecin(Long id) {
        this.id = id;
    }

    public Medecin(Long id, String fullname, String adresse, Long telephone, String type, List<Appointement> appointements, List<Prelevement> prelevements, List<Chirurgie> chirurgies, List<Accouchement> accouchements, List<Deces> decess) {
        this.id = id;
        this.fullname = fullname;
        this.adresse = adresse;
        this.telephone = telephone;
        this.type = type;
        this.appointements = appointements;
        this.prelevements = prelevements;
        this.chirurgies = chirurgies;
        this.accouchements = accouchements;
        this.decess = decess;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Prelevement> getPrelevements() {
        if (prelevements == null) {
            prelevements = new ArrayList<>();
        }
        return prelevements;
    }

    public void setPrelevements(List<Prelevement> prelevements) {
        this.prelevements = prelevements;
    }

    public List<Appointement> getAppointements() {
        if (appointements == null) {
            appointements = new ArrayList<>();
        }
        return appointements;
    }

    public void setAppointements(List<Appointement> appointements) {
        this.appointements = appointements;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Medecin other = (Medecin) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
