/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Chambre;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.ChambreFacade;

/**
 *
 * @author asus
 */
@Named(value = "chambreController")
@SessionScoped
public class ChambreController implements Serializable {

    private Chambre chambre;
    private List<Chambre> chambres;
    @EJB
    private ChambreFacade chambreFacade;
    
    
    
    public void save() {
        chambreFacade.create(chambre);
        chambres.add(chambre);
        chambre = null;
        
    }

    public Chambre getChambre() {
        if (chambre == null) {
            chambre = new Chambre();
        }
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public List<Chambre> getChambres() {
        if (chambres == null) {
            chambres = chambreFacade.findAll();
        }
        return chambres;
    }

    public void setChambres(List<Chambre> chambres) {
        this.chambres = chambres;
    }

    public ChambreFacade getChambreFacade() {
        return chambreFacade;
    }

    public void setChambreFacade(ChambreFacade chambreFacade) {
        this.chambreFacade = chambreFacade;
    }

    /**
     * Creates a new instance of ChambreController
     */
    public ChambreController() {
    }

}
