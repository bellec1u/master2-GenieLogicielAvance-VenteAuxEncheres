/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Leopold
 */
@Entity
@Table(name="Items")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "starting_price")
    private double startingPrice;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Categorie> categories;
    @Column(name = "duree_de_vente")
    private int dureeDeVente;

    public Item() {
        
    }
    
    public Item(String name, String description, int startingPrice) {
        this.name = name;
        this.description = description;
        this.startingPrice = startingPrice;
        
        this.categories = new ArrayList();
    }
    
    public void addCategorie(Categorie c) {
        this.categories.add(c);
    }
    
    public void delCategorie() {
        System.err.println("----- ----- Item.delCategorie()");
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

    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
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
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Item[ "
                + "\n\tid=" + id + ","
                + "\n\tnom=" + name + ","
                + "\n\tdescription=" + description + ","
                + "\n\tprixDeDepart=" + startingPrice + ","
                + "\n\tcategories=" + categories
                + "\n]";
    }
    
}
