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
public class BanqueSang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String GroupeSanguin;
    private long quantite;
    private String statut;

    public BanqueSang() {
    }

    public BanqueSang(Long id) {
        this.id = id;
    }

    public BanqueSang(Long id, String GroupeSanguin, long quantite, String statut) {
        this.id = id;
        this.GroupeSanguin = GroupeSanguin;
        this.quantite = quantite;
        this.statut = statut;
    }

    public String getGroupeSanguin() {
        return GroupeSanguin;
    }

    public void setGroupeSanguin(String GroupeSanguin) {
        this.GroupeSanguin = GroupeSanguin;
    }

    public long getQuantite() {
        return quantite;
    }

    public void setQuantite(long quantite) {
        this.quantite = quantite;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
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
        if (!(object instanceof BanqueSang)) {
            return false;
        }
        BanqueSang other = (BanqueSang) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.BanqueSang[ id=" + id + " ]";
    }

}
