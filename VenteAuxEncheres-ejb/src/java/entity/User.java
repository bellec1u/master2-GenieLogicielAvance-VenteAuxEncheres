/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Leopold
 */
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name = "login")
    private String login;
    @NotNull
    @Column(name = "mdp")
    private String mdp;
    @NotNull
    @Column(name = "nom")
    private String nom;
    @NotNull
    @Column(name = "prenom")
    private String prenom;
    
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "numero_compte_bancaire")
    private String numeroCompteBancaire;
    
    public User(String login, String mdp, String nom, String prenom) {
        this.login = login;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
    }
    
    public User(String login, String mdp, String nom, String prenom, String adresse, String ncb) {
        this.login = login;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numeroCompteBancaire = ncb;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.User[ "
                + "\n\tid=" + id 
                + "\n\tlogin=" + login
                + "\n\tnom=" + nom
                + "\n\tprenom=" + prenom
                + "\n\tadresse=" + adresse
                + "\n\tnumero_compte_bancaire=" + numeroCompteBancaire
                + "\n]";
    }
    
}
