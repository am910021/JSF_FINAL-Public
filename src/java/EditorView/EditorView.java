/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EditorView;

import database.entities.Pedias;
import database.entityControler.PediasFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

/**
 *
 * @author Chipan
 */
@Named(value = "editorView")
@SessionScoped
public class EditorView implements Serializable {

    private static final Logger LOG = Logger.getLogger(EditorView.class.getName());

    @EJB
    PediasFacade pediasFacade;
    
    
    
    /**
     * Creates a new instance of EditorView
     */
    public EditorView() {
    }

    private String title;

    private String subtitle;

    private String text;

    /**
     * Get the value of text
     *
     * @return the value of text
     */
    public String getText() {
        return text;
    }

    /**
     * Set the value of text
     *
     * @param text new value of text
     */
    public void setText(String text) {
        this.text = text;
        
    }

    /**
     * Get the value of subtitle
     *
     * @return the value of subtitle
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * Set the value of subtitle
     *
     * @param subtitle new value of subtitle
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
        
    }

    /**
     * Get the value of title
     *
     * @return the value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the value of title
     *
     * @param title new value of title
     */
    public void setTitle(String title) {
        this.title = title;
        
    }
    
    public String actionAddPedias(){
        Pedias pedia = new Pedias();
        pedia.setTitle(title);
        pedia.setSubtitle(subtitle);
        pedia.setText(text.getBytes());
        pediasFacade.create(pedia);
        
        
        LOG.log(Level.INFO, text);
        LOG.log(Level.INFO, subtitle);
        LOG.log(Level.INFO, title);
        
        
        //LOG.log(Level.INFO, new String(text.getBytes()));
        
        return "/index.xhtml";
    }

}
