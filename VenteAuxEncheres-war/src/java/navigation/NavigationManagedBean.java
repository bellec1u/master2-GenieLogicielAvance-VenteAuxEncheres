/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package navigation;

import cookie.CookieHelper;
import dao.UserManagerBean;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.Cookie;

/**
 *
 * @author Leopold
 */
@Named(value = "navigationManagedBean")
@RequestScoped
public class NavigationManagedBean {
    
    private List<NavigationElement> listMenu;
    private Long currentId;
    private String currentLogin;
    
    public NavigationManagedBean() {
        this.listMenu = new ArrayList<>();
        
        Cookie cookieId = CookieHelper.getCookie("authentication_id");
        Cookie cookieLogin = CookieHelper.getCookie("authentication_login");
        
        currentLogin = null;
        
        if ((cookieId == null) || (cookieLogin == null)) {
            this.listMenu.add(new NavigationElement("Connexion", "connexion.xhtml"));
        } else {
            this.listMenu.add(new NavigationElement("Ajouter un article", "newArticle.xhtml"));
            this.listMenu.add(new NavigationElement("Liste des enchères", "biddings.xhtml"));
            currentId = Long.parseLong(cookieId.getValue());
            currentLogin = cookieLogin.getValue();
        }
    }
    
    public List<NavigationElement> getListMenu() {
        return listMenu;
    }
    
    public void setListMenu(List<NavigationElement> listMenu) {
        this.listMenu = listMenu;
    }

    public Long getCurrentId() {
        return currentId;
    }

    public String getCurrentLogin() {
        return currentLogin;
    }
}
