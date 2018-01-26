/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.seed;

import entity.Article;
import entity.Bidding;
import entity.Purchase;
import entity.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Singleton to feed the database with some datas.
 *
 * @author alexis
 */
@Singleton
@Startup
public class DatabaseSeed {

    @PersistenceContext(unitName = "VenteAuxEncheres-ejbPU")
    private EntityManager em;
    
    @PostConstruct
    public void seed() {
        try {
            User u1 = new User("u1", "password", "user", "one", "address");
            u1.setWallet(0.5);
            User u2 = new User("u2", "password", "user", "two", "address");
            u2.setWallet(15.0);
            User u3 = new User("u3", "password", "user", "three", "address");
            u3.setWallet(1.0);
            u3.incrementNbAbandonedBiddings();
            u3.incrementNbAbandonedBiddings();
            u3.incrementNbAbandonedBiddings();
            User u4 = new User("u4", "password", "user", "four", "address");
            u4.setWallet(70.0);
            
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            
            Article a1 = new Article("Pikachu", "this is a Pikachu !", 10, simpleDateFormat.parse("28/06/2018"), "pokemon/jaune/electique");
            u1.addArticle(a1);
            Article a2 = new Article("Brice Peters", "hey !", 10, simpleDateFormat.parse("28/06/2018"), "personne");
            u1.addArticle(a2);
            Article a3 = new Article("Télé", "c'est une télévision.", 10, simpleDateFormat.parse("28/06/2018"), "television");
            u2.addArticle(a3);
            
            //ajout d'articles avec enchere perimée pour commande
            Article ap1 = new Article("Magnifique table en acajou massif", "je l'ai reçu en cadeau de chez canapi mais j'aime pas", 25, simpleDateFormat.parse("10/01/2018"), "acajou/table");
            u1.addArticle(ap1);
            Article ap2 = new Article("666x cornets de glaces", "cornets vides, faut pas rever non plus wsh", 25, simpleDateFormat.parse("01/01/2018"), "cornet");
            u2.addArticle(ap2);
            Article ap3 = new Article("Matou", "sous classe de l'ensemble des chats", 200, simpleDateFormat.parse("01/01/2018"), "chat");
            u3.addArticle(ap3);
            Article ap4 = new Article("Blue Ugandan Knuckels (Replica)", "Do yu kno de wey ?", 150, simpleDateFormat.parse("02/01/2018"), "sonic");
            u4.addArticle(ap4);
            
            Bidding b1 = new Bidding(15.5);
            u2.addBidding(b1);
            a1.addBidding(b1);
            Bidding b2 = new Bidding(18.5);
            u3.addBidding(b2);
            a1.addBidding(b2);
            Bidding b3 = new Bidding(25);
            u2.addBidding(b3);
            ap1.addBidding(b3);
            Bidding b4 = new Bidding(100);
            u3.addBidding(b4);
            ap2.addBidding(b4);
            Bidding b5 = new Bidding(1000);
            u1.addBidding(b5);
            ap4.addBidding(b5);
            
            em.persist(u1);
            em.persist(u2);
            em.persist(u3);
            em.persist(u4);
        } catch (ParseException ex) {
            Logger.getLogger(DatabaseSeed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
