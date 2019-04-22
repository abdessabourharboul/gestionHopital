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
public class MedecinJpaController implements Serializable {

    public MedecinJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Medecin medecin) throws RollbackFailureException, Exception {
        if (medecin.getChirurgies() == null) {
            medecin.setChirurgies(new ArrayList<Chirurgie>());
        }
        if (medecin.getAccouchements() == null) {
            medecin.setAccouchements(new ArrayList<Accouchement>());
        }
        if (medecin.getDecess() == null) {
            medecin.setDecess(new ArrayList<Deces>());
        }
        if (medecin.getPrelevements() == null) {
            medecin.setPrelevements(new ArrayList<Prelevement>());
        }
        if (medecin.getAppointements() == null) {
            medecin.setAppointements(new ArrayList<Appointement>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Chirurgie> attachedChirurgies = new ArrayList<Chirurgie>();
            for (Chirurgie chirurgiesChirurgieToAttach : medecin.getChirurgies()) {
                chirurgiesChirurgieToAttach = em.getReference(chirurgiesChirurgieToAttach.getClass(), chirurgiesChirurgieToAttach.getId());
                attachedChirurgies.add(chirurgiesChirurgieToAttach);
            }
            medecin.setChirurgies(attachedChirurgies);
            List<Accouchement> attachedAccouchements = new ArrayList<Accouchement>();
            for (Accouchement accouchementsAccouchementToAttach : medecin.getAccouchements()) {
                accouchementsAccouchementToAttach = em.getReference(accouchementsAccouchementToAttach.getClass(), accouchementsAccouchementToAttach.getId());
                attachedAccouchements.add(accouchementsAccouchementToAttach);
            }
            medecin.setAccouchements(attachedAccouchements);
            List<Deces> attachedDecess = new ArrayList<Deces>();
            for (Deces decessDecesToAttach : medecin.getDecess()) {
                decessDecesToAttach = em.getReference(decessDecesToAttach.getClass(), decessDecesToAttach.getId());
                attachedDecess.add(decessDecesToAttach);
            }
            medecin.setDecess(attachedDecess);
            List<Prelevement> attachedPrelevements = new ArrayList<Prelevement>();
            for (Prelevement prelevementsPrelevementToAttach : medecin.getPrelevements()) {
                prelevementsPrelevementToAttach = em.getReference(prelevementsPrelevementToAttach.getClass(), prelevementsPrelevementToAttach.getId());
                attachedPrelevements.add(prelevementsPrelevementToAttach);
            }
            medecin.setPrelevements(attachedPrelevements);
            List<Appointement> attachedAppointements = new ArrayList<Appointement>();
            for (Appointement appointementsAppointementToAttach : medecin.getAppointements()) {
                appointementsAppointementToAttach = em.getReference(appointementsAppointementToAttach.getClass(), appointementsAppointementToAttach.getId());
                attachedAppointements.add(appointementsAppointementToAttach);
            }
            medecin.setAppointements(attachedAppointements);
            em.persist(medecin);
            for (Chirurgie chirurgiesChirurgie : medecin.getChirurgies()) {
                Medecin oldMedecinOfChirurgiesChirurgie = chirurgiesChirurgie.getMedecin();
                chirurgiesChirurgie.setMedecin(medecin);
                chirurgiesChirurgie = em.merge(chirurgiesChirurgie);
                if (oldMedecinOfChirurgiesChirurgie != null) {
                    oldMedecinOfChirurgiesChirurgie.getChirurgies().remove(chirurgiesChirurgie);
                    oldMedecinOfChirurgiesChirurgie = em.merge(oldMedecinOfChirurgiesChirurgie);
                }
            }
            for (Accouchement accouchementsAccouchement : medecin.getAccouchements()) {
                Medecin oldMedecinOfAccouchementsAccouchement = accouchementsAccouchement.getMedecin();
                accouchementsAccouchement.setMedecin(medecin);
                accouchementsAccouchement = em.merge(accouchementsAccouchement);
                if (oldMedecinOfAccouchementsAccouchement != null) {
                    oldMedecinOfAccouchementsAccouchement.getAccouchements().remove(accouchementsAccouchement);
                    oldMedecinOfAccouchementsAccouchement = em.merge(oldMedecinOfAccouchementsAccouchement);
                }
            }
            for (Deces decessDeces : medecin.getDecess()) {
                Medecin oldMedecinOfDecessDeces = decessDeces.getMedecin();
                decessDeces.setMedecin(medecin);
                decessDeces = em.merge(decessDeces);
                if (oldMedecinOfDecessDeces != null) {
                    oldMedecinOfDecessDeces.getDecess().remove(decessDeces);
                    oldMedecinOfDecessDeces = em.merge(oldMedecinOfDecessDeces);
                }
            }
            for (Prelevement prelevementsPrelevement : medecin.getPrelevements()) {
                Medecin oldMedecinOfPrelevementsPrelevement = prelevementsPrelevement.getMedecin();
                prelevementsPrelevement.setMedecin(medecin);
                prelevementsPrelevement = em.merge(prelevementsPrelevement);
                if (oldMedecinOfPrelevementsPrelevement != null) {
                    oldMedecinOfPrelevementsPrelevement.getPrelevements().remove(prelevementsPrelevement);
                    oldMedecinOfPrelevementsPrelevement = em.merge(oldMedecinOfPrelevementsPrelevement);
                }
            }
            for (Appointement appointementsAppointement : medecin.getAppointements()) {
                Medecin oldMedecinOfAppointementsAppointement = appointementsAppointement.getMedecin();
                appointementsAppointement.setMedecin(medecin);
                appointementsAppointement = em.merge(appointementsAppointement);
                if (oldMedecinOfAppointementsAppointement != null) {
                    oldMedecinOfAppointementsAppointement.getAppointements().remove(appointementsAppointement);
                    oldMedecinOfAppointementsAppointement = em.merge(oldMedecinOfAppointementsAppointement);
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

    public void edit(Medecin medecin) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Medecin persistentMedecin = em.find(Medecin.class, medecin.getId());
            List<Chirurgie> chirurgiesOld = persistentMedecin.getChirurgies();
            List<Chirurgie> chirurgiesNew = medecin.getChirurgies();
            List<Accouchement> accouchementsOld = persistentMedecin.getAccouchements();
            List<Accouchement> accouchementsNew = medecin.getAccouchements();
            List<Deces> decessOld = persistentMedecin.getDecess();
            List<Deces> decessNew = medecin.getDecess();
            List<Prelevement> prelevementsOld = persistentMedecin.getPrelevements();
            List<Prelevement> prelevementsNew = medecin.getPrelevements();
            List<Appointement> appointementsOld = persistentMedecin.getAppointements();
            List<Appointement> appointementsNew = medecin.getAppointements();
            List<Chirurgie> attachedChirurgiesNew = new ArrayList<Chirurgie>();
            for (Chirurgie chirurgiesNewChirurgieToAttach : chirurgiesNew) {
                chirurgiesNewChirurgieToAttach = em.getReference(chirurgiesNewChirurgieToAttach.getClass(), chirurgiesNewChirurgieToAttach.getId());
                attachedChirurgiesNew.add(chirurgiesNewChirurgieToAttach);
            }
            chirurgiesNew = attachedChirurgiesNew;
            medecin.setChirurgies(chirurgiesNew);
            List<Accouchement> attachedAccouchementsNew = new ArrayList<Accouchement>();
            for (Accouchement accouchementsNewAccouchementToAttach : accouchementsNew) {
                accouchementsNewAccouchementToAttach = em.getReference(accouchementsNewAccouchementToAttach.getClass(), accouchementsNewAccouchementToAttach.getId());
                attachedAccouchementsNew.add(accouchementsNewAccouchementToAttach);
            }
            accouchementsNew = attachedAccouchementsNew;
            medecin.setAccouchements(accouchementsNew);
            List<Deces> attachedDecessNew = new ArrayList<Deces>();
            for (Deces decessNewDecesToAttach : decessNew) {
                decessNewDecesToAttach = em.getReference(decessNewDecesToAttach.getClass(), decessNewDecesToAttach.getId());
                attachedDecessNew.add(decessNewDecesToAttach);
            }
            decessNew = attachedDecessNew;
            medecin.setDecess(decessNew);
            List<Prelevement> attachedPrelevementsNew = new ArrayList<Prelevement>();
            for (Prelevement prelevementsNewPrelevementToAttach : prelevementsNew) {
                prelevementsNewPrelevementToAttach = em.getReference(prelevementsNewPrelevementToAttach.getClass(), prelevementsNewPrelevementToAttach.getId());
                attachedPrelevementsNew.add(prelevementsNewPrelevementToAttach);
            }
            prelevementsNew = attachedPrelevementsNew;
            medecin.setPrelevements(prelevementsNew);
            List<Appointement> attachedAppointementsNew = new ArrayList<Appointement>();
            for (Appointement appointementsNewAppointementToAttach : appointementsNew) {
                appointementsNewAppointementToAttach = em.getReference(appointementsNewAppointementToAttach.getClass(), appointementsNewAppointementToAttach.getId());
                attachedAppointementsNew.add(appointementsNewAppointementToAttach);
            }
            appointementsNew = attachedAppointementsNew;
            medecin.setAppointements(appointementsNew);
            medecin = em.merge(medecin);
            for (Chirurgie chirurgiesOldChirurgie : chirurgiesOld) {
                if (!chirurgiesNew.contains(chirurgiesOldChirurgie)) {
                    chirurgiesOldChirurgie.setMedecin(null);
                    chirurgiesOldChirurgie = em.merge(chirurgiesOldChirurgie);
                }
            }
            for (Chirurgie chirurgiesNewChirurgie : chirurgiesNew) {
                if (!chirurgiesOld.contains(chirurgiesNewChirurgie)) {
                    Medecin oldMedecinOfChirurgiesNewChirurgie = chirurgiesNewChirurgie.getMedecin();
                    chirurgiesNewChirurgie.setMedecin(medecin);
                    chirurgiesNewChirurgie = em.merge(chirurgiesNewChirurgie);
                    if (oldMedecinOfChirurgiesNewChirurgie != null && !oldMedecinOfChirurgiesNewChirurgie.equals(medecin)) {
                        oldMedecinOfChirurgiesNewChirurgie.getChirurgies().remove(chirurgiesNewChirurgie);
                        oldMedecinOfChirurgiesNewChirurgie = em.merge(oldMedecinOfChirurgiesNewChirurgie);
                    }
                }
            }
            for (Accouchement accouchementsOldAccouchement : accouchementsOld) {
                if (!accouchementsNew.contains(accouchementsOldAccouchement)) {
                    accouchementsOldAccouchement.setMedecin(null);
                    accouchementsOldAccouchement = em.merge(accouchementsOldAccouchement);
                }
            }
            for (Accouchement accouchementsNewAccouchement : accouchementsNew) {
                if (!accouchementsOld.contains(accouchementsNewAccouchement)) {
                    Medecin oldMedecinOfAccouchementsNewAccouchement = accouchementsNewAccouchement.getMedecin();
                    accouchementsNewAccouchement.setMedecin(medecin);
                    accouchementsNewAccouchement = em.merge(accouchementsNewAccouchement);
                    if (oldMedecinOfAccouchementsNewAccouchement != null && !oldMedecinOfAccouchementsNewAccouchement.equals(medecin)) {
                        oldMedecinOfAccouchementsNewAccouchement.getAccouchements().remove(accouchementsNewAccouchement);
                        oldMedecinOfAccouchementsNewAccouchement = em.merge(oldMedecinOfAccouchementsNewAccouchement);
                    }
                }
            }
            for (Deces decessOldDeces : decessOld) {
                if (!decessNew.contains(decessOldDeces)) {
                    decessOldDeces.setMedecin(null);
                    decessOldDeces = em.merge(decessOldDeces);
                }
            }
            for (Deces decessNewDeces : decessNew) {
                if (!decessOld.contains(decessNewDeces)) {
                    Medecin oldMedecinOfDecessNewDeces = decessNewDeces.getMedecin();
                    decessNewDeces.setMedecin(medecin);
                    decessNewDeces = em.merge(decessNewDeces);
                    if (oldMedecinOfDecessNewDeces != null && !oldMedecinOfDecessNewDeces.equals(medecin)) {
                        oldMedecinOfDecessNewDeces.getDecess().remove(decessNewDeces);
                        oldMedecinOfDecessNewDeces = em.merge(oldMedecinOfDecessNewDeces);
                    }
                }
            }
            for (Prelevement prelevementsOldPrelevement : prelevementsOld) {
                if (!prelevementsNew.contains(prelevementsOldPrelevement)) {
                    prelevementsOldPrelevement.setMedecin(null);
                    prelevementsOldPrelevement = em.merge(prelevementsOldPrelevement);
                }
            }
            for (Prelevement prelevementsNewPrelevement : prelevementsNew) {
                if (!prelevementsOld.contains(prelevementsNewPrelevement)) {
                    Medecin oldMedecinOfPrelevementsNewPrelevement = prelevementsNewPrelevement.getMedecin();
                    prelevementsNewPrelevement.setMedecin(medecin);
                    prelevementsNewPrelevement = em.merge(prelevementsNewPrelevement);
                    if (oldMedecinOfPrelevementsNewPrelevement != null && !oldMedecinOfPrelevementsNewPrelevement.equals(medecin)) {
                        oldMedecinOfPrelevementsNewPrelevement.getPrelevements().remove(prelevementsNewPrelevement);
                        oldMedecinOfPrelevementsNewPrelevement = em.merge(oldMedecinOfPrelevementsNewPrelevement);
                    }
                }
            }
            for (Appointement appointementsOldAppointement : appointementsOld) {
                if (!appointementsNew.contains(appointementsOldAppointement)) {
                    appointementsOldAppointement.setMedecin(null);
                    appointementsOldAppointement = em.merge(appointementsOldAppointement);
                }
            }
            for (Appointement appointementsNewAppointement : appointementsNew) {
                if (!appointementsOld.contains(appointementsNewAppointement)) {
                    Medecin oldMedecinOfAppointementsNewAppointement = appointementsNewAppointement.getMedecin();
                    appointementsNewAppointement.setMedecin(medecin);
                    appointementsNewAppointement = em.merge(appointementsNewAppointement);
                    if (oldMedecinOfAppointementsNewAppointement != null && !oldMedecinOfAppointementsNewAppointement.equals(medecin)) {
                        oldMedecinOfAppointementsNewAppointement.getAppointements().remove(appointementsNewAppointement);
                        oldMedecinOfAppointementsNewAppointement = em.merge(oldMedecinOfAppointementsNewAppointement);
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
                Long id = medecin.getId();
                if (findMedecin(id) == null) {
                    throw new NonexistentEntityException("The medecin with id " + id + " no longer exists.");
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
            Medecin medecin;
            try {
                medecin = em.getReference(Medecin.class, id);
                medecin.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medecin with id " + id + " no longer exists.", enfe);
            }
            List<Chirurgie> chirurgies = medecin.getChirurgies();
            for (Chirurgie chirurgiesChirurgie : chirurgies) {
                chirurgiesChirurgie.setMedecin(null);
                chirurgiesChirurgie = em.merge(chirurgiesChirurgie);
            }
            List<Accouchement> accouchements = medecin.getAccouchements();
            for (Accouchement accouchementsAccouchement : accouchements) {
                accouchementsAccouchement.setMedecin(null);
                accouchementsAccouchement = em.merge(accouchementsAccouchement);
            }
            List<Deces> decess = medecin.getDecess();
            for (Deces decessDeces : decess) {
                decessDeces.setMedecin(null);
                decessDeces = em.merge(decessDeces);
            }
            List<Prelevement> prelevements = medecin.getPrelevements();
            for (Prelevement prelevementsPrelevement : prelevements) {
                prelevementsPrelevement.setMedecin(null);
                prelevementsPrelevement = em.merge(prelevementsPrelevement);
            }
            List<Appointement> appointements = medecin.getAppointements();
            for (Appointement appointementsAppointement : appointements) {
                appointementsAppointement.setMedecin(null);
                appointementsAppointement = em.merge(appointementsAppointement);
            }
            em.remove(medecin);
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

    public List<Medecin> findMedecinEntities() {
        return findMedecinEntities(true, -1, -1);
    }

    public List<Medecin> findMedecinEntities(int maxResults, int firstResult) {
        return findMedecinEntities(false, maxResults, firstResult);
    }

    private List<Medecin> findMedecinEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Medecin.class));
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

    public Medecin findMedecin(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Medecin.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedecinCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Medecin> rt = cq.from(Medecin.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
