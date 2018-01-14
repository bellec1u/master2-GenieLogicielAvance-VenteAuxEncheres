/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Article;
import java.util.List;
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
    
    public List<Article> getAll() {
        return executeNamedQuery("Article.findAll");
    }

    public List<Article> findByName(String name) {
        System.out.println("dao.ArticleManagerBean.findByName()");
        return getEntityManager()
                .createNamedQuery("Article.findByName", Article.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }
    
    public List<Article> findByCategories(String categories) {
        System.out.println("dao.ArticleManagerBean.findByCategories()");
        return getEntityManager()
                .createNamedQuery("Article.findByCategories", Article.class)
                .setParameter("categories", "%" + categories + "%")
                .getResultList();
    }
    
    public List<Article> findByNameAndCategories(String name, String categories) {
        System.out.println("dao.ArticleManagerBean.findByNameAndCategories()");
        return getEntityManager()
                .createNamedQuery("Article.findByCategories", Article.class)
                .setParameter("name", "%" + name + "%")
                .setParameter("categories", "%" + categories + "%")
                .getResultList();
    }

}
