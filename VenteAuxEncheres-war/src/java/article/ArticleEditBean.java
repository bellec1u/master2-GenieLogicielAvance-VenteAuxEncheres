/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package article;

import dao.ArticleManagerBean;
import entity.Article;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Leopold
 */
@Named(value = "articleEditBean")
@RequestScoped
public class ArticleEditBean {

    @EJB
    private ArticleManagerBean articleManager;
    
    private Long id;
    private Article article;
        
    /**
     * Creates a new instance of ArticleEditBean
     */
    public ArticleEditBean() {
        this.article = new Article();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
    
    public String getEditForm() {
        article = articleManager.getById(id);
        
        return "";
    }
    
    public String edit() {
        articleManager.edit(article);
        
        return "";
    }
    
    public String create() {
        articleManager.create(article);
        
        return "";
    }
    
}
