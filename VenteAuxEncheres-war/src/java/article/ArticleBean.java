/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package article;

import dao.ArticleManagerBean;
import dao.UserManagerBean;
import entity.Article;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Leopold
 */
@Named(value = "articleBean")
@SessionScoped
public class ArticleBean implements Serializable {

    @EJB
    private ArticleManagerBean articleManager;
    
    @EJB
    private UserManagerBean userManager;
    
    @ManagedProperty(value = "#{param.articleID}")
    private Long articleId;
    
    private Article article;
        
    /**
     * Creates a new instance of ArticleManagedBean
     */
    public ArticleBean() {
    }
    
    public String remove(Long articleID) {
        articleManager.removeById(articleID);
        
        return "accountInfo?faces-redirect=true";
    }

    public void findArticle() {
        article = articleManager.getById(articleId);
    }
    
    public List<Article> getAllArticles() {
        return articleManager.getAll();
    }
    
    public List<Article> getAllArticlesUnexpired() {
        return articleManager.getAllArticlesUnexpired();
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
        Article article = articleManager.getById(articleId);
        if (article == null) {
            return "Not available yet";
        } else {
            return article.getOwner().getLogin();
        }
    }
    
    public boolean isArticleOwner(Long userID) {
        if (article == null) {
            System.out.println("######### NULL");
        }
        return Objects.equals(article.getOwner().getId(), userID);
    }
    
    public String getArticleStatus(Article article) {
        if (article.hasEnded()) {
            return "Finie";
        }
        return "En cours";
    }
    
}
