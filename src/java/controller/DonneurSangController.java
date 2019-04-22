/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.BanqueSang;
import bean.DonneurSang;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.BanqueSangFacade;
import service.DonneurSangFacade;

/**
 *
 * @author asus
 */
@Named(value = "donneurSangController")
@SessionScoped
public class DonneurSangController implements Serializable {
    
    private DonneurSang donneurSang;
    private BanqueSang banqueSang;
    private List<DonneurSang> donneurSangs;
    @EJB
    private DonneurSangFacade donneurSangFacade;
    @EJB
    private BanqueSangFacade banqueSangFacade;
    
    public void save() {
        banqueSang = banqueSangFacade.findBanqueSangByGroupeSanguin(donneurSang.getGroupeSanguin());
        banqueSang.setQuantite(banqueSang.getQuantite() + 1);
        String nvStatut = banqueSangFacade.checkStatut(banqueSang.getQuantite());
        banqueSang.setStatut(nvStatut);
        banqueSangFacade.edit(banqueSang);
        donneurSangFacade.create(donneurSang);
        donneurSangs.add(donneurSang);
        donneurSang = null;
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
    
    public DonneurSang getDonneurSang() {
        if (donneurSang == null) {
            donneurSang = new DonneurSang();
        }
        return donneurSang;
    }
    
    public void setDonneurSang(DonneurSang donneurSang) {
        this.donneurSang = donneurSang;
    }
    
    public List<DonneurSang> getDonneurSangs() {
        if (donneurSangs == null) {
            donneurSangs = donneurSangFacade.findAll();
        }
        return donneurSangs;
    }
    
    public void setDonneurSangs(List<DonneurSang> donneurSangs) {
        this.donneurSangs = donneurSangs;
    }
    
    public DonneurSangFacade getDonneurSangFacade() {
        return donneurSangFacade;
    }
    
    public void setDonneurSangFacade(DonneurSangFacade donneurSangFacade) {
        this.donneurSangFacade = donneurSangFacade;
    }
    
    public BanqueSangFacade getBanqueSangFacade() {
        return banqueSangFacade;
    }
    
    public void setBanqueSangFacade(BanqueSangFacade banqueSangFacade) {
        this.banqueSangFacade = banqueSangFacade;
    }

    /**
     * Creates a new instance of DonneurSangController
     */
    public DonneurSangController() {
    }
    
}
