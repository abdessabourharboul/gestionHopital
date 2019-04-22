/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Chirurgie;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.ChirurgieFacade;

/**
 *
 * @author asus
 */
@Named(value = "chirurgieController")
@SessionScoped
public class ChirurgieController implements Serializable {

    private Chirurgie chirurgie;
    
    private List<Chirurgie> chirurgies;
    @EJB
    private ChirurgieFacade chirurgieFacade;

    public void save() {
        chirurgieFacade.create(chirurgie);
        chirurgies.add(chirurgie);
        chirurgie = null;
    }

    public Chirurgie getChirurgie() {
        if (chirurgie == null) {
            chirurgie = new Chirurgie();
        }
        return chirurgie;
    }

    public void setChirurgie(Chirurgie chirurgie) {
        this.chirurgie = chirurgie;
    }

    public List<Chirurgie> getChirurgies() {
        if (chirurgies == null) {
            chirurgies = chirurgieFacade.findAll();
        }
        return chirurgies;
    }

    public void setChirurgies(List<Chirurgie> chirurgies) {
        this.chirurgies = chirurgies;
    }

    public ChirurgieFacade getChirurgieFacade() {
        return chirurgieFacade;
    }

    public void setChirurgieFacade(ChirurgieFacade chirurgieFacade) {
        this.chirurgieFacade = chirurgieFacade;
    }

    /**
     * Creates a new instance of ChirurgieController
     */
    public ChirurgieController() {
    }

}
