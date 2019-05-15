/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityControler;

import entitys.Configure;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yuri
 */
@Stateless
public class ConfigureFacade extends AbstractFacade<Configure> {

    @PersistenceContext(unitName = "FinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConfigureFacade() {
        super(Configure.class);
    }
    
}
