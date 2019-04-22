/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Accouchement;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.AccouchementFacade;

/**
 *
 * @author asus
 */
@Named(value = "accouchementController")
@SessionScoped
public class AccouchementController implements Serializable {

    private Accouchement accouchement;
    private List<Accouchement> accouchements;
    @EJB
    private AccouchementFacade accouchementFacade;

    public void save() {
        accouchementFacade.create(accouchement);
        accouchements.add(accouchement);
        accouchement = null;
    }
    public Accouchement getAccouchement() {
        if (accouchement == null) {
            accouchement = new Accouchement();
        }
        return accouchement;
    }

    public void setAccouchement(Accouchement accouchement) {
        this.accouchement = accouchement;
    }

    public List<Accouchement> getAccouchements() {
        if (accouchements == null) {
            accouchements = accouchementFacade.findAll();
        }
        return accouchements;
    }

    public void setAccouchements(List<Accouchement> accouchements) {
        this.accouchements = accouchements;
    }

    public AccouchementFacade getAccouchementFacade() {
        return accouchementFacade;
    }

    public void setAccouchementFacade(AccouchementFacade accouchementFacade) {
        this.accouchementFacade = accouchementFacade;
    }

    /**
     * Creates a new instance of AccouchementController
     */
    public AccouchementController() {
    }

}
