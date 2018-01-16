/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Bidding;
import entity.User;
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

}