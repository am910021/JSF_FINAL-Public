/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import database.entities.Users;
import database.entityControler.UsersFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author yuri
 */
@FacesConverter("converter.idToUserConverter")
public class IdToUserConverter implements Converter {

    @Override
    public Users getAsObject(FacesContext context, UIComponent component, String value) {
        UsersFacade usersFacade;
        try {
            usersFacade = (UsersFacade) InitialContext.doLookup("java:global/Final/UsersFacade");
        } catch (NamingException e) {
            return null;
        }
        long id = Long.parseLong(value);
        Users user = usersFacade.getUserById(id);

        if (user == null) {
            return null;
        } else {
            return user;
        }
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        if(arg2 == null){
            return null;
        }
        Users user = (Users) arg2;
        
        return String.valueOf(user.getId());
    }
}
