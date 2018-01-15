/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navigation;

/**
 *
 * @author j-m_d
 */
public class NavigationElement {
    
    private String displayName, link;

    public NavigationElement(String name, String link){
        this.displayName = name;
        this.link = link;
    }
    
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
}
