/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Purchase;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author j-m_d
 */
@Stateless
public class PurchaseManagerBean extends AbstractManager<Purchase> {

    @Resource(mappedName = "jms/VenteAuxEncheresQueue")
    private Queue venteAuxEncheresQueue;

    @Inject
    @JMSConnectionFactory("jms/VenteAuxEncheresQueueFactory")
    private JMSContext context;

    @PersistenceContext(unitName = "VenteAuxEncheres-ejbPU")
    private EntityManager em;
    
    public PurchaseManagerBean() {
        super(Purchase.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Purchase> getAll() {
        return executeNamedQuery("Purchase.findAll");
    }
    
    public boolean isArticlePurchased(Long articleId) {
        List<Purchase> list = getEntityManager()
                .createNamedQuery("Purchase.findByArticleId", Purchase.class)
                .setParameter("articleId", articleId)
                .getResultList();
        
        return !list.isEmpty();
    }
    
    public void verifyPurchase(Purchase purchase) {
        sendJMSMessageToVenteAuxEncheresQueue(purchase);
    }
    
    private Message createPurchaseMessage(Purchase purchase) throws JMSException {
        ObjectMessage om = context.createObjectMessage();
        om.setObject(purchase);
        return om;
    }

    private void sendJMSMessageToVenteAuxEncheresQueue(Purchase purchase) {
        try {
            context.createProducer().send(venteAuxEncheresQueue, createPurchaseMessage(purchase));
            System.out.println("Purchase for article \"" + purchase.getArticle().getName() + "\" sent to be verified !");
        } catch (JMSException ex) {
            Logger.getLogger(PurchaseManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
