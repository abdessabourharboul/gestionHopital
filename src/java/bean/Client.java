/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author asus
 */
@Entity
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id

//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(unique = true)
    private String id;

    @Size(min = 5, max = 20, message = "Nom :Khassou ikoun bin 5 ou 20")
    private String nom;
    @Size(min = 5, max = 20, message = "Prenom :Khassou ikoun bin 5 ou 20")
    private String prenom;
//    @Size(min = 5, max = 20, message = "Mot de passe :Khassou ikoun bin 5 ou 20")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Minimum 8 characters at least 1 Alphabet and 1 Number")
    private String password;
//    @Pattern(regexp = "\\(\\d{3}\\)\\d{3}-\\d{4}")
    @Pattern(regexp = "\\+\\d{3}\\-\\d{9}", message = "Telephone : Khassou ikoun bhal hakka +212-651455730")
    private String telephone;
    private String gender;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Past
    private Date birthdate;

    @OneToMany(mappedBy = "client")
    private List<Appointement> appointements;
    @OneToMany(mappedBy = "client")
    private List<Prelevement> prelevements;

    @OneToMany(mappedBy = "client")
    private List<Chirurgie> chirurgies;
    @OneToMany(mappedBy = "client")
    private List<Accouchement> accouchements;
    @OneToOne(mappedBy = "client")
    private Deces deces;

    public Client() {
    }

    public Client(String id) {
        this.id = id;
    }

    public Client(String id, String nom, String prenom, String password, String telephone, String gender, Date birthdate, List<Appointement> appointements, List<Prelevement> prelevements, List<Chirurgie> chirurgies, List<Accouchement> accouchements, Deces deces) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.telephone = telephone;
        this.gender = gender;
        this.birthdate = birthdate;
        this.appointements = appointements;
        this.prelevements = prelevements;
        this.chirurgies = chirurgies;
        this.accouchements = accouchements;
        this.deces = deces;
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

    public Deces getDeces() {
        if (deces == null) {
            deces = new Deces();
        }
        return deces;
    }

    public void setDeces(Deces deces) {
        this.deces = deces;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Client[ id=" + id + " ]";
    }

}
