/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Medecin;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import service.MedecinFacade;

/**
 *
 * @author asus
 */
@Named(value = "medecinController")
@SessionScoped
public class MedecinController implements Serializable {

    private Medecin medecin;
    private List<Medecin> gynecologues;
    private List<Medecin> medecins;
    @EJB
    private MedecinFacade medecinFacade;
    private String valeur ;
    private String var;

    public void findByAll(){
        medecins=medecinFacade.findByAll(valeur,var);
        
    }

   
    public void save() {
        medecinFacade.create(medecin);
        medecins.add(medecin);
        medecin = null;
    }

    public void modify() {
        int index = medecins.indexOf(medecin);
        medecins.set(index, medecin);
        medecinFacade.edit(medecin);
        medecin = null;
    }

    public void update(Medecin medecin) {
        this.medecin = medecin;
    }

    public void remove(Medecin medecin) {
        medecinFacade.remove(medecin);
        medecins.remove(medecins.indexOf(medecin));
    }

    public List<Medecin> getMedecins() {
        if (medecins == null) {
            medecins = medecinFacade.findAll();
        }
        return medecins;
    }

    public void setMedecins(List<Medecin> medecins) {
        this.medecins = medecins;
    }

    public Medecin getMedecin() {
        if (medecin == null) {
            medecin = new Medecin();
        }
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public MedecinFacade getMedecinFacade() {
        return medecinFacade;
    }

    public void setMedecinFacade(MedecinFacade medecinFacade) {
        this.medecinFacade = medecinFacade;
    }

    public List<Medecin> getGynecologues() {
        if (gynecologues == null) {
            gynecologues = medecinFacade.findGynecologues("Gynecologue");
        }
        return gynecologues;
    }

    public void setGynecologues(List<Medecin> gynecologues) {
        this.gynecologues = gynecologues;
    }

    /**
     * Creates a new instance of MedecinController
     */
    public MedecinController() {
    }
public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }
    
}
