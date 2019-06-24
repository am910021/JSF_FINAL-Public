/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author user
 */
@Named(value = "securityBean")
@SessionScoped
public class SecurityBean implements Serializable{

    @Inject
    private Principal principal;
    @Inject
    private HttpServletRequest request;

    /**
     * Creates a new instance of SecurityBean
     */
    public SecurityBean() {
    }

    public String getPrincipalName() {
        //request.getu
        return principal.getName();
    }

    public boolean isLoginStatus() {
        String remoteUser = request.getRemoteUser();
        return (remoteUser != null);
    }

    public String logout() {
        try {
            request.logout();
        } catch (ServletException ex) {
            Logger.getLogger(SecurityBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/auth/authmethodform?faces-redirect=true";
    }

    public boolean isAdmin() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        return context.isUserInRole("admin");
    }

}
