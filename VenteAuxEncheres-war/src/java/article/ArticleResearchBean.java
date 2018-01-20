/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package article;

import dao.ArticleManagerBean;
import entity.Article;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Leopold
 */
@Named(value = "articleResearchManagedBean")
@RequestScoped
public class ArticleResearchBean {

    @EJB
    private ArticleManagerBean articleManager;

    private String name;
    private String categories;

    private List<Article> articles;

    /**
     * Creates a new instance of ArticleManagedBean
     */
    public ArticleResearchBean() {
        this.name = "";
        this.categories = "";

        this.articles = new ArrayList<>();
    }

    public void findArticles() {
        if ("".equals(name) && "".equals(categories)) {
            // research with no parameters
            articles = articleManager.getAllArticlesUnexpired();
        } else if ("".equals(categories)) {
            // research by name
            articles = articleManager.findByNameUnexpired(name);
        } else if ("".equals(name)) {
            // research by categories
            articles = articleManager.findByCategoriesUnexpired(categories);
        } else {
            // research by name and categories
            articles = articleManager.findByNameAndCategoriesUnexpired(name, categories);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

}
