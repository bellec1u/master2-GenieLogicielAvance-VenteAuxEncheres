/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Article;
import entity.Bidding;
import entity.Purchase;
import entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
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

    public List<Article> getAll() {
        return executeNamedQuery("Article.findAll");
    }

    public List<Article> findByNameUnexpired(String name) {
        System.out.println("dao.ArticleManagerBean.findByName()");
        return getEntityManager()
                .createNamedQuery("Article.findByNameUnexpired", Article.class)
                .setParameter("endDate", new Date())
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    public List<Article> findByCategoriesUnexpired(String categories) {
        System.out.println("dao.ArticleManagerBean.findByCategories()");
        return getEntityManager()
                .createNamedQuery("Article.findByCategoriesUnexpired", Article.class)
                .setParameter("endDate", new Date())
                .setParameter("categories", "%" + categories + "%")
                .getResultList();
    }

    public List<Article> findByNameAndCategoriesUnexpired(String name, String categories) {
        System.out.println("dao.ArticleManagerBean.findByNameAndCategories()");
        return getEntityManager()
                .createNamedQuery("Article.findByNameAndCategoriesUnexpired", Article.class)
                .setParameter("endDate", new Date())
                .setParameter("name", "%" + name + "%")
                .setParameter("categories", "%" + categories + "%")
                .getResultList();
    }

    public List<Article> findByBonus() {
        System.out.println("dao.ArticleManagerBean.findByBonus()");
        return getEntityManager()
                .createNamedQuery("Article.findByBonus", Article.class)
                .setParameter("endDate", new Date())
                .getResultList();
    }

    @Schedule(second = "0", minute = "0", hour = "0", dayOfMonth = "*", month = "*", year = "*")
    public void newBonus() {
        Random random = new Random();

        //cleaning at midnight
        List<Article> listArticle = findByBonus();
        for (Article a : listArticle) {
            a.setBonus(0);
            edit(a);
        }

        //ADD new promotion
        listArticle = getAll();
        List<Article> listBonus = new ArrayList<>();
        int index = random.nextInt(listArticle.size());
        for (int i = 0; i < 2; i++) {
            while (listBonus.contains(listArticle.get(index))) {
                index = random.nextInt(listArticle.size());
            }
            listBonus.add(listArticle.get(index));
        }

        //merge
        for (Article bonus : listBonus) {
            bonus.setBonus(10.0);
            edit(bonus);
        }
    }

    public void addBidding(Bidding bid, Long articleID) {
        Article article = getById(articleID);
        article.addBidding(bid);
        edit(article);
    }

    @Override
    public void removeById(Object articleID) {
        Article article = getById(articleID);

        // remove all biddings
        List<Bidding> biddings = article.getBiddings();
        article.setBiddings(new ArrayList<>());
        for (Bidding bidding : biddings) {
            // remove from user
            bidding.removeUser();
            // remove Object
            getEntityManager().remove(getEntityManager().merge(bidding));
        }

        // remove for the user
        article.getOwner().removeArticle(article.getId());
        article.setOwner(null);

        // remove all purchases
        List<Purchase> purchases = getEntityManager()
                .createNamedQuery("Purchase.findByArticleId", Purchase.class)
                .setParameter("articleId", articleID)
                .getResultList();
        for (Purchase purchase : purchases) {
            // remove dependences
            purchase.removeUser();
            purchase.removeArticle();
            // remove object
            getEntityManager().remove(getEntityManager().merge(purchase));
        }

        // remove the article
        getEntityManager().remove(getEntityManager().merge(article));
    }

    public void addArticleToUser(Article article, Long userID) {
        // get the user
        User user = getEntityManager().find(User.class, userID);
        // add the article
        user.addArticle(article);
        // update the user
        getEntityManager().merge(user);
    }

    public List<Article> getAllArticlesUnexpired() {
        return getEntityManager()
                .createNamedQuery("Article.findAllUnexpired", Article.class)
                .setParameter("endDate", new Date())
                .getResultList();
    }

}
