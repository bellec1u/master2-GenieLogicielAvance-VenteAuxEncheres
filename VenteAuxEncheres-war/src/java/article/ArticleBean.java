/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package article;

import dao.ArticleManagerBean;
import dao.UserManagerBean;
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
    
    @EJB
    private UserManagerBean userManager;
    
    @ManagedProperty(value = "#{param.userID}")
    private Long articleId;
    
    private Article article;
    private String sellerLogin;
        
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
        sellerLogin = "Not available yet";
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
    
    public String getSellerLogin() {
        return sellerLogin;
    }
}
