

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.BanqueSang;
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
public class BanqueSangFacade extends AbstractFacade<BanqueSang> {

    @PersistenceContext(unitName = "gestionHopitalPU")
    private EntityManager em;

    public BanqueSang findBanqueSangByGroupeSanguin(String GroupeSanguin) {
        String requette = "select u from BanqueSang u where u.GroupeSanguin = ?1";
        TypedQuery<BanqueSang> query = em.createQuery(requette, BanqueSang.class);
        query.setParameter(1, GroupeSanguin);
        List<BanqueSang> banqueSangs = query.getResultList();
        if (!banqueSangs.isEmpty()) {
            return banqueSangs.get(0);
        }
        return null;
    }

    public String checkStatut(long quantite) {
        String statut;
        if (quantite < 20) {
            statut = "Manque";
        } else if (quantite < 50) {
            statut = "Normal";

        } else {
            statut = "Bien";
        }
        return statut;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BanqueSangFacade() {
        super(BanqueSang.class);
    }

}
