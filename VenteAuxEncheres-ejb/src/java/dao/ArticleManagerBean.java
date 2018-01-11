/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Article;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Leopold
 */
@Stateless
@LocalBean
public class ArticleManagerBean extends AbstractManager<Article> {

    @PersistenceContext(unitName = "VenteAuxEncheres-ejbPU")
    private EntityManager em;
    
    public ArticleManagerBean() {
        super(Article.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public Article edit(Article article) {
        if (article.hasEnded()) {
            return super.edit(article);
        }
        
        return article;
    }

}
