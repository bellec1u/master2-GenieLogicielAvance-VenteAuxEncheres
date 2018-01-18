/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Purchase;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author j-m_d
 */
@Stateless
public class PurchaseManagerBean extends AbstractManager<Purchase> {

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
}
