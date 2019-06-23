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
@FacesValidator("validator.user.username")
public class UserNameValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        UsersFacade usersFacade;
        try {
            usersFacade = (UsersFacade)InitialContext.doLookup("java:global/Final/UsersFacade");
        } catch (NamingException ex) {
            Logger.getLogger(UserNameValidator.class.getName()).log(Level.SEVERE, null, ex);
            throw new ValidatorException(new FacesMessage("未知錯誤，請聯絡管理員。"));
        }
        
        String username = (String)value;
        
        if (username.length() < 6  || username.length() > 32){
            throw new ValidatorException(new FacesMessage("輸入的帳號請符合6~32字元長度之間。"));
        }
        
        List l = usersFacade.getEM().createNamedQuery("Users.findUserByUserName").setParameter("username", username).getResultList();

        if (!l.isEmpty()) {
            throw new ValidatorException(new FacesMessage("您無法使用該帳號，請更換一個。"));
        }
    }
}
