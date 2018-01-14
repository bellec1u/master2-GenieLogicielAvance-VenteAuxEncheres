/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.seed;

import entity.Article;
import static entity.Article_.categories;
import entity.Bidding;
import entity.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            User u2 = new User("u2", "password", "user", "two", "address");
            User u3 = new User("u3", "password", "user", "three", "address");
            User u4 = new User("u4", "password", "user", "four", "address");
            
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            
            Article a1 = new Article("Pikachu", "this is a Pikachu !", 10, simpleDateFormat.parse("28/06/2018"), "pokemon/jaune/electique");
            u1.addArticle(a1);
            Article a2 = new Article("Brice Peters", "hey !", 10, simpleDateFormat.parse("28/06/2018"), "personne");
            u1.addArticle(a2);
            Article a3 = new Article("Télé", "c'est une télévision.", 10, simpleDateFormat.parse("28/06/2018"), "television");
            u2.addArticle(a3);
            
            Bidding b1 = new Bidding(15.5);
            u2.addBidding(b1);
            a1.addBidding(b1);
            Bidding b2 = new Bidding(18.5);
            u3.addBidding(b2);
            a1.addBidding(b2);
            
            em.persist(u1);
            em.persist(u2);
            em.persist(u3);
            em.persist(u4);
        } catch (ParseException ex) {
            Logger.getLogger(DatabaseSeed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
