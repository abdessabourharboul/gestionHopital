/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Medecin;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author asus
 */
@Stateless
public class MedecinFacade extends AbstractFacade<Medecin> {

    @PersistenceContext(unitName = "gestionHopitalPU")
    private EntityManager em;

    /**
     *
     * @param type
     * @return
     */
    public List<Medecin> findByAll(String vleur,String var) {
        String requette = " SELECT m FROM Medecin m WHERE 1=1 ";
        if (vleur.equals("")) {
        }else {
            if (vleur.equals("fullname")) {
                requette += " AND m.fullname LIKE '%" + var + "%' ";
            } else if (vleur.equals("adresse")) {
                requette += " AND m.adresse LIKE '%" + var + "%'  ";
            } else if (vleur.equals("telephone")) {
                requette += " AND m.telephone LIKE '%" + var + "%'  ";
            } 
            else if (vleur.equals("type")) {
                requette += " AND m.type LIKE '%" + var + "%'  ";
            }
            
        }

        return em.createQuery(requette).getResultList();
    }

    public List<Medecin> findGynecologues(String type) {
        String requette = "select c from Medecin c where c.type = ?1";
        TypedQuery<Medecin> query = em.createQuery(requette, Medecin.class);
        query.setParameter(1, type);
        return query.getResultList();
    }

    public MedecinFacade(Class<Medecin> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedecinFacade() {
        super(Medecin.class);
    }

}
