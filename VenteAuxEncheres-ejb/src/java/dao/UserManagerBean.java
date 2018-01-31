/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Article;
import entity.Bidding;
import entity.Purchase;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        return new ArrayList<>();
    }
    
    public List<Article> getAllArticlessByUser(Long userID) {
        User user = getById(userID);
        if (user != null) {
            return user.getArticles();
        }
        return new ArrayList<>();
    }
    
    public List<Purchase> getAllPurchasesByUser(Long userID) {
        User user = getById(userID);
        if (user != null) {
            return user.getPurchases();
        }
        return new ArrayList<>();
    }
    
    public double getWallet(Long userID) {
        User user= getById(userID);
        return user.getWallet();
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

    public boolean canAddArticle(Long userID) {
        return getById(userID).getNbAbandonedBiddings() < 3;
    }

}
