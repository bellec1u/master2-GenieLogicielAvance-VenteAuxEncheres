/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bidding;

import dao.ArticleManagerBean;
import dao.BiddingManagerBean;
import dao.UserManagerBean;
import entity.Bidding;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Leopold
 */
@Named(value = "biddingBean")
@RequestScoped
public class BiddingBean {

    @EJB
    private BiddingManagerBean biddingManager;
    @EJB
    private UserManagerBean userManager;
    @EJB
    private ArticleManagerBean articleManager;

    private Bidding bidding;

    /**
     * Creates a new instance of BiddingBean
     */
    public BiddingBean() {
        this.bidding = new Bidding();
    }

    public String bid(Long articleID, Long userID) {
        System.out.println("----- " + articleID + " - " + userID);
        if ((biddingManager.getHighestBiddingValue(articleID) == -1 && articleManager.getById(articleID).getStartingPrice() < bidding.getAmount())
                || biddingManager.getHighestBiddingValue(articleID) < bidding.getAmount()) {
            System.out.println("----- - " + bidding.getAmount());
            Bidding bid = biddingManager.create(bidding);
            System.out.println("----- - - " + bid);
            userManager.addBidding(bid, userID);
            articleManager.addBidding(bid, articleID);
        }
        return "index";
    }

    public String getHighestBiddingValue(Long articleID) {        
        double bid = biddingManager.getHighestBiddingValue(articleID);     
        if (bid == -1) {
            return "Pas encore d'enchères.";
        } else {
            return bid + "";
        }
    }
    
    public List<Bidding> getBiddingsForUserId(Long userID) {
        return userManager.getAllBiddingsByUser(userID);
    }

    public Bidding getBidding() {
        return bidding;
    }

    public void setBidding(Bidding bidding) {
        this.bidding = bidding;
    }

}
