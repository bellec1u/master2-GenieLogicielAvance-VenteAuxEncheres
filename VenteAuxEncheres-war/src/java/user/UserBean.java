/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import cookie.CookieHelper;
import dao.UserManagerBean;
import entity.User;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author j-m_d
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean {
    
    private static final String SECRET = "3__v#LU_c-2R-J6#Mv-L2BMjK#_kh5r9y-6r7D59HD7fbs3#8vF55Rp--63-wc_La35dcSMqc93ZUDj-T-d-8HLPa-4_4_k-n_M85R#_gdrn2422##-Kha5FLR-#P_W-QzFFc3B4cc862";
    
    @EJB
    private UserManagerBean userManager;
    
    private String login, password;
    private User user;
    
    public void authenticate() {
        user = userManager.getByCredentials(login, password);
        
        //expiration
        Calendar calendar = Calendar.getInstance();
        long currentTime = calendar.getTimeInMillis();
        Date expirationDate = new Date(currentTime + (30 * 60000));
        
        //creation token
        /*
        String jwtToken;
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            jwtToken = JWT.create()
                    .withClaim("id", user.getId())
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException | IllegalArgumentException | JWTCreationException ex) {
            
        }
        */
        
        CookieHelper.setCookie("authentication_id", user.getId() + "", (int) TimeUnit.MINUTES.toSeconds(15));
        CookieHelper.setCookie("authentication_login", user.getLogin() + "", (int) TimeUnit.MINUTES.toSeconds(15));
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String outcome = "index.xhtml";
        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, outcome);
    }
    
    public String disconnect() {
        System.out.println("user.UserBean.disconnect()");
        
        CookieHelper.removeCookie("authentication_id");
        CookieHelper.removeCookie("authentication_login");
        
        return "index.xhtml";
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
}
