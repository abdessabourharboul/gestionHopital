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
import javax.persistence.Temporal;

/**
 *
 * @author asus
 */
@Entity
public class DonneurSang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private int age;
    private String Sexe;
    private String GroupeSanguin;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date derniereDonation;

    public DonneurSang() {
    }

    public DonneurSang(Long id) {
        this.id = id;
    }

    public DonneurSang(Long id, String nom, String prenom, int age, String Sexe, String GroupeSanguin, Date derniereDonation) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.Sexe = Sexe;
        this.GroupeSanguin = GroupeSanguin;
        this.derniereDonation = derniereDonation;
    }

    public String getNom() {
        return nom;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSexe() {
        return Sexe;
    }

    public void setSexe(String Sexe) {
        this.Sexe = Sexe;
    }

    public String getGroupeSanguin() {
        return GroupeSanguin;
    }

    public void setGroupeSanguin(String GroupeSanguin) {
        this.GroupeSanguin = GroupeSanguin;
    }

    public Date getDerniereDonation() {
        return derniereDonation;
    }

    public void setDerniereDonation(Date derniereDonation) {
        this.derniereDonation = derniereDonation;
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
        if (!(object instanceof DonneurSang)) {
            return false;
        }
        DonneurSang other = (DonneurSang) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.DonneurSang[ id=" + id + " ]";
    }

}
