/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EditorView;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author yuri
 */
@Named(value = "indexBean")
@RequestScoped
public class IndexBean {

    
    public final static String SITE_NAME = "JWFK";
    
    /**
     * Creates a new instance of IndexBean
     */
    public IndexBean() {
    }
    
    public String getSiteName(){
        return SITE_NAME;
    }
    
    public String getBaseUrl(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getRequestURL().toString();
        String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
        return baseURL;
    }
    
    
}
