/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package purchase;

import dao.UserManagerBean;
import entity.Article;
import entity.Bidding;
import entity.Purchase;
import entity.User;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author master2-lmfi
 */
@Named(value = "purchaseBean")
@SessionScoped
public class PurchaseBean implements Serializable {

    @EJB
    private UserManagerBean userManagerBean;
    
    private Article article;
    private Bidding bidding;
    private User user;
    private Purchase purchase;
    
    /**
     * Creates a new instance of PurchaseBean
     */
    public PurchaseBean() {
        this.purchase = new Purchase();
    }
    
    public String getPurchaseForm(Long userId, Article article) {
        this.article = article;      
        this.user = userManagerBean.getById(userId);
        
        return "purchase?faces-redirect=true";
    }
    
    public String getPurchaseForm(Bidding bidding) {
        this.bidding = bidding;
        
        return "purchase?faces-redirect=true";
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Bidding getBidding() {
        return bidding;
    }
    
    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
    
    public User getUser() {
        return user;
    }
    
    public String submitPurchase() {
        purchase.setArticle(bidding.getArticle());
        bidding.getUser().addPurchase(purchase);
        
        userManagerBean.edit(bidding.getUser());
        
        return "index?faces-redirect=true";
    }
}
