/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import database.entities.Pedias;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author yuri
 */
@Named(value = "mainBean")
@RequestScoped
public class MainBean {

    /**
     * Creates a new instance of MainBean
     */
    public MainBean() {
    }
    
        private Pedias pedia;

    /**
     * Get the value of pedia
     *
     * @return the value of pedia
     */
    public Pedias getPedia() {
        return pedia;
    }

    /**
     * Set the value of pedia
     *
     * @param pedia new value of pedia
     */
    public void setPedia(Pedias pedia) {
        this.pedia = pedia;
    }

        
    
    public String getHtml(){
        
        return new String(pedia.getText());
    }
    
    
}
