/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Infirmiere;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author asus
 */
@Stateless
public class InfirmiereFacade extends AbstractFacade<Infirmiere> {
    @PersistenceContext(unitName = "gestionHopitalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InfirmiereFacade() {
        super(Infirmiere.class);
    }
    public List<Infirmiere> findByAllInf(String vleur,String var) {
        String requette = " SELECT i FROM Infirmiere i WHERE 1=1 ";
        if (vleur.equals("")) {
        }else {
            if (vleur.equals("fullname")) {
                requette += " AND i.fullnom LIKE '%" + var + "%' ";
            } else if (vleur.equals("adresse")) {
                requette += " AND i.adresse LIKE '%" + var + "%'  ";
            } else if (vleur.equals("telephone")) {
                requette += " AND i.telephone LIKE '%" + var + "%'  ";
            } 
            else if (vleur.equals("type")) {
                requette += " AND i.type LIKE '%" + var + "%'  ";
            }
            
        }

        return em.createQuery(requette).getResultList();
    }
    
}