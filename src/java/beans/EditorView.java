/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import database.entities.Pedias;
import database.entityControler.PediasFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Chipan
 */
@Named(value = "editorView")
@RequestScoped
public class EditorView implements Serializable {

    private static final Logger LOG = Logger.getLogger(EditorView.class.getName());

    @EJB
    PediasFacade pediasFacade;

    @Inject
    IndexBean indexBean;

    /**
     * Creates a new instance of EditorView
     */
    public EditorView() {
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
        this.title = pedia.getTitle();
        this.subtitle = pedia.getSubtitle();
        this.text = new String(pedia.getText());

        this.pedia = pedia;
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

    public String actionModifyPedias() {

        pedia.setTitle(title);
        pedia.setSubtitle(subtitle);
        pedia.setText(text.getBytes());
        pediasFacade.edit(pedia);
        
        return String.format("/pagepedia.xhtml?id=%dfaces-redirect=true", pedia.getId());
    }

    public String actionAddPedias() {
        Pedias pedia = new Pedias();
        pedia.setTitle(title);
        pedia.setSubtitle(subtitle);
        pedia.setText(text.getBytes());
        pediasFacade.create(pedia);
        
        return String.format("/pagepedia.xhtml?id=%dfaces-redirect=true", pedia.getId());
    }

}
