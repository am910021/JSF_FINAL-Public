/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import database.entities.Pedias;
import database.entityControler.PediasFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author yuri
 */
@FacesConverter("converter.idToPediaConverter")
public class IdToPediaConverter implements Converter {

    private static final Logger LOG = Logger.getLogger(IdToPediaConverter.class.getName());

    
    @Override
    public Pedias getAsObject(FacesContext context, UIComponent component, String value) {
        LOG.log(Level.SEVERE, String.valueOf(11111111));
        PediasFacade pediasFacade;
        try {
            pediasFacade = (PediasFacade) InitialContext.doLookup("java:global/Final/PediasFacade");
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
        LOG.log(Level.SEVERE, String.valueOf(2222222));
        long id = Long.parseLong(value);
        Pedias pedias = pediasFacade.getById(id);

        if (pedias == null) {
            LOG.log(Level.SEVERE, String.valueOf(3333333));
            return null;
        } else {
            return pedias;
        }
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        if(arg2 == null){
            return null;
        }
        Pedias pedias = (Pedias) arg2;
        
        return String.valueOf(pedias.getId());
    }
}
