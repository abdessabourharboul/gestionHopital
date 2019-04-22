/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Chambre;
import bean.Reservation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author asus
 */
@Stateless
public class ReservationFacade extends AbstractFacade<Reservation> {

    @PersistenceContext(unitName = "gestionHopitalPU")
    private EntityManager em;
    @EJB
    ReservationFacade reservationFacade;

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
public List<Reservation> hola() {
        String req = "SELECT r  FROM Reservation r  WHERE 1=1"
                + " AND r.sortiedate >= '2016-04-01' AND r.entreedate <= '2016-04-08' ";
//                + "GROUP BY date";
//        return em.createNativeQuery(req).getResultList();
        return em.createQuery(req).getResultList();
    }
public List<Reservation> searshResS(Date dateEntre ,Date dateSortie) {
        String req = "SELECT r  FROM Reservation r  WHERE 1=1"
                + " AND r.sortiedate >= " + dateSortie + " AND r.entreedate <= " + dateEntre;
//                + "GROUP BY date";
//        return em.createNativeQuery(req).getResultList();
        return em.createQuery(req).getResultList();
    }

    public List<Chambre> chambreActuelVide(String type) {
        List<Chambre> myChambres = findChambresByType(type);
        List<Chambre> Chambres = new ArrayList();
        Date dateAct = new Date();
        Date datec = new Date(2000, 01, 01);
        for (int i = 0; i < myChambres.size(); i++) {
            Chambre get = myChambres.get(i);
            if (get != null) {
                Chambre ch = new Chambre();
                List<Reservation> reservations = new ArrayList();
                for (int j = 0; j < get.getReservations().size(); j++) {
                    Reservation get1 = get.getReservations().get(j);
                    if (get1 != null) {
                        if (get1.getEntreedate() == datec) {
                            Reservation res = new Reservation();
                            reservations.add(get1);
                        }
                    }
                    datec.setDate(datec.getDate() + 1);
                }
                ch.setReservations(reservations);
                Chambres.add(ch);
            }
        }
        
        
        return Chambres;
    }
    
    public int occupe(Date dateFin) {

        Date dateActuel = new Date();
        if (dateFin.after(dateActuel)) {
            return 1;
        } else {
            return -1;
        }
    }
    public Reservation CalculPrixReservation(Reservation reservation) {
        long prix;
        long nbDays = getDifferenceDays(reservation.getEntreedate(), reservation.getSortiedate());
        long tarifType = 0;
        if (reservation.getChambre().getType().equals("Avec un lit")) {
            tarifType = 100;
        } else if (reservation.getChambre().getType().equals("Avec deux lits")) {
            tarifType = 50;
        } else if (reservation.getChambre().getType().equals("Avec quatres lits")) {
            tarifType = 25;
        } else {
        }
        prix = nbDays * tarifType;
        reservation.setPrix(prix);
        return reservation;
    }

    public List<Chambre> findChambresByType(String type) {
        String requette = "select u from Chambre u where u.type = ?1";
        TypedQuery<Chambre> query = em.createQuery(requette, Chambre.class);
        query.setParameter(1, type);
        return query.getResultList();
    }

    public List<Reservation> isoccupied(Long chambre) {
        List<Reservation> ml = findAll();
        List<Reservation> ml1 = new ArrayList<>();
        for (Reservation get : ml) {
            if (get.getChambre().getId() == chambre) {
                ml1.add(get);
            }
        }
        return ml1;
    }

    public String isOccupied(Date entree, Date sortie, Long chambre) {
        List<Reservation> maListe = isoccupied(chambre);
        for (Reservation get : maListe) {
            System.out.println(get.getId());
        }
        String isOcuppe = "Occupe";
        if (maListe == null) {
            isOcuppe = "nonOccupe";
        }
        for (Reservation reservation : maListe) {
            if ((entree.before(reservation.getEntreedate())
                    && sortie.before(reservation.getEntreedate()))) {
                isOcuppe = "nonOccupe";
            } else if ((entree.after(reservation.getSortiedate())
                    && sortie.after(reservation.getSortiedate()))) {
                isOcuppe = "nonOccupe";
            }
        }
        return isOcuppe;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservationFacade() {
        super(Reservation.class);
    }

    
}
