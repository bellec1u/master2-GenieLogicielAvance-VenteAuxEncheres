/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Leopold
 */
@Entity
@Table(name="Biddings")
@NamedQueries({
    @NamedQuery(name = "Bidding.findHighestBidding", 
            query = "select b from Bidding b where b.article.id = :id AND b.amount = "
                  + "(select max(b2.amount) from Bidding b2 where b2.article.id = :id)"),
    @NamedQuery(name = "Bidding.findByUserAndArticle",
            query = "SELECT b from Bidding b where b.article.id = :articleId AND b.user.id = :userId")
})
public class Bidding implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private double amount;
    
    @ManyToOne
    private Article article;
    
    @ManyToOne
    private User user;

    public Bidding() {
        
    }
    
    public Bidding(double amount) {
        this.amount = amount;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void removeUser() {
        user.removeBidding(id);
        this.user = null;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bidding)) {
            return false;
        }
        Bidding other = (Bidding) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bidding{" + "id=" + id + ", amount=" + amount + '}';
    }
    
}
