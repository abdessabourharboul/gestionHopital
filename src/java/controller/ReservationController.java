/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Chambre;
import bean.Reservation;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import service.ReservationFacade;

/**
 *
 * @author asus
 */
@Named(value = "reservationController")
@SessionScoped
public class ReservationController implements Serializable {

    Reservation reservation;
    List<Reservation> reservations;
    List<Chambre> myChambres;
    private Chambre ch;
    @EJB
    ReservationFacade reservationFacade;
    private Date dateEntre ;
    private Date dateSortie;
    
    public void searshResS(){
       reservations= reservationFacade.searshResS(dateEntre, dateSortie);
       reservations=null;
    }
    

    public Chambre getCh() {
        return ch;
    }

    public void setCh(Chambre ch) {
        this.ch = ch;
    }

    public void dddd(){
        ch.setType("Classe1");
        reservationFacade.chambreActuelVide(ch.getType());
    }
    public void save() {
        String estOccupe = reservationFacade.isOccupied(reservation.getEntreedate(),
                reservation.getSortiedate(),
                reservation.getChambre().getId());
        System.out.println(estOccupe);
        if (estOccupe.equals("nonOccupe")) {
            reservationFacade.CalculPrixReservation(reservation);
            reservationFacade.create(reservation);
            reservations.add(reservation);
            reservation = null;
        } else if (estOccupe.equals("Occupe")) {
            FacesMessage msg = new FacesMessage();
            msg.setDetail("Cette chambre est ocuppée pendant cette date");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage("Chambre Ocuppée", msg);
        }
        reservation = null;
    }

    public List<Chambre> getMyChambres() {
        if (myChambres == null) {
            myChambres = new ArrayList<>();
        }
        return myChambres;
    }

    public void setMyChambres(List<Chambre> myChambres) {
        this.myChambres = myChambres;
    }

    public List<Chambre> findChambresByType() {
        myChambres = reservationFacade.findChambresByType(reservation.getChambre().getType());
        for (int i = 0; i < myChambres.size(); i++) {
            Chambre get = myChambres.get(i);
            System.out.println(get.getId());
        }
        return myChambres;
    }

    public String isOccupied() {
        String estOccupe = reservationFacade.isOccupied(reservation.getEntreedate(),
                reservation.getSortiedate(),
                reservation.getChambre().getId());
        System.out.println(estOccupe);
        if (estOccupe.equals("nonOccupe")) {
            reservationFacade.create(reservation);
        }
        return estOccupe;
    }

    public Reservation getReservation() {
        if (reservation == null) {
            reservation = new Reservation();
        }
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public List<Reservation> getReservations() {
        if (reservations == null) {
            reservations = reservationFacade.findAll();
        }
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
public void hola() {
        List<Reservation> dd = reservationFacade.hola();
//        System.out.println(dd);
        for (int i = 0; i < dd.size(); i++) {
            Reservation get = dd.get(i);
            System.out.println(get.toString());
        }
    }
    public ReservationFacade getReservationFacade() {
        return reservationFacade;
    }

    public void setReservationFacade(ReservationFacade reservationFacade) {
        this.reservationFacade = reservationFacade;
    }

    /**
     * Creates a new instance of ReservationController
     */
    public ReservationController() {
    }

    public Date getDateEntre() {
        return dateEntre;
    }

    public void setDateEntre(Date dateEntre) {
        this.dateEntre = dateEntre;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    
}
