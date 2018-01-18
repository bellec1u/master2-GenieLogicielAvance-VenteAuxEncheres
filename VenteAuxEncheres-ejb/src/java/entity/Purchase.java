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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Leopold
 */
@Entity
@Table(name="Purchases")
@NamedQueries({
    @NamedQuery(name = "Purchase.findAll", 
            query = "select p from Purchase p"),
    @NamedQuery(name = "Purchase.findByArticleId",
            query = "SELECT p FROM Purchase p WHERE p.article.id = :articleId")
})
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String address;
    @NotNull
    private String bankAccountNumber;
    @NotNull
    private boolean isShipped;
    @NotNull
    private boolean isPayed;
    
    @OneToOne
    private Article article;
    
    public Purchase() {
        
    }
    
    public Purchase(String address, String bankAccountNumber, boolean isShipped, boolean isPayed) {
        this.address = address;
        this.bankAccountNumber = bankAccountNumber;
        this.isShipped = isShipped;
        this.isPayed = isPayed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public boolean isIsShipped() {
        return isShipped;
    }

    public void setIsShipped(boolean isShipped) {
        this.isShipped = isShipped;
    }

    public boolean isIsPayed() {
        return isPayed;
    }

    public void setIsPayed(boolean isPayed) {
        this.isPayed = isPayed;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
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
        if (!(object instanceof Purchase)) {
            return false;
        }
        Purchase other = (Purchase) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Purchase{" + "id=" + id + ", address=" + address + ", bankAccountNumber=" + bankAccountNumber + ", isShipped=" + isShipped + ", isPayed=" + isPayed + '}';
    }
    
    public String toJSON() {
        return "{\"id\": " + id + ", \"address\": \"" + address + "\", \"bankAccountNumber\": \"" + bankAccountNumber + "\", \"isShipped\": " + isShipped + ", \"isPayed\": " + isPayed + "}";
    }
}
