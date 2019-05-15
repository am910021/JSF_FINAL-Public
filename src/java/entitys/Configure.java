/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author yuri
 */
@Entity
public class Configure implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String ckey;

    private String cvalue;

    /**
     * Get the value of cvalue
     *
     * @return the value of cvalue
     */
    public String getCvalue() {
        return cvalue;
    }

    /**
     * Set the value of cvalue
     *
     * @param cvalue new value of cvalue
     */
    public void setCvalue(String cvalue) {
        this.cvalue = cvalue;
    }

    /**
     * Get the value of ckey
     *
     * @return the value of ckey
     */
    public String getCkey() {
        return ckey;
    }

    /**
     * Set the value of ckey
     *
     * @param ckey new value of ckey
     */
    public void setCkey(String ckey) {
        this.ckey = ckey;
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
        if (!(object instanceof Configure)) {
            return false;
        }
        Configure other = (Configure) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.Configure[ id=" + id + " ]";
    }

}
