/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import database.entityControler.UsersFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import validation.user.EmailValidator;

/**
 *
 * @author yuri
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }

    @EJB
    UsersFacade usersFacade;

    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());

    private String username;

    private String password;

    private String confirmPassword;

    private String email;

    private String nickName;

    private String page;

    /**
     * Get the value of page
     *
     * @return the value of page
     */
    public String getPage() {
        return page;
    }

    /**
     * Set the value of page
     *
     * @param page new value of page
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * Get the value of nickName
     *
     * @return the value of nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Set the value of nickName
     *
     * @param nickName new value of nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of confirmPassword
     *
     * @return the value of confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Set the value of confirmPassword
     *
     * @param confirmPassword new value of confirmPassword
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String actionSignIn() {
        return "index";
    }

    public String actionSignUp() {
        //List l = usersFacade.getEM().createNamedQuery("Users.countEmail").setParameter("email", email).getResultList();
        
        //Logger.getLogger(EmailValidator.class.getName()).log(Level.SEVERE, null, String.format("test %d", l.size()));
        return "index.xhtml?faces-redirect=true";
    }

}
