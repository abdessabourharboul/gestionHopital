/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import bean.exceptions.NonexistentEntityException;
import bean.exceptions.PreexistingEntityException;
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
public class ClientJpaController implements Serializable {

    public ClientJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Client client) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (client.getChirurgies() == null) {
            client.setChirurgies(new ArrayList<Chirurgie>());
        }
        if (client.getAccouchements() == null) {
            client.setAccouchements(new ArrayList<Accouchement>());
        }
        if (client.getPrelevements() == null) {
            client.setPrelevements(new ArrayList<Prelevement>());
        }
        if (client.getAppointements() == null) {
            client.setAppointements(new ArrayList<Appointement>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Deces deces = client.getDeces();
            if (deces != null) {
                deces = em.getReference(deces.getClass(), deces.getId());
                client.setDeces(deces);
            }
            List<Chirurgie> attachedChirurgies = new ArrayList<Chirurgie>();
            for (Chirurgie chirurgiesChirurgieToAttach : client.getChirurgies()) {
                chirurgiesChirurgieToAttach = em.getReference(chirurgiesChirurgieToAttach.getClass(), chirurgiesChirurgieToAttach.getId());
                attachedChirurgies.add(chirurgiesChirurgieToAttach);
            }
            client.setChirurgies(attachedChirurgies);
            List<Accouchement> attachedAccouchements = new ArrayList<Accouchement>();
            for (Accouchement accouchementsAccouchementToAttach : client.getAccouchements()) {
                accouchementsAccouchementToAttach = em.getReference(accouchementsAccouchementToAttach.getClass(), accouchementsAccouchementToAttach.getId());
                attachedAccouchements.add(accouchementsAccouchementToAttach);
            }
            client.setAccouchements(attachedAccouchements);
            List<Prelevement> attachedPrelevements = new ArrayList<Prelevement>();
            for (Prelevement prelevementsPrelevementToAttach : client.getPrelevements()) {
                prelevementsPrelevementToAttach = em.getReference(prelevementsPrelevementToAttach.getClass(), prelevementsPrelevementToAttach.getId());
                attachedPrelevements.add(prelevementsPrelevementToAttach);
            }
            client.setPrelevements(attachedPrelevements);
            List<Appointement> attachedAppointements = new ArrayList<Appointement>();
            for (Appointement appointementsAppointementToAttach : client.getAppointements()) {
                appointementsAppointementToAttach = em.getReference(appointementsAppointementToAttach.getClass(), appointementsAppointementToAttach.getId());
                attachedAppointements.add(appointementsAppointementToAttach);
            }
            client.setAppointements(attachedAppointements);
            em.persist(client);
            if (deces != null) {
                Client oldClientOfDeces = deces.getClient();
                if (oldClientOfDeces != null) {
                    oldClientOfDeces.setDeces(null);
                    oldClientOfDeces = em.merge(oldClientOfDeces);
                }
                deces.setClient(client);
                deces = em.merge(deces);
            }
            for (Chirurgie chirurgiesChirurgie : client.getChirurgies()) {
                Client oldClientOfChirurgiesChirurgie = chirurgiesChirurgie.getClient();
                chirurgiesChirurgie.setClient(client);
                chirurgiesChirurgie = em.merge(chirurgiesChirurgie);
                if (oldClientOfChirurgiesChirurgie != null) {
                    oldClientOfChirurgiesChirurgie.getChirurgies().remove(chirurgiesChirurgie);
                    oldClientOfChirurgiesChirurgie = em.merge(oldClientOfChirurgiesChirurgie);
                }
            }
            for (Accouchement accouchementsAccouchement : client.getAccouchements()) {
                Client oldClientOfAccouchementsAccouchement = accouchementsAccouchement.getClient();
                accouchementsAccouchement.setClient(client);
                accouchementsAccouchement = em.merge(accouchementsAccouchement);
                if (oldClientOfAccouchementsAccouchement != null) {
                    oldClientOfAccouchementsAccouchement.getAccouchements().remove(accouchementsAccouchement);
                    oldClientOfAccouchementsAccouchement = em.merge(oldClientOfAccouchementsAccouchement);
                }
            }
            for (Prelevement prelevementsPrelevement : client.getPrelevements()) {
                Client oldClientOfPrelevementsPrelevement = prelevementsPrelevement.getClient();
                prelevementsPrelevement.setClient(client);
                prelevementsPrelevement = em.merge(prelevementsPrelevement);
                if (oldClientOfPrelevementsPrelevement != null) {
                    oldClientOfPrelevementsPrelevement.getPrelevements().remove(prelevementsPrelevement);
                    oldClientOfPrelevementsPrelevement = em.merge(oldClientOfPrelevementsPrelevement);
                }
            }
            for (Appointement appointementsAppointement : client.getAppointements()) {
                Client oldClientOfAppointementsAppointement = appointementsAppointement.getClient();
                appointementsAppointement.setClient(client);
                appointementsAppointement = em.merge(appointementsAppointement);
                if (oldClientOfAppointementsAppointement != null) {
                    oldClientOfAppointementsAppointement.getAppointements().remove(appointementsAppointement);
                    oldClientOfAppointementsAppointement = em.merge(oldClientOfAppointementsAppointement);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findClient(client.getId()) != null) {
                throw new PreexistingEntityException("Client " + client + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Client client) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Client persistentClient = em.find(Client.class, client.getId());
            Deces decesOld = persistentClient.getDeces();
            Deces decesNew = client.getDeces();
            List<Chirurgie> chirurgiesOld = persistentClient.getChirurgies();
            List<Chirurgie> chirurgiesNew = client.getChirurgies();
            List<Accouchement> accouchementsOld = persistentClient.getAccouchements();
            List<Accouchement> accouchementsNew = client.getAccouchements();
            List<Prelevement> prelevementsOld = persistentClient.getPrelevements();
            List<Prelevement> prelevementsNew = client.getPrelevements();
            List<Appointement> appointementsOld = persistentClient.getAppointements();
            List<Appointement> appointementsNew = client.getAppointements();
            if (decesNew != null) {
                decesNew = em.getReference(decesNew.getClass(), decesNew.getId());
                client.setDeces(decesNew);
            }
            List<Chirurgie> attachedChirurgiesNew = new ArrayList<Chirurgie>();
            for (Chirurgie chirurgiesNewChirurgieToAttach : chirurgiesNew) {
                chirurgiesNewChirurgieToAttach = em.getReference(chirurgiesNewChirurgieToAttach.getClass(), chirurgiesNewChirurgieToAttach.getId());
                attachedChirurgiesNew.add(chirurgiesNewChirurgieToAttach);
            }
            chirurgiesNew = attachedChirurgiesNew;
            client.setChirurgies(chirurgiesNew);
            List<Accouchement> attachedAccouchementsNew = new ArrayList<Accouchement>();
            for (Accouchement accouchementsNewAccouchementToAttach : accouchementsNew) {
                accouchementsNewAccouchementToAttach = em.getReference(accouchementsNewAccouchementToAttach.getClass(), accouchementsNewAccouchementToAttach.getId());
                attachedAccouchementsNew.add(accouchementsNewAccouchementToAttach);
            }
            accouchementsNew = attachedAccouchementsNew;
            client.setAccouchements(accouchementsNew);
            List<Prelevement> attachedPrelevementsNew = new ArrayList<Prelevement>();
            for (Prelevement prelevementsNewPrelevementToAttach : prelevementsNew) {
                prelevementsNewPrelevementToAttach = em.getReference(prelevementsNewPrelevementToAttach.getClass(), prelevementsNewPrelevementToAttach.getId());
                attachedPrelevementsNew.add(prelevementsNewPrelevementToAttach);
            }
            prelevementsNew = attachedPrelevementsNew;
            client.setPrelevements(prelevementsNew);
            List<Appointement> attachedAppointementsNew = new ArrayList<Appointement>();
            for (Appointement appointementsNewAppointementToAttach : appointementsNew) {
                appointementsNewAppointementToAttach = em.getReference(appointementsNewAppointementToAttach.getClass(), appointementsNewAppointementToAttach.getId());
                attachedAppointementsNew.add(appointementsNewAppointementToAttach);
            }
            appointementsNew = attachedAppointementsNew;
            client.setAppointements(appointementsNew);
            client = em.merge(client);
            if (decesOld != null && !decesOld.equals(decesNew)) {
                decesOld.setClient(null);
                decesOld = em.merge(decesOld);
            }
            if (decesNew != null && !decesNew.equals(decesOld)) {
                Client oldClientOfDeces = decesNew.getClient();
                if (oldClientOfDeces != null) {
                    oldClientOfDeces.setDeces(null);
                    oldClientOfDeces = em.merge(oldClientOfDeces);
                }
                decesNew.setClient(client);
                decesNew = em.merge(decesNew);
            }
            for (Chirurgie chirurgiesOldChirurgie : chirurgiesOld) {
                if (!chirurgiesNew.contains(chirurgiesOldChirurgie)) {
                    chirurgiesOldChirurgie.setClient(null);
                    chirurgiesOldChirurgie = em.merge(chirurgiesOldChirurgie);
                }
            }
            for (Chirurgie chirurgiesNewChirurgie : chirurgiesNew) {
                if (!chirurgiesOld.contains(chirurgiesNewChirurgie)) {
                    Client oldClientOfChirurgiesNewChirurgie = chirurgiesNewChirurgie.getClient();
                    chirurgiesNewChirurgie.setClient(client);
                    chirurgiesNewChirurgie = em.merge(chirurgiesNewChirurgie);
                    if (oldClientOfChirurgiesNewChirurgie != null && !oldClientOfChirurgiesNewChirurgie.equals(client)) {
                        oldClientOfChirurgiesNewChirurgie.getChirurgies().remove(chirurgiesNewChirurgie);
                        oldClientOfChirurgiesNewChirurgie = em.merge(oldClientOfChirurgiesNewChirurgie);
                    }
                }
            }
            for (Accouchement accouchementsOldAccouchement : accouchementsOld) {
                if (!accouchementsNew.contains(accouchementsOldAccouchement)) {
                    accouchementsOldAccouchement.setClient(null);
                    accouchementsOldAccouchement = em.merge(accouchementsOldAccouchement);
                }
            }
            for (Accouchement accouchementsNewAccouchement : accouchementsNew) {
                if (!accouchementsOld.contains(accouchementsNewAccouchement)) {
                    Client oldClientOfAccouchementsNewAccouchement = accouchementsNewAccouchement.getClient();
                    accouchementsNewAccouchement.setClient(client);
                    accouchementsNewAccouchement = em.merge(accouchementsNewAccouchement);
                    if (oldClientOfAccouchementsNewAccouchement != null && !oldClientOfAccouchementsNewAccouchement.equals(client)) {
                        oldClientOfAccouchementsNewAccouchement.getAccouchements().remove(accouchementsNewAccouchement);
                        oldClientOfAccouchementsNewAccouchement = em.merge(oldClientOfAccouchementsNewAccouchement);
                    }
                }
            }
            for (Prelevement prelevementsOldPrelevement : prelevementsOld) {
                if (!prelevementsNew.contains(prelevementsOldPrelevement)) {
                    prelevementsOldPrelevement.setClient(null);
                    prelevementsOldPrelevement = em.merge(prelevementsOldPrelevement);
                }
            }
            for (Prelevement prelevementsNewPrelevement : prelevementsNew) {
                if (!prelevementsOld.contains(prelevementsNewPrelevement)) {
                    Client oldClientOfPrelevementsNewPrelevement = prelevementsNewPrelevement.getClient();
                    prelevementsNewPrelevement.setClient(client);
                    prelevementsNewPrelevement = em.merge(prelevementsNewPrelevement);
                    if (oldClientOfPrelevementsNewPrelevement != null && !oldClientOfPrelevementsNewPrelevement.equals(client)) {
                        oldClientOfPrelevementsNewPrelevement.getPrelevements().remove(prelevementsNewPrelevement);
                        oldClientOfPrelevementsNewPrelevement = em.merge(oldClientOfPrelevementsNewPrelevement);
                    }
                }
            }
            for (Appointement appointementsOldAppointement : appointementsOld) {
                if (!appointementsNew.contains(appointementsOldAppointement)) {
                    appointementsOldAppointement.setClient(null);
                    appointementsOldAppointement = em.merge(appointementsOldAppointement);
                }
            }
            for (Appointement appointementsNewAppointement : appointementsNew) {
                if (!appointementsOld.contains(appointementsNewAppointement)) {
                    Client oldClientOfAppointementsNewAppointement = appointementsNewAppointement.getClient();
                    appointementsNewAppointement.setClient(client);
                    appointementsNewAppointement = em.merge(appointementsNewAppointement);
                    if (oldClientOfAppointementsNewAppointement != null && !oldClientOfAppointementsNewAppointement.equals(client)) {
                        oldClientOfAppointementsNewAppointement.getAppointements().remove(appointementsNewAppointement);
                        oldClientOfAppointementsNewAppointement = em.merge(oldClientOfAppointementsNewAppointement);
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
                String id = client.getId();
                if (findClient(id) == null) {
                    throw new NonexistentEntityException("The client with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Client client;
            try {
                client = em.getReference(Client.class, id);
                client.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The client with id " + id + " no longer exists.", enfe);
            }
            Deces deces = client.getDeces();
            if (deces != null) {
                deces.setClient(null);
                deces = em.merge(deces);
            }
            List<Chirurgie> chirurgies = client.getChirurgies();
            for (Chirurgie chirurgiesChirurgie : chirurgies) {
                chirurgiesChirurgie.setClient(null);
                chirurgiesChirurgie = em.merge(chirurgiesChirurgie);
            }
            List<Accouchement> accouchements = client.getAccouchements();
            for (Accouchement accouchementsAccouchement : accouchements) {
                accouchementsAccouchement.setClient(null);
                accouchementsAccouchement = em.merge(accouchementsAccouchement);
            }
            List<Prelevement> prelevements = client.getPrelevements();
            for (Prelevement prelevementsPrelevement : prelevements) {
                prelevementsPrelevement.setClient(null);
                prelevementsPrelevement = em.merge(prelevementsPrelevement);
            }
            List<Appointement> appointements = client.getAppointements();
            for (Appointement appointementsAppointement : appointements) {
                appointementsAppointement.setClient(null);
                appointementsAppointement = em.merge(appointementsAppointement);
            }
            em.remove(client);
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

    public List<Client> findClientEntities() {
        return findClientEntities(true, -1, -1);
    }

    public List<Client> findClientEntities(int maxResults, int firstResult) {
        return findClientEntities(false, maxResults, firstResult);
    }

    private List<Client> findClientEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Client.class));
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

    public Client findClient(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Client.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Client> rt = cq.from(Client.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
