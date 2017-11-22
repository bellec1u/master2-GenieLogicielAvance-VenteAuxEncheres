/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Leopold
 */
@Stateless
public class UserManagerBean {

    @PersistenceContext(unitName = "VenteAuxEncheres-ejbPU")
    private EntityManager em;
    
    public void addUser(User u) {
        em.persist(u);
    }
    
}
