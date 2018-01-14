/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
@Table(name="Articles")
@NamedQueries({
    @NamedQuery(name = "Article.findAll", 
            query = "select a from Article a"),
    @NamedQuery(name = "Article.findByName",
            query = "select a from Article a where upper(a.name) like upper(:name)"),
    @NamedQuery(name = "Article.findByCategories",
            query = "select a from Article a where upper(a.categories) like upper(:categories)"),
    @NamedQuery(name = "Article.findByNameAndCategories",
            query = "select a from Article a where upper(a.name) like upper(:name) and upper(a.categories) like upper(:categories)")
})
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private double startingPrice;
    @NotNull
    private Date endDate;

    private double bonus;
    
    @NotNull
    private String categories;
    
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Bidding> biddings;

    public Article() {
        
    }
    
    public Article(String name, String description, double startingPrice, Date endDate, String categories) {
        this.name = name;
        this.description = description;
        this.startingPrice = startingPrice;
        this.endDate = endDate;
        this.bonus = 0;
        this.categories = categories;
        
        this.biddings = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public List<Bidding> getBiddings() {
        return biddings;
    }

    public void setBiddings(List<Bidding> biddings) {
        this.biddings = biddings;
    }
    
    public void addBidding(Bidding bidding) {
        bidding.setArticle(this);
        biddings.add(bidding);
    }
    
    public boolean hasEnded() {
        if (endDate.compareTo(new Date()) > 0) {
            return true;
        }
        return false;
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
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", name=" + name + ", description=" + description + ", startingPrice=" + startingPrice + ", endDate=" + endDate + ", bonus=" + bonus + ", categories=" + categories + '}';
    }
    
}
