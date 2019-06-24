/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author yuri
 */
@Entity
public class PediaLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL)
    // Specity join column name and referened column name.
    @JoinColumn(name = "pedia_id", referencedColumnName = "ID")
    private Pedias pedia;

    @OneToOne(cascade = CascadeType.ALL)
    // Specity join column name and referened column name.
    @JoinColumn(name = "user_id", referencedColumnName = "ID")
    private Users user;
    
    @Basic(optional = false)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
        private Date createDate;

    /**
     * Get the value of createDate
     *
     * @return the value of createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Set the value of createDate
     *
     * @param createDate new value of createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    
    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public Users getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(Users user) {
        this.user = user;
    }

    /**
     * Get the value of pedias
     *
     * @return the value of pedias
     */
    public Pedias getPedia() {
        return pedia;
    }

    /**
     * Set the value of pedias
     *
     * @param pedias new value of pedias
     */
    public void setPedias(Pedias pedia) {
        this.pedia = pedia;
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
        if (!(object instanceof PediaLog)) {
            return false;
        }
        PediaLog other = (PediaLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.entities.PediaLog[ id=" + id + " ]";
    }

}
