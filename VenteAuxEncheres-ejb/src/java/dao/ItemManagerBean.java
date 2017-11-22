/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Item;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Leopold
 */
@Stateless
public class ItemManagerBean {

    @PersistenceContext(unitName = "VenteAuxEncheres-ejbPU")
    private EntityManager em;
    
    public List<Item> getAll() {
        TypedQuery<Item> query = em.createNamedQuery("Item.findAll", Item.class);
        List<Item> items = query.getResultList();
        return (items == null ? new ArrayList() : items);
    }
    
}
