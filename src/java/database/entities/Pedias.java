/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yuri
 */
@Entity
@XmlRootElement
@NamedQueries({
 
    @NamedQuery(name = "Pedias.findPediaById", query = "SELECT p FROM Pedias p WHERE p.id = :id")

})
public class Pedias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte[] text;

    private String title;

    private String subtitle;

    @OneToMany(mappedBy = "pedia", cascade = CascadeType.ALL)
    private List<PediaLog> pediaLog;

    /**
     * Get the value of pediaLog
     *
     * @return the value of pediaLog
     */
    public List<PediaLog> getPediaLog() {
        return pediaLog;
    }

    /**
     * Set the value of pediaLog
     *
     * @param pediaLog new value of pediaLog
     */
    public void setPediaLog(List<PediaLog> pediaLog) {
        this.pediaLog = pediaLog;
    }

    public void addPediaLog(PediaLog pl) {
        if(pediaLog.isEmpty()){
            List<PediaLog> lpl = new ArrayList<>();
            lpl.add(pl);
            
            setPediaLog(lpl);
            return;
        }
        
        
        this.pediaLog.add(pl);
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

    /**
     * Get the value of text
     *
     * @return the value of text
     */
    public byte[] getText() {
        
        return text;
    }

    /**
     * Set the value of text
     *
     * @param text new value of text
     */
    public void setText(byte[] text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedias)) {
            return false;
        }
        Pedias other = (Pedias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.entities.Pedias[ id=" + id + " ]";
    }

}
