/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DonneurSang;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author asus
 */
@Stateless
public class DonneurSangFacade extends AbstractFacade<DonneurSang> {
    @PersistenceContext(unitName = "gestionHopitalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DonneurSangFacade() {
        super(DonneurSang.class);
    }
    
}
