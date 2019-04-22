/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Prelevement;
import bean.PrelevementResultat;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import net.sf.jasperreports.engine.JRException;
import service.PrelevementFacade;
import service.PrelevementResultatFacade;
import util.Config;
import util.JasperUtil;

/**
 *
 * @author asus
 */
@Named(value = "prelevementController")
@SessionScoped
public class PrelevementController implements Serializable {

    private Prelevement prelevement;
    private PrelevementResultat prelevementResultat;
    private Prelevement prev;

    public void imprimer() throws IOException, JRException{
         List<Prelevement> prelevements=new ArrayList();
         Prelevement p = new Prelevement();
         
        prelevements=prelevementFacade.findAll();
        Map params = new HashMap();
        params.put("molChi", "younes");
        JasperUtil.generatePdf(prelevements, Config.getCheminJasper(), Config.getCheminExport(), params, true);
}
    public Prelevement getPrev() {
        return prev;
    }

    public void setPrev(Prelevement prev) {
        this.prev = prev;
    }

    @EJB
    private PrelevementFacade prelevementFacade;
    @EJB
    private PrelevementResultatFacade prelevementResultatFacade;

    private EntityManager em;

    private Long generateId() {
        Long maxId = (Long) em.createQuery("SELECT MAX id FROM Prelevementresultat").getSingleResult();
        return (maxId == null ? 1l : maxId + 1);
    }

    public void save() {
//        prelevementResultat.setId(generateId());
//        System.out.println(prelevementResultat.getId());
        prelevementResultatFacade.create(prelevement.getPrelevementResultat());
//        prelevement.setPrelevementResultat(prelevementResultat);
        prelevementFacade.create(prelevement);
    }

    public void recuperation() {
    }

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
     * Creates a new instance of PrelevementController
     */
    public PrelevementController() {
    }

}
