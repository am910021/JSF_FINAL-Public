/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation.user;

import database.entityControler.UsersFacade;
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
            throw new ValidatorException(new FacesMessage("未知錯誤。"));
        }
        
        
        String email = (String)value;
        
        usersFacade.getEM().createNamedQuery("Users.countEmail").setParameter("email", email).executeUpdate();

        //UM.setMode(AbstractManager.MODE.SELECT);
        //UM.equal(UserEntity_.email, email);
        //if (!UM.isEmpty()) {
        //    throw new ValidatorException(new FacesMessage("該使電子郵件已經被使用，請換一個。"));
        //}

    }

}
