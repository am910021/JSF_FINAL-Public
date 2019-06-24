/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator.user;

import database.entities.Users;
import database.entityControler.UsersFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tool.Crypto;

/**
 *
 * @author yuri
 */
@FacesValidator("validator.user.signin")
public class SignInValidator implements Validator {

    private static final Logger LOG = Logger.getLogger(SignInValidator.class.getName());

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        UsersFacade usersFacade;
        try {
            usersFacade = (UsersFacade) InitialContext.doLookup("java:global/Final/UsersFacade");
        } catch (NamingException ex) {
            Logger.getLogger(EmailValidator.class.getName()).log(Level.SEVERE, null, ex);
            throw new ValidatorException(new FacesMessage("未知錯誤，請聯絡管理員。"));
        }

        UIInput uiUsername = (UIInput) component.getAttributes().get("hUsername");
        UIInput uiPassword = (UIInput) component.getAttributes().get("hPassword");

        if (uiPassword == null || uiUsername == null) {
            throw new ValidatorException(new FacesMessage("未知錯誤，請聯絡管理員。"));
        }

        String password = (String) uiPassword.getSubmittedValue();
        String username = (String) uiUsername.getSubmittedValue();

        List<Users> l = usersFacade.getEM().createNamedQuery("Users.findUserByUserName").setParameter("username", username).getResultList();

        if (l.isEmpty()) {
            throw new ValidatorException(new FacesMessage("帳號密碼錯誤，請重新登入。"));
        }

        Users user = l.get(0);

        if (!Crypto.checkSHA512(password, user.getPassword())) {
            throw new ValidatorException(new FacesMessage("帳號密碼錯誤，請重新登入。"));
        }
  
        

    }
}
