/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entityControler;

import database.entities.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yuri
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "FinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
        public Users getUserById(long id){
        return (Users)em.createNamedQuery("Users.findUserById").setParameter("id", id).getSingleResult();
    }
    
    public Users getUserByUsername(String username){
        return (Users)em.createNamedQuery("Users.findUserByUserName").setParameter("username", username).getSingleResult();
    }
    
    
}
