/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Chambre;
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
public class ChambreFacade extends AbstractFacade<Chambre> {

    @PersistenceContext(unitName = "gestionHopitalPU")
    private EntityManager em;

    public List<Chambre> findChambresByType(String type) {
        String requette = "select c from Chambre c where c.type = ?1";
        TypedQuery<Chambre> query = em.createQuery(requette, Chambre.class);
        query.setParameter(1, type);
        return query.getResultList();
    }

    public List<Chambre> findChambresNonOcuppeesByType(String type) {
        List<Chambre> loadedChambres = findChambresByType(type);
        List<Chambre> newList = new ArrayList<>();
        for (int i = 0; i < loadedChambres.size(); i++) {
            Chambre item = newList.get(i);
            if (!item.isOccupe() == true) {
                newList.add(item);
            }
        }
        return newList;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChambreFacade() {
        super(Chambre.class);
    }

}
