/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Article;
import entity.Bidding;
import java.util.ArrayList;
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
public class BiddingManagerBean extends AbstractManager<Bidding> {

    @PersistenceContext(unitName = "VenteAuxEncheres-ejbPU")
    private EntityManager em;

    public BiddingManagerBean() {
        super(Bidding.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Bidding getByUserAndArticle(Long articleID, Long userID) {
        try {
            TypedQuery<Bidding> query = getEntityManager()
                    .createNamedQuery("Bidding.findByUserAndArticle", Bidding.class);
            
            return query.setParameter("articleId", articleID)
                    .setParameter("userId", userID)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public double getHighestBiddingValue(Long articleID) {
        try {
            TypedQuery<Bidding> query = getEntityManager()
                    .createNamedQuery("Bidding.findHighestBidding", Bidding.class);
            
            return query.setParameter("id", articleID)
                    .getSingleResult()
                    .getAmount();
        } catch (NoResultException e) {
            return -1;
        }
    }
    
    @Override
    public void removeById(Object biddingID) {
        Bidding bidding = getById(biddingID);
        
        if (bidding.getArticle().hasEnded()) {
            bidding.getUser().incrementNbAbandonedBiddings();
        }
        
        // remove all links
        bidding.removeArticle();
        bidding.removeUser();

        // remove the entity
        getEntityManager().remove(getEntityManager().merge(bidding));
    }

}
