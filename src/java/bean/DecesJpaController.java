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
public class DecesJpaController implements Serializable {

    public DecesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Deces deces) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Medecin medecin = deces.getMedecin();
            if (medecin != null) {
                medecin = em.getReference(medecin.getClass(), medecin.getId());
                deces.setMedecin(medecin);
            }
            Infirmiere infirmiere = deces.getInfirmiere();
            if (infirmiere != null) {
                infirmiere = em.getReference(infirmiere.getClass(), infirmiere.getId());
                deces.setInfirmiere(infirmiere);
            }
            Client client = deces.getClient();
            if (client != null) {
                client = em.getReference(client.getClass(), client.getId());
                deces.setClient(client);
            }
            em.persist(deces);
            if (medecin != null) {
                medecin.getDecess().add(deces);
                medecin = em.merge(medecin);
            }
            if (infirmiere != null) {
                infirmiere.getDecess().add(deces);
                infirmiere = em.merge(infirmiere);
            }
            if (client != null) {
                Deces oldDecesOfClient = client.getDeces();
                if (oldDecesOfClient != null) {
                    oldDecesOfClient.setClient(null);
                    oldDecesOfClient = em.merge(oldDecesOfClient);
                }
                client.setDeces(deces);
                client = em.merge(client);
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

    public void edit(Deces deces) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Deces persistentDeces = em.find(Deces.class, deces.getId());
            Medecin medecinOld = persistentDeces.getMedecin();
            Medecin medecinNew = deces.getMedecin();
            Infirmiere infirmiereOld = persistentDeces.getInfirmiere();
            Infirmiere infirmiereNew = deces.getInfirmiere();
            Client clientOld = persistentDeces.getClient();
            Client clientNew = deces.getClient();
            if (medecinNew != null) {
                medecinNew = em.getReference(medecinNew.getClass(), medecinNew.getId());
                deces.setMedecin(medecinNew);
            }
            if (infirmiereNew != null) {
                infirmiereNew = em.getReference(infirmiereNew.getClass(), infirmiereNew.getId());
                deces.setInfirmiere(infirmiereNew);
            }
            if (clientNew != null) {
                clientNew = em.getReference(clientNew.getClass(), clientNew.getId());
                deces.setClient(clientNew);
            }
            deces = em.merge(deces);
            if (medecinOld != null && !medecinOld.equals(medecinNew)) {
                medecinOld.getDecess().remove(deces);
                medecinOld = em.merge(medecinOld);
            }
            if (medecinNew != null && !medecinNew.equals(medecinOld)) {
                medecinNew.getDecess().add(deces);
                medecinNew = em.merge(medecinNew);
            }
            if (infirmiereOld != null && !infirmiereOld.equals(infirmiereNew)) {
                infirmiereOld.getDecess().remove(deces);
                infirmiereOld = em.merge(infirmiereOld);
            }
            if (infirmiereNew != null && !infirmiereNew.equals(infirmiereOld)) {
                infirmiereNew.getDecess().add(deces);
                infirmiereNew = em.merge(infirmiereNew);
            }
            if (clientOld != null && !clientOld.equals(clientNew)) {
                clientOld.setDeces(null);
                clientOld = em.merge(clientOld);
            }
            if (clientNew != null && !clientNew.equals(clientOld)) {
                Deces oldDecesOfClient = clientNew.getDeces();
                if (oldDecesOfClient != null) {
                    oldDecesOfClient.setClient(null);
                    oldDecesOfClient = em.merge(oldDecesOfClient);
                }
                clientNew.setDeces(deces);
                clientNew = em.merge(clientNew);
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
                Long id = deces.getId();
                if (findDeces(id) == null) {
                    throw new NonexistentEntityException("The deces with id " + id + " no longer exists.");
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
            Deces deces;
            try {
                deces = em.getReference(Deces.class, id);
                deces.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The deces with id " + id + " no longer exists.", enfe);
            }
            Medecin medecin = deces.getMedecin();
            if (medecin != null) {
                medecin.getDecess().remove(deces);
                medecin = em.merge(medecin);
            }
            Infirmiere infirmiere = deces.getInfirmiere();
            if (infirmiere != null) {
                infirmiere.getDecess().remove(deces);
                infirmiere = em.merge(infirmiere);
            }
            Client client = deces.getClient();
            if (client != null) {
                client.setDeces(null);
                client = em.merge(client);
            }
            em.remove(deces);
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

    public List<Deces> findDecesEntities() {
        return findDecesEntities(true, -1, -1);
    }

    public List<Deces> findDecesEntities(int maxResults, int firstResult) {
        return findDecesEntities(false, maxResults, firstResult);
    }

    private List<Deces> findDecesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Deces.class));
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

    public Deces findDeces(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Deces.class, id);
        } finally {
            em.close();
        }
    }

    public int getDecesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Deces> rt = cq.from(Deces.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
