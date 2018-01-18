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
import java.util.Date;
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
        if ((biddingManager.getHighestBiddingValue(articleID) == -1 && articleManager.getById(articleID).getStartingPrice() < bidding.getAmount())
                || biddingManager.getHighestBiddingValue(articleID) < bidding.getAmount()) {
            Bidding bid = biddingManager.getByUserAndArticle(articleID, userID);
            if (bid != null) {
                bid.setAmount(bidding.getAmount());
                biddingManager.edit(bid);
            } else {
                Bidding newBid = biddingManager.create(bidding);
                userManager.addBidding(newBid, userID);
                articleManager.addBidding(newBid, articleID);
            }
        }
        return "article?faces-redirect=true&includeViewParams=true";
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

    public String getBiddingStatus(Bidding bidding) {
        if (bidding.getArticle().hasEnded()) {
            if (biddingManager.getHighestBiddingValue(bidding.getArticle().getId()) == bidding.getAmount()) {
                return "Gagnée";
            }
            return "Perdue";
        }
        return "En cours";
    }

    public Bidding getBidding() {
        return bidding;
    }

    public void setBidding(Bidding bidding) {
        this.bidding = bidding;
    }

}
