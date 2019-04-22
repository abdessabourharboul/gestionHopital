/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import bean.exceptions.NonexistentEntityException;
import bean.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author ayoub
 */
public class PrelevementJpaController implements Serializable {

    public PrelevementJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Prelevement prelevement) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Client client = prelevement.getClient();
            if (client != null) {
                client = em.getReference(client.getClass(), client.getId());
                prelevement.setClient(client);
            }
            Medecin medecin = prelevement.getMedecin();
            if (medecin != null) {
                medecin = em.getReference(medecin.getClass(), medecin.getId());
                prelevement.setMedecin(medecin);
            }
            em.persist(prelevement);
            if (client != null) {
                client.getPrelevements().add(prelevement);
                client = em.merge(client);
            }
            if (medecin != null) {
                medecin.getPrelevements().add(prelevement);
                medecin = em.merge(medecin);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Prelevement prelevement) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Prelevement persistentPrelevement = em.find(Prelevement.class, prelevement.getId());
            Client clientOld = persistentPrelevement.getClient();
            Client clientNew = prelevement.getClient();
            Medecin medecinOld = persistentPrelevement.getMedecin();
            Medecin medecinNew = prelevement.getMedecin();
            if (clientNew != null) {
                clientNew = em.getReference(clientNew.getClass(), clientNew.getId());
                prelevement.setClient(clientNew);
            }
            if (medecinNew != null) {
                medecinNew = em.getReference(medecinNew.getClass(), medecinNew.getId());
                prelevement.setMedecin(medecinNew);
            }
            prelevement = em.merge(prelevement);
            if (clientOld != null && !clientOld.equals(clientNew)) {
                clientOld.getPrelevements().remove(prelevement);
                clientOld = em.merge(clientOld);
            }
            if (clientNew != null && !clientNew.equals(clientOld)) {
                clientNew.getPrelevements().add(prelevement);
                clientNew = em.merge(clientNew);
            }
            if (medecinOld != null && !medecinOld.equals(medecinNew)) {
                medecinOld.getPrelevements().remove(prelevement);
                medecinOld = em.merge(medecinOld);
            }
            if (medecinNew != null && !medecinNew.equals(medecinOld)) {
                medecinNew.getPrelevements().add(prelevement);
                medecinNew = em.merge(medecinNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = prelevement.getId();
                if (findPrelevement(id) == null) {
                    throw new NonexistentEntityException("The prelevement with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Prelevement prelevement;
            try {
                prelevement = em.getReference(Prelevement.class, id);
                prelevement.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prelevement with id " + id + " no longer exists.", enfe);
            }
            Client client = prelevement.getClient();
            if (client != null) {
                client.getPrelevements().remove(prelevement);
                client = em.merge(client);
            }
            Medecin medecin = prelevement.getMedecin();
            if (medecin != null) {
                medecin.getPrelevements().remove(prelevement);
                medecin = em.merge(medecin);
            }
            em.remove(prelevement);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Prelevement> findPrelevementEntities() {
        return findPrelevementEntities(true, -1, -1);
    }

    public List<Prelevement> findPrelevementEntities(int maxResults, int firstResult) {
        return findPrelevementEntities(false, maxResults, firstResult);
    }

    private List<Prelevement> findPrelevementEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Prelevement.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Prelevement findPrelevement(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Prelevement.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrelevementCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Prelevement> rt = cq.from(Prelevement.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
