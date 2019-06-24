/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author yuri
 */
@Named(value = "indexBean")
@SessionScoped
public class IndexBean implements Serializable{

    public final static String SITE_NAME = "JWFK";

    private String page;

    private String query;

    /**
     * Get the value of query
     *
     * @return the value of query
     */
    public String getQuery() {
        return query;
    }

    /**
     * Set the value of query
     *
     * @param query new value of query
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * Get the value of page
     *
     * @return the value of page
     */
    public String getPage() {
        if (page == null) {
            return "/index.xhtml";
        }
        return page;
    }
    
    public String getPageO() {
        return page;
    }
    /**
     * Set the value of page
     *
     * @param page new value of page
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * Creates a new instance of IndexBean
     */
    public IndexBean() {
    }

    public String getSiteName() {
        return SITE_NAME;
    }

    public String getBaseUrl() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getRequestURL().toString();
        String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
        return baseURL;
    }

}
