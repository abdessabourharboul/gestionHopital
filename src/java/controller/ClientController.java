/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Client;
import bean.User;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import service.ClientFacade;
import service.UserFacade;

/**
 *
 * @author asus
 */
@Named(value = "clientController")
@SessionScoped
public class ClientController implements Serializable {

    private Client client;
    private List<Client> clients;
    @EJB
    private ClientFacade clientFacade;
    @EJB
    private UserFacade userFacade;
    private String confirmedPassword; 

    
    public boolean isValidUser() {
        boolean valid = false;
        String username = client.getId();
        if (username != null) {
            if (clientFacade.find(username) == null) {
                valid = true;
            } else {
                FacesMessage msg = new FacesMessage();
                msg.setDetail("The username you have entered is already in use.");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
        return valid;
    }

    public String save() {
        if (isValidUser()) {
            if (client.getPassword().equals(confirmedPassword)) {
                User myUser = new User();
                myUser.setId(client.getId());
                myUser.setPassword(client.getPassword());
                myUser.setClient(client);
                clientFacade.create(client);
                userFacade.create(myUser);
                return "/index?faces-redirect=true";
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    
    
    public List<Client> getClients() {
        if (clients == null) {
            clients = clientFacade.findAll();
        }
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Client getClient() {
        if (client == null) {
            client = new Client();
        }
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ClientFacade getClientFacade() {
        return clientFacade;
    }

    public void setClientFacade(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    /**
     * Creates a new instance of ClientController
     */
    public ClientController() {
    }

}
