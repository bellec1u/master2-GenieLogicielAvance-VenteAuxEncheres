/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Leopold
 */
@Entity
@Table(name="Users")
@NamedQueries({
    @NamedQuery(name = "User.findAll", 
            query = "select u from User u"),
    @NamedQuery(name = "User.findById", 
            query = "select u from User u where u.id = :id"),
    @NamedQuery(name = "User.findByLogin",
            query = "select u from User u where u.login = :login")
})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    private String lastName;
    @NotNull
    private String firstName;
    @NotNull
    private String address;
    @NotNull
    private int nbAbandonedBiddings;
    @NotNull
    private double wallet;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Purchase> purchases;
    
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Article> articles;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Bidding> biddings;
    
    public User() {
        
    }
    
    public User(String login, String password, String lastName, String firstName, String address) {
        this.login = login;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.nbAbandonedBiddings = 0;
        this.wallet = 0;
        
        this.purchases = new ArrayList<>();
        this.articles = new ArrayList<>();
        this.biddings = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNbAbandonedBiddings() {
        return nbAbandonedBiddings;
    }

    public void setNbAbandonedBiddings(int nbAbandonedBiddings) {
        this.nbAbandonedBiddings = nbAbandonedBiddings;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
    
    public void addPurchase(Purchase purchase) {
        purchase.setUser(this);
        purchases.add(purchase);
    }

    void removePurchase(Long id) {
        int x = -1;
        for (int i = 0; i < purchases.size(); i++) {
            if (Objects.equals(purchases.get(i).getId(), id)) {
                x = i;
            }
        }
        purchases.remove(x);
    }

    public List<Article> getArticles() {
        return articles;
    }

    public Article getArticleById(Long id) {
        for (Article a : articles) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
    
    public void addArticle(Article article) {
        article.setOwner(this);
        articles.add(article);
    }
    
    public void removeArticle(Long id) {
        int x = -1;
        for (int i = 0; i < articles.size(); i++) {
            if (Objects.equals(articles.get(i).getId(), id)) {
                x = i;
            }
        }
        articles.remove(x);
    }

    public List<Bidding> getBiddings() {
        return biddings;
    }

    public void setBiddings(List<Bidding> biddings) {
        this.biddings = biddings;
    }
    
    public void addBidding(Bidding bidding) {
        bidding.setUser(this);
        biddings.add(bidding);
    }

    void removeBidding(Long id) {
        int x = -1;
        for (int i = 0; i < biddings.size(); i++) {
            if (Objects.equals(biddings.get(i).getId(), id)) {
                x = i;
            }
        }
        biddings.remove(x);
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", login=" + login + ", password=" + password + ", lastName=" + lastName + ", firstName=" + firstName + ", address=" + address + ", nbAbandonedBiddings=" + nbAbandonedBiddings + ", wallet=" + wallet + '}';
    }
    
}
