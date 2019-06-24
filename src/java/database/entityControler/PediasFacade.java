/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entityControler;

import database.entities.Pedias;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yuri
 */
@Stateless
public class PediasFacade extends AbstractFacade<Pedias> {

    @PersistenceContext(unitName = "FinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PediasFacade() {
        super(Pedias.class);
    }
    
}
