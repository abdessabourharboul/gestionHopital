/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Administrateur;
import bean.User;
import controler.util.SessionUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import service.AdministrateurFacade;

/**
 *
 * @author asus
 */
@Named(value = "administrateurController")
@SessionScoped
public class AdministrateurController implements Serializable {

    Administrateur administrateur;
    @EJB
    AdministrateurFacade administrateurFacade;

    public String logIn() {
        Administrateur myAdministrateur = administrateurFacade.find(administrateur.getId());
        FacesContext context = FacesContext.getCurrentInstance();
        if (myAdministrateur == null) {
            context.addMessage(null, new FacesMessage("Unknown login, try again"));
            administrateur.setId(null);
            administrateur.setPassword(null);
            administrateur.setIsLogged(false);
            return null;
        } else if (!myAdministrateur.getPassword().equals(administrateur.getPassword())) {
            administrateur.setIsLogged(false);
            return null;

        } else {
            SessionUtil.setAttribute("administrateur", myAdministrateur);
            administrateur.setId(null);
            administrateur.setPassword(null);
            myAdministrateur.setIsLogged(true);
            administrateurFacade.edit(myAdministrateur);
            return "securiseAdministrateur/WelcomeAdministrateur?faces-redirect=true";
        }
    }
    
    public String logOut() {
        Administrateur myAdministrateur = (Administrateur) SessionUtil.getAttribute("administrateur");
        myAdministrateur.setIsLogged(false);
        administrateurFacade.edit(myAdministrateur);
        SessionUtil.getSession().invalidate();
        return "/index?faces-redirect=true";
    }

    public Administrateur getAdministrateur() {
        if (administrateur == null) {
            administrateur = new Administrateur();
        }
        return administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

    public AdministrateurFacade getAdministrateurFacade() {
        return administrateurFacade;
    }

    public void setAdministrateurFacade(AdministrateurFacade administrateurFacade) {
        this.administrateurFacade = administrateurFacade;
    }

    /**
     * Creates a new instance of AdministrateurController
     */
    public AdministrateurController() {
    }

}
