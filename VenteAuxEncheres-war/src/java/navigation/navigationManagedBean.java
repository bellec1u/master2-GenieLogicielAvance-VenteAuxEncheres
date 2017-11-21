/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package navigation;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Leopold
 */
@Named(value = "navigationManagedBean")
@RequestScoped
public class navigationManagedBean {
    
    private List<String> listMenu;
    
    /**
     * Creates a new instance of navigationManagedBean
     */
    public navigationManagedBean() {
        this.listMenu = new ArrayList();
        this.listMenu.add( "Menu 1" );
        this.listMenu.add( "Menu 2" );
        this.listMenu.add( "Menu 3" );
    }
    
    public List<String> getListMenu() {
        return listMenu;
    }
    
    public void setListMenu(List<String> listMenu) {
        this.listMenu = listMenu;
    }
    
}
