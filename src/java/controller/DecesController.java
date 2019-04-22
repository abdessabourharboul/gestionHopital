/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Deces;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import service.DecesFacade;

/**
 *
 * @author asus
 */
@Named(value = "decesController")
@SessionScoped
public class DecesController implements Serializable {

    private Deces deces;
    private List<Deces> deceses;
    private DecesFacade decesFacade;

    public Deces getDeces() {
        if (deces == null) {
            deces = new Deces();
        }
        return deces;
    }

    public void setDeces(Deces deces) {
        this.deces = deces;
    }

    public List<Deces> getDeceses() {
        if (deceses == null) {
            deceses = decesFacade.findAll();
        }
        return deceses;
    }

    public void setDeceses(List<Deces> deceses) {
        this.deceses = deceses;
    }

    public DecesFacade getDecesFacade() {
        return decesFacade;
    }

    public void setDecesFacade(DecesFacade decesFacade) {
        this.decesFacade = decesFacade;
    }

    /**
     * Creates a new instance of DecesController
     */
    public DecesController() {
    }

}
