/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author asus
 */
@Entity
public class PrelevementResultat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Float lymphocytes;
    private Float monocytes;
    private Float plaquettes;
    private Float cholesterol;
    private Float triglycerides;
    private Float glycemie;
    private Float sodium;
    private Float calcium;
    private Float phosphore;

    public PrelevementResultat() {
    }

    public PrelevementResultat(Long id) {
        this.id = id;
    }

    public PrelevementResultat(Long id, Float lymphocytes, Float monocytes, Float plaquettes, Float cholesterol, Float triglycerides, Float glycemie, Float sodium, Float calcium, Float phosphore) {
        this.id = id;
        this.lymphocytes = lymphocytes;
        this.monocytes = monocytes;
        this.plaquettes = plaquettes;
        this.cholesterol = cholesterol;
        this.triglycerides = triglycerides;
        this.glycemie = glycemie;
        this.sodium = sodium;
        this.calcium = calcium;
        this.phosphore = phosphore;
    }

    public Float getLymphocytes() {
        return lymphocytes;
    }

    public void setLymphocytes(Float lymphocytes) {
        this.lymphocytes = lymphocytes;
    }

    public Float getMonocytes() {
        return monocytes;
    }

    public void setMonocytes(Float monocytes) {
        this.monocytes = monocytes;
    }

    public Float getPlaquettes() {
        return plaquettes;
    }

    public void setPlaquettes(Float plaquettes) {
        this.plaquettes = plaquettes;
    }

    public Float getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Float cholesterol) {
        this.cholesterol = cholesterol;
    }

    public Float getTriglycerides() {
        return triglycerides;
    }

    public void setTriglycerides(Float triglycerides) {
        this.triglycerides = triglycerides;
    }

    public Float getGlycemie() {
        return glycemie;
    }

    public void setGlycemie(Float glycemie) {
        this.glycemie = glycemie;
    }

    public Float getSodium() {
        return sodium;
    }

    public void setSodium(Float sodium) {
        this.sodium = sodium;
    }

    public Float getCalcium() {
        return calcium;
    }

    public void setCalcium(Float calcium) {
        this.calcium = calcium;
    }

    public Float getPhosphore() {
        return phosphore;
    }

    public void setPhosphore(Float phosphore) {
        this.phosphore = phosphore;
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
        if (!(object instanceof PrelevementResultat)) {
            return false;
        }
        PrelevementResultat other = (PrelevementResultat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.PrelevementResultat[ id=" + id + " ]";
    }

}
