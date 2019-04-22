/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.User;
import controler.util.SessionUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import service.ClientFacade;
import service.UserFacade;

/**
 *
 * @author asus
 */
@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {

    private User user;
    private String newPassword, confNewPassword;
    @EJB
    private UserFacade userFacade;
    @EJB
    private ClientFacade clientFacade;

    public String logIn() {
        User myUser = userFacade.find(user.getId());
        FacesContext context = FacesContext.getCurrentInstance();
        if (myUser == null) {
            context.addMessage(null, new FacesMessage("Unknown login, try again"));
            user.setId(null);
            user.setPassword(null);
            user.setIsLogged(false);
            return null;
        } else if (!myUser.getPassword().equals(user.getPassword())) {
            user.setIsLogged(false);
            return null;

        } else {
            SessionUtil.setAttribute("user", myUser);
            user.setId(null);
            user.setPassword(null);
            myUser.setIsLogged(true);
            userFacade.edit(myUser);
            return "securiseClient/welcomePrimefaces?faces-redirect=true";
        }
    }

    public String logOut() {
        User myUser = (User) SessionUtil.getAttribute("user");
        myUser.setIsLogged(false);
        userFacade.edit(myUser);
        SessionUtil.getSession().invalidate();
        return "/index?faces-redirect=true";
    }

    public void changePassword() {
        User myUser = (User) SessionUtil.getAttribute("user");
        if (myUser.getPassword().equals(user.getPassword()) && newPassword.equals(confNewPassword)) {
            myUser.setPassword(newPassword);
            myUser.getClient().setPassword(newPassword);
            clientFacade.edit(myUser.getClient());
            userFacade.edit(myUser);
        } else {
        }
    }

    public User getConnectedUser() {
        User myUser = (User) SessionUtil.getAttribute("user");
        return myUser;
    }

    public User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfNewPassword() {
        return confNewPassword;
    }

    public void setConfNewPassword(String confNewPassword) {
        this.confNewPassword = confNewPassword;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public ClientFacade getClientFacade() {
        return clientFacade;
    }

    public void setClientFacade(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    /**
     * Creates a new instance of UserController
     */
    public UserController() {
    }

}
