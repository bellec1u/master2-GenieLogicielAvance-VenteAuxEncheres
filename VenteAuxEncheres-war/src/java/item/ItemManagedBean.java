/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package item;

import dao.ItemManagerBean;
import dao.UserManagerBean;
import entity.Item;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Leopold
 */
@Named(value = "itemManagedBean")
@RequestScoped
public class ItemManagedBean {
    
    @EJB
    private UserManagerBean umb;
    @EJB
    private ItemManagerBean imb;
        
    /**
     * Creates a new instance of ItemManagedBean
     */
    public ItemManagedBean() {
        
    }
    
    // p-e a bouger
    public void initBDD() {
        User u = new User("login", "password", "lastName", "firstName");
      
        Item i = new Item("nom", "description", 18);
        Item i2 = new Item("nom2", "description2", 19);
        
        u.addItem(i);
        u.addItem(i2);

        umb.add(u);
    }
    
    public List<Item> getAllItems() {
        return imb.getAll();
    }
    
}
