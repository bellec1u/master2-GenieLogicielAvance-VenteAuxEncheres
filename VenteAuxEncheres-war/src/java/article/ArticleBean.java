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
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Leopold
 */
@Named(value = "articleBean")
@RequestScoped
public class ArticleBean {

    @EJB
    private ArticleManagerBean articleManager;
    
    @ManagedProperty(value = "#{param.userID}")
    private Long articleId;
    
    private Article article;
        
    /**
     * Creates a new instance of ArticleManagedBean
     */
    public ArticleBean() {
    }
    
    public String remove() {
        articleManager.removeById(articleId);
        
        return "";
    }

    public void findArticle() {
        article = articleManager.getById(articleId);
    }
    
    public List<Article> getAllArticles() {
        return articleManager.getAll();
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
    
}
