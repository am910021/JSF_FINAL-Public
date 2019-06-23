/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator.user;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author yuri
 */
@FacesValidator("validator.user.password")
public class PasswordValidator implements Validator {

    
    
    int min = 6;
    private static final Logger LOG = Logger.getLogger(PasswordValidator.class.getName());
    
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        UIInput uiPassword = (UIInput) component.getAttributes().get("hPassword");
        UIInput uiConfirmPassword = (UIInput) component.getAttributes().get("hConfirmPassword");

        if (uiPassword == null || uiConfirmPassword == null) {
            throw new ValidatorException(new FacesMessage("未知錯誤，請聯絡管理員。"));
        }

        String password = (String) uiPassword.getSubmittedValue();
        String confirmPassword = (String) uiConfirmPassword.getSubmittedValue();

        if (!password.equals(confirmPassword)) {
            throw new ValidatorException(new FacesMessage("密碼與確認密碼不符，請重新輸入。"));
        }

        String pattern = "^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9]))).{6,}$";
        
        if (!password.matches(pattern)) {
            throw new ValidatorException(new FacesMessage(String.format("請輸入大、小英文字母和數字共%d以上。", min)));
        }

    }

}