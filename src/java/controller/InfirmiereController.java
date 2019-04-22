/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Infirmiere;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.InfirmiereFacade;

/**
 *
 * @author asus
 */
@Named(value = "infirmiereController")
@SessionScoped
public class InfirmiereController implements Serializable {

    private Infirmiere infirmiere;
    private List<Infirmiere> infirmieres;
    @EJB
    private InfirmiereFacade infirmiereFacade;
    private String valeur;
    private String var;

    public void findByAllInf(){
        infirmieres=infirmiereFacade.findByAllInf(valeur,var);
        
    }
    public void save() {
        infirmiereFacade.create(infirmiere);
        infirmieres.add(infirmiere);
        infirmiere = null;
    }

    public void modify() {
        int index = infirmieres.indexOf(infirmiere);
        infirmieres.set(index, infirmiere);
        infirmiereFacade.edit(infirmiere);
        infirmiere = null;
    }

    public void update(Infirmiere infirmiere) {
        this.infirmiere = infirmiere;
    }

    public void remove(Infirmiere infirmiere) {
        infirmiereFacade.remove(infirmiere);
        infirmieres.remove(infirmieres.indexOf(infirmiere));
    }

    public Infirmiere getInfirmiere() {
        if (infirmiere == null) {
            infirmiere = new Infirmiere();
        }
        return infirmiere;
    }

    public void setInfirmiere(Infirmiere infirmiere) {
        this.infirmiere = infirmiere;
    }

    public List<Infirmiere> getInfirmieres() {
        if (infirmieres == null) {
            infirmieres = infirmiereFacade.findAll();
        }
        return infirmieres;
    }

    public void setInfirmieres(List<Infirmiere> infirmieres) {
        this.infirmieres = infirmieres;
    }

    public InfirmiereFacade getInfirmiereFacade() {
        return infirmiereFacade;
    }

    public void setInfirmiereFacade(InfirmiereFacade infirmiereFacade) {
        this.infirmiereFacade = infirmiereFacade;
    }

    /**
     * Creates a new instance of InfirmiereController
     */
    public InfirmiereController() {
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
