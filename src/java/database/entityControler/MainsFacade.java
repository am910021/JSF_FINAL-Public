/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entityControler;

import database.entities.Mains;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yuri
 */
@Stateless
public class MainsFacade extends AbstractFacade<Mains> {

    @PersistenceContext(unitName = "FinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MainsFacade() {
        super(Mains.class);
    }
    
}
