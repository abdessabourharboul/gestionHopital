/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Appointement;
import bean.Client;
import bean.Medecin;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.AppointementFacade;
import service.ClientFacade;
import service.MedecinFacade;

/**
 *
 * @author asus
 */
@Named(value = "appointementController")
@SessionScoped
public class AppointementController implements Serializable {

    private Appointement appointement;
    private List<Appointement> appointements;
    @EJB
    private AppointementFacade appointementFacade;
    @EJB
    private MedecinFacade medecinFacade;
    @EJB
    private ClientFacade clientFacade;

    
    public void save() {
        Medecin loaded = medecinFacade.find(appointement.getMedecin().getId());
        appointement.setMedecin(loaded);
        appointementFacade.create(appointement);
        appointements.add(appointement);
        appointement = null;
    }

    public void modify() {
        
        Medecin loaded = medecinFacade.find(appointement.getMedecin().getId());
        appointement.setMedecin(loaded);
        Client loadedC = clientFacade.find(appointement.getClient().getId());
        appointement.setClient(loadedC);
        
        int index = appointements.indexOf(appointement);
        appointements.set(index, appointement);      
        appointementFacade.edit(appointement); 
        appointement = null;
    }

    public void update(Appointement appointement) {
        this.appointement = appointement;

    }

    public void remove(Appointement appointement) {
        appointementFacade.remove(appointement);
        appointements.remove(appointements.indexOf(appointement));
    }

    public List<Appointement> getAppointements() {
        if (appointements == null) {
            appointements = appointementFacade.findAll();
        }
        return appointements;
    }

    public void setAppointements(List<Appointement> appointements) {
        this.appointements = appointements;
    }

    public Appointement getAppointement() {
        if (appointement == null) {
            appointement = new Appointement();
        }
        return appointement;
    }

    public void setAppointement(Appointement appointement) {
        this.appointement = appointement;
    }

    public AppointementFacade getAppointementFacade() {
        return appointementFacade;
    }

    public void setAppointementFacade(AppointementFacade appointementFacade) {
        this.appointementFacade = appointementFacade;
    }

    /**
     * Creates a new instance of AppointementController
     */
    public AppointementController() {
    }

    public MedecinFacade getMedecinFacade() {
        return medecinFacade;
    }

    public void setMedecinFacade(MedecinFacade medecinFacade) {
        this.medecinFacade = medecinFacade;
    }

    public ClientFacade getClientFacade() {
        return clientFacade;
    }

    public void setClientFacade(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

   

    
}
