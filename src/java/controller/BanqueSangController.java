/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.BanqueSang;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.BanqueSangFacade;

/**
 *
 * @author asus
 */
@Named(value = "banqueSangController")
@SessionScoped
public class BanqueSangController implements Serializable {

    private BanqueSang banqueSang;
    private List<BanqueSang> banqueSangs;
    @EJB
    private BanqueSangFacade banqueSangFacade;

    public void save() {
        banqueSang.setStatut(banqueSangFacade.checkStatut(banqueSang.getQuantite()));
        banqueSangFacade.create(banqueSang);
        banqueSangs.add(banqueSang);
        banqueSang = null;
    }

    public BanqueSang getBanqueSang() {
        if (banqueSang == null) {
            banqueSang = new BanqueSang();
        }
        return banqueSang;
    }

    public void setBanqueSang(BanqueSang banqueSang) {
        this.banqueSang = banqueSang;
    }

    public List<BanqueSang> getBanqueSangs() {
        banqueSangs = banqueSangFacade.findAll();
        return banqueSangs;
    }

    public void setBanqueSangs(List<BanqueSang> banqueSangs) {
        this.banqueSangs = banqueSangs;
    }

    public BanqueSangFacade getBanqueSangFacade() {
        return banqueSangFacade;
    }

    public void setBanqueSangFacade(BanqueSangFacade banqueSangFacade) {
        this.banqueSangFacade = banqueSangFacade;
    }

    /**
     * Creates a new instance of BanqueSangController
     */
    public BanqueSangController() {
    }

}
