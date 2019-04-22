/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.util;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import service.ClientFacade;

/**
 *
 * @author asus
 */
@Named(value = "checkvalidation")
@RequestScoped
public class Checkvalidation implements Validator {

    @EJB
    private ClientFacade clientFacade;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return; // Let required="true" handle.
        }

        String username = (String) value;

        if (clientFacade.find(username) != null) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Had login fayet lih kayen, Dir chi wahed akhor", null));
        }
    }

    /**
     * Creates a new instance of Checkvalidation
     */
    public Checkvalidation() {
    }

}
