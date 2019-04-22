/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import bean.exceptions.NonexistentEntityException;
import bean.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author ayoub
 */
public class InfirmiereJpaController implements Serializable {

    public InfirmiereJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Infirmiere infirmiere) throws RollbackFailureException, Exception {
        if (infirmiere.getChirurgies() == null) {
            infirmiere.setChirurgies(new ArrayList<Chirurgie>());
        }
        if (infirmiere.getAccouchements() == null) {
            infirmiere.setAccouchements(new ArrayList<Accouchement>());
        }
        if (infirmiere.getDecess() == null) {
            infirmiere.setDecess(new ArrayList<Deces>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Chirurgie> attachedChirurgies = new ArrayList<Chirurgie>();
            for (Chirurgie chirurgiesChirurgieToAttach : infirmiere.getChirurgies()) {
                chirurgiesChirurgieToAttach = em.getReference(chirurgiesChirurgieToAttach.getClass(), chirurgiesChirurgieToAttach.getId());
                attachedChirurgies.add(chirurgiesChirurgieToAttach);
            }
            infirmiere.setChirurgies(attachedChirurgies);
            List<Accouchement> attachedAccouchements = new ArrayList<Accouchement>();
            for (Accouchement accouchementsAccouchementToAttach : infirmiere.getAccouchements()) {
                accouchementsAccouchementToAttach = em.getReference(accouchementsAccouchementToAttach.getClass(), accouchementsAccouchementToAttach.getId());
                attachedAccouchements.add(accouchementsAccouchementToAttach);
            }
            infirmiere.setAccouchements(attachedAccouchements);
            List<Deces> attachedDecess = new ArrayList<Deces>();
            for (Deces decessDecesToAttach : infirmiere.getDecess()) {
                decessDecesToAttach = em.getReference(decessDecesToAttach.getClass(), decessDecesToAttach.getId());
                attachedDecess.add(decessDecesToAttach);
            }
            infirmiere.setDecess(attachedDecess);
            em.persist(infirmiere);
            for (Chirurgie chirurgiesChirurgie : infirmiere.getChirurgies()) {
                Infirmiere oldInfirmiereOfChirurgiesChirurgie = chirurgiesChirurgie.getInfirmiere();
                chirurgiesChirurgie.setInfirmiere(infirmiere);
                chirurgiesChirurgie = em.merge(chirurgiesChirurgie);
                if (oldInfirmiereOfChirurgiesChirurgie != null) {
                    oldInfirmiereOfChirurgiesChirurgie.getChirurgies().remove(chirurgiesChirurgie);
                    oldInfirmiereOfChirurgiesChirurgie = em.merge(oldInfirmiereOfChirurgiesChirurgie);
                }
            }
            for (Accouchement accouchementsAccouchement : infirmiere.getAccouchements()) {
                Infirmiere oldInfirmiereOfAccouchementsAccouchement = accouchementsAccouchement.getInfirmiere();
                accouchementsAccouchement.setInfirmiere(infirmiere);
                accouchementsAccouchement = em.merge(accouchementsAccouchement);
                if (oldInfirmiereOfAccouchementsAccouchement != null) {
                    oldInfirmiereOfAccouchementsAccouchement.getAccouchements().remove(accouchementsAccouchement);
                    oldInfirmiereOfAccouchementsAccouchement = em.merge(oldInfirmiereOfAccouchementsAccouchement);
                }
            }
            for (Deces decessDeces : infirmiere.getDecess()) {
                Infirmiere oldInfirmiereOfDecessDeces = decessDeces.getInfirmiere();
                decessDeces.setInfirmiere(infirmiere);
                decessDeces = em.merge(decessDeces);
                if (oldInfirmiereOfDecessDeces != null) {
                    oldInfirmiereOfDecessDeces.getDecess().remove(decessDeces);
                    oldInfirmiereOfDecessDeces = em.merge(oldInfirmiereOfDecessDeces);
                }
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

    public void edit(Infirmiere infirmiere) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Infirmiere persistentInfirmiere = em.find(Infirmiere.class, infirmiere.getId());
            List<Chirurgie> chirurgiesOld = persistentInfirmiere.getChirurgies();
            List<Chirurgie> chirurgiesNew = infirmiere.getChirurgies();
            List<Accouchement> accouchementsOld = persistentInfirmiere.getAccouchements();
            List<Accouchement> accouchementsNew = infirmiere.getAccouchements();
            List<Deces> decessOld = persistentInfirmiere.getDecess();
            List<Deces> decessNew = infirmiere.getDecess();
            List<Chirurgie> attachedChirurgiesNew = new ArrayList<Chirurgie>();
            for (Chirurgie chirurgiesNewChirurgieToAttach : chirurgiesNew) {
                chirurgiesNewChirurgieToAttach = em.getReference(chirurgiesNewChirurgieToAttach.getClass(), chirurgiesNewChirurgieToAttach.getId());
                attachedChirurgiesNew.add(chirurgiesNewChirurgieToAttach);
            }
            chirurgiesNew = attachedChirurgiesNew;
            infirmiere.setChirurgies(chirurgiesNew);
            List<Accouchement> attachedAccouchementsNew = new ArrayList<Accouchement>();
            for (Accouchement accouchementsNewAccouchementToAttach : accouchementsNew) {
                accouchementsNewAccouchementToAttach = em.getReference(accouchementsNewAccouchementToAttach.getClass(), accouchementsNewAccouchementToAttach.getId());
                attachedAccouchementsNew.add(accouchementsNewAccouchementToAttach);
            }
            accouchementsNew = attachedAccouchementsNew;
            infirmiere.setAccouchements(accouchementsNew);
            List<Deces> attachedDecessNew = new ArrayList<Deces>();
            for (Deces decessNewDecesToAttach : decessNew) {
                decessNewDecesToAttach = em.getReference(decessNewDecesToAttach.getClass(), decessNewDecesToAttach.getId());
                attachedDecessNew.add(decessNewDecesToAttach);
            }
            decessNew = attachedDecessNew;
            infirmiere.setDecess(decessNew);
            infirmiere = em.merge(infirmiere);
            for (Chirurgie chirurgiesOldChirurgie : chirurgiesOld) {
                if (!chirurgiesNew.contains(chirurgiesOldChirurgie)) {
                    chirurgiesOldChirurgie.setInfirmiere(null);
                    chirurgiesOldChirurgie = em.merge(chirurgiesOldChirurgie);
                }
            }
            for (Chirurgie chirurgiesNewChirurgie : chirurgiesNew) {
                if (!chirurgiesOld.contains(chirurgiesNewChirurgie)) {
                    Infirmiere oldInfirmiereOfChirurgiesNewChirurgie = chirurgiesNewChirurgie.getInfirmiere();
                    chirurgiesNewChirurgie.setInfirmiere(infirmiere);
                    chirurgiesNewChirurgie = em.merge(chirurgiesNewChirurgie);
                    if (oldInfirmiereOfChirurgiesNewChirurgie != null && !oldInfirmiereOfChirurgiesNewChirurgie.equals(infirmiere)) {
                        oldInfirmiereOfChirurgiesNewChirurgie.getChirurgies().remove(chirurgiesNewChirurgie);
                        oldInfirmiereOfChirurgiesNewChirurgie = em.merge(oldInfirmiereOfChirurgiesNewChirurgie);
                    }
                }
            }
            for (Accouchement accouchementsOldAccouchement : accouchementsOld) {
                if (!accouchementsNew.contains(accouchementsOldAccouchement)) {
                    accouchementsOldAccouchement.setInfirmiere(null);
                    accouchementsOldAccouchement = em.merge(accouchementsOldAccouchement);
                }
            }
            for (Accouchement accouchementsNewAccouchement : accouchementsNew) {
                if (!accouchementsOld.contains(accouchementsNewAccouchement)) {
                    Infirmiere oldInfirmiereOfAccouchementsNewAccouchement = accouchementsNewAccouchement.getInfirmiere();
                    accouchementsNewAccouchement.setInfirmiere(infirmiere);
                    accouchementsNewAccouchement = em.merge(accouchementsNewAccouchement);
                    if (oldInfirmiereOfAccouchementsNewAccouchement != null && !oldInfirmiereOfAccouchementsNewAccouchement.equals(infirmiere)) {
                        oldInfirmiereOfAccouchementsNewAccouchement.getAccouchements().remove(accouchementsNewAccouchement);
                        oldInfirmiereOfAccouchementsNewAccouchement = em.merge(oldInfirmiereOfAccouchementsNewAccouchement);
                    }
                }
            }
            for (Deces decessOldDeces : decessOld) {
                if (!decessNew.contains(decessOldDeces)) {
                    decessOldDeces.setInfirmiere(null);
                    decessOldDeces = em.merge(decessOldDeces);
                }
            }
            for (Deces decessNewDeces : decessNew) {
                if (!decessOld.contains(decessNewDeces)) {
                    Infirmiere oldInfirmiereOfDecessNewDeces = decessNewDeces.getInfirmiere();
                    decessNewDeces.setInfirmiere(infirmiere);
                    decessNewDeces = em.merge(decessNewDeces);
                    if (oldInfirmiereOfDecessNewDeces != null && !oldInfirmiereOfDecessNewDeces.equals(infirmiere)) {
                        oldInfirmiereOfDecessNewDeces.getDecess().remove(decessNewDeces);
                        oldInfirmiereOfDecessNewDeces = em.merge(oldInfirmiereOfDecessNewDeces);
                    }
                }
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
                Long id = infirmiere.getId();
                if (findInfirmiere(id) == null) {
                    throw new NonexistentEntityException("The infirmiere with id " + id + " no longer exists.");
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
            Infirmiere infirmiere;
            try {
                infirmiere = em.getReference(Infirmiere.class, id);
                infirmiere.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The infirmiere with id " + id + " no longer exists.", enfe);
            }
            List<Chirurgie> chirurgies = infirmiere.getChirurgies();
            for (Chirurgie chirurgiesChirurgie : chirurgies) {
                chirurgiesChirurgie.setInfirmiere(null);
                chirurgiesChirurgie = em.merge(chirurgiesChirurgie);
            }
            List<Accouchement> accouchements = infirmiere.getAccouchements();
            for (Accouchement accouchementsAccouchement : accouchements) {
                accouchementsAccouchement.setInfirmiere(null);
                accouchementsAccouchement = em.merge(accouchementsAccouchement);
            }
            List<Deces> decess = infirmiere.getDecess();
            for (Deces decessDeces : decess) {
                decessDeces.setInfirmiere(null);
                decessDeces = em.merge(decessDeces);
            }
            em.remove(infirmiere);
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

    public List<Infirmiere> findInfirmiereEntities() {
        return findInfirmiereEntities(true, -1, -1);
    }

    public List<Infirmiere> findInfirmiereEntities(int maxResults, int firstResult) {
        return findInfirmiereEntities(false, maxResults, firstResult);
    }

    private List<Infirmiere> findInfirmiereEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Infirmiere.class));
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

    public Infirmiere findInfirmiere(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Infirmiere.class, id);
        } finally {
            em.close();
        }
    }

    public int getInfirmiereCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Infirmiere> rt = cq.from(Infirmiere.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
