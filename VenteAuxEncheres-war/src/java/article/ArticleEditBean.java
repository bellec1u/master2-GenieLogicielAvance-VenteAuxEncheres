/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package article;

import dao.ArticleManagerBean;
import dao.UserManagerBean;
import entity.Article;
import entity.User;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import navigation.NavigationManagedBean;

/**
 *
 * @author Leopold
 */
@Named(value = "articleEditBean")
@RequestScoped
public class ArticleEditBean {

    @EJB
    private ArticleManagerBean articleManager;
    
    @EJB
    private UserManagerBean userManager;

    private NavigationManagedBean navigationBean;
    
    private Long id;
    private Article article;
        
    /**
     * Creates a new instance of ArticleEditBean
     */
    public ArticleEditBean() {
        this.article = new Article();
        this.article.setEndDate(new Date());
        this.navigationBean = new NavigationManagedBean();
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
    
    public void create() {
        articleManager.create(article);
        User user = userManager.getById(navigationBean.getCurrentId());
        user.addArticle(article);
        userManager.edit(user);
    }
    
    public String edit() {
        articleManager.edit(article);
        
        return "";
    }
    
}
