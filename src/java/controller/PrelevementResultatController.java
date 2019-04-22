/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Prelevement;
import bean.PrelevementResultat;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import service.PrelevementFacade;
import service.PrelevementResultatFacade;

/**
 *
 * @author asus
 */
@Named(value = "prelevementResultatController")
@SessionScoped
public class PrelevementResultatController implements Serializable {

    Prelevement prelevement;
    PrelevementResultat prelevementResultat;
    @EJB
    PrelevementFacade prelevementFacade;
    @EJB
    PrelevementResultatFacade prelevementResultatFacade;

    public Prelevement getPrelevement() {
        if (prelevement == null) {
            prelevement = new Prelevement();
        }
        return prelevement;
    }

    public void setPrelevement(Prelevement prelevement) {
        this.prelevement = prelevement;
    }

    public PrelevementResultat getPrelevementResultat() {
        if (prelevementResultat == null) {
            prelevementResultat = new PrelevementResultat();
        }
        return prelevementResultat;
    }

    public void setPrelevementResultat(PrelevementResultat prelevementResultat) {
        this.prelevementResultat = prelevementResultat;
    }

    public PrelevementFacade getPrelevementFacade() {
        return prelevementFacade;
    }

    public void setPrelevementFacade(PrelevementFacade prelevementFacade) {
        this.prelevementFacade = prelevementFacade;
    }

    public PrelevementResultatFacade getPrelevementResultatFacade() {
        return prelevementResultatFacade;
    }

    public void setPrelevementResultatFacade(PrelevementResultatFacade prelevementResultatFacade) {
        this.prelevementResultatFacade = prelevementResultatFacade;
    }

    /**
     * Creates a new instance of PrelevementResultatController
     */
    public PrelevementResultatController() {
    }

}
