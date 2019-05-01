/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EditorView;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chipan
 */
@Named(value = "editorView")
@SessionScoped
public class EditorView implements Serializable {

    private static final Logger LOG = Logger.getLogger(EditorView.class.getName());

    /**
     * Creates a new instance of EditorView
     */
    public EditorView() {
    }
    
    private String text;
 
    public String getText() {
        return text;
    }
 
    public void setText(String text) {
        this.text = text;
        LOG.log(Level.INFO, text);
    }
}
