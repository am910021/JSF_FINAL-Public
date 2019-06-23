/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator.user;

import database.entityControler.UsersFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author yuri
 */
@FacesValidator("validator.user.email")
public class EmailValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        UsersFacade usersFacade;
        try {
            usersFacade = (UsersFacade)InitialContext.doLookup("java:global/Final/UsersFacade");
        } catch (NamingException ex) {
            Logger.getLogger(EmailValidator.class.getName()).log(Level.SEVERE, null, ex);
            throw new ValidatorException(new FacesMessage("未知錯誤，請聯絡管理員。"));
        }
        
        String email = (String)value;
        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

        if (!email.matches(regex)) {
            throw new ValidatorException(new FacesMessage("電子郵件不符合規範，請重新輸入。"));
        }
        
        List l = usersFacade.getEM().createNamedQuery("Users.countEmail").setParameter("email", email).getResultList();

        if (!l.isEmpty()) {
            throw new ValidatorException(new FacesMessage("您無法使用該電子郵件，請更換一個。"));
        }

    }

}
