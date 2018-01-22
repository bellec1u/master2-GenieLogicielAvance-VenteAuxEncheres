/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import dao.BiddingManagerBean;
import dao.PurchaseManagerBean;
import dao.UserManagerBean;
import entity.Article;
import entity.Purchase;
import entity.User;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author j-m_d
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/VenteAuxEncheresQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class PurchaseMessageDrivenBean implements MessageListener {
    
    @EJB
    private UserManagerBean userManager;
    
    @EJB
    private BiddingManagerBean biddingManager;
    
    @EJB
    private PurchaseManagerBean purchaseManager;
    
    public PurchaseMessageDrivenBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objMessage = (ObjectMessage) message;
            Purchase purchase = (Purchase) objMessage.getObject();
            System.out.println("Purchase for article \"" + purchase.getArticle().getName() + "\" received to be verified !");
            processPurchaseToPay(purchase);
            processPurchaseToShip(purchase);
            purchaseManager.edit(purchase);
        } catch (JMSException ex) {
            Logger.getLogger(PurchaseMessageDrivenBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PurchaseMessageDrivenBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void processPurchaseToPay(Purchase purchase) throws InterruptedException {
        Random r = new Random();
        long id = purchase.getId();
        Article article = purchase.getArticle();
        double finalArticlePrice = biddingManager.getHighestBiddingValue(article.getId());
        User user = purchase.getUser();
        
        System.out.println("Started Payement process for purchase #" + id + "...");
        
        System.out.println("Verification of the account number for purchase #" + id + "...");
        
        //calculating amounts to debit from user's wallet and user's account 
        double walletAmount = user.getWallet();
        double amountToDebit = finalArticlePrice;
        double walletAmountToDebit = 0.0;
        if (walletAmount > finalArticlePrice) {
            amountToDebit = 0.0;
            walletAmountToDebit = finalArticlePrice;
            user.setWallet(walletAmount - walletAmountToDebit);
        } else {
            amountToDebit -= walletAmount;
            walletAmountToDebit = walletAmount;
            user.setWallet(0.0);
        }
        
        System.out.println("Debiting " + walletAmountToDebit + " from user's wallet");
        userManager.edit(user);
        
        System.out.println("Debiting " + amountToDebit + " from the account #" + purchase.getBankAccountNumber() + "for purchase #" + id + "...");
        
        System.out.println("Payement Finished for purchase #" + id);
        
        purchase.setIsPayed(true);
    }
    
    private void processPurchaseToShip(Purchase purchase) throws InterruptedException {
        Random r = new Random();
        long id = purchase.getId();
        Article article = purchase.getArticle();
        
        System.out.println("Started Shipping process for purchase #" + id + "...");
        
        System.out.println("Verification of the dimensions for purchase #" + id + "...");
        
        System.out.println("Verification of the weight for purchase #" + id + "...");
        
        System.out.println("Printing the adress \"" + purchase.getAddress() + "\" on the shipping box for purchase #" + id + "...");
        
        System.out.println("Turning on the engine of the delivering truck for purchase #" + id + "...");
        
        System.out.println("Shipping procedure finished for purchase #" + id);
        
        purchase.setIsShipped(true);
    }
}
