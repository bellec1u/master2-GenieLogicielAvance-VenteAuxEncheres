/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package article;

import dao.ArticleManagerBean;
import entity.Article;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Leopold
 */
@Named(value = "articleManagedBean")
@RequestScoped
public class ArticleBean {

    @EJB
    private ArticleManagerBean articleManager;
    
    private Long articleId;
    
    /**
     * Creates a new instance of ArticleManagedBean
     */
    public ArticleBean() {
    }
    
    public String remove() {
        articleManager.removeById(articleId);
        
        return "";
    }
    
    public List<Article> getAllArticles() {
        return articleManager.getAll();
    }
    
}
