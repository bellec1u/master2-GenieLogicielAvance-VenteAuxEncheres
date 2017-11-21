/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package item;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Leopold
 */
@Named(value = "itemManagedBean")
@RequestScoped
public class ItemManagedBean {
    
    private List<String> listItems;
    
    /**
     * Creates a new instance of ItemManagedBean
     */
    public ItemManagedBean() {
        this.listItems = new ArrayList();
        for (int i = 0; i < 7; i++) {
            this.listItems.add( "item " + i );
        }
    }
    
    public List<String> getListItems() {
        return listItems;
    }
    
    public void setListItems(List<String> listItems) {
        this.listItems = listItems;
    }
    
}
