/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Article;
import entity.Bidding;
import entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Leopold
 */
@Stateless
public class UserManagerBean extends AbstractManager<User> {
    
    @PersistenceContext(unitName = "VenteAuxEncheres-ejbPU")
    private EntityManager em;
    
    public UserManagerBean() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void addBidding(Bidding bid, Long userID) {
        User user = getById(userID);
        user.addBidding(bid);
        edit(user);
    }
    
    public List<Bidding> getAllBiddingsByUser(Long userID) {
        User user = getById(userID);
        if (user != null) {
            return user.getBiddings();
        }
        return null;
    }

    public User getByCredentials(String login, String password) {
        User result = getEntityManager()
                .createNamedQuery("User.findByLogin", User.class)
                .setParameter("login", login)
                .getSingleResult();
        
        if (result.getPassword().equals(password))
            return result;
        else
            return null;
    }

}
