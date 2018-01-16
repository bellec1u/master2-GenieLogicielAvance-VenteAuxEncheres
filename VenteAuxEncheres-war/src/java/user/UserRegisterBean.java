/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import cookie.CookieHelper;
import dao.UserManagerBean;
import entity.User;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Leopold
 */
@Named(value = "userRegisterBean")
@RequestScoped
public class UserRegisterBean {

    @EJB
    private UserManagerBean userManager;

    private String login;
    private String password;
    private String passwordValidation;
    private String lastname;
    private String firstname;
    private String address;

    public UserRegisterBean() {
        this.login = "";
        this.password = "";
        this.passwordValidation = "";
        this.lastname = "";
        this.firstname = "";
        this.address = "";
    }

    public String register() {
        if (formCheck() && password.equals(passwordValidation)) {
            // register
            User user = userManager.create(new User(login, password, lastname, firstname, address));
            // connect
            CookieHelper.setCookie("authentication_id", user.getId() + "", (int) TimeUnit.MINUTES.toSeconds(15));
            CookieHelper.setCookie("authentication_login", user.getLogin() + "", (int) TimeUnit.MINUTES.toSeconds(15));
        }
        return "index";
    }

    private boolean formCheck() {
        if (!"".equals(login) && !"".equals(password) && !"".equals(passwordValidation)
                && !"".equals(lastname) && !"".equals(firstname) && !"".equals(address)) {
            return true;
        }
        return false;
    }

    public UserManagerBean getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManagerBean userManager) {
        this.userManager = userManager;
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

    public String getPasswordValidation() {
        return passwordValidation;
    }

    public void setPasswordValidation(String passwordValidation) {
        this.passwordValidation = passwordValidation;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
