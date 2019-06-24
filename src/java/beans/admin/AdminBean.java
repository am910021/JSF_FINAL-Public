/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.admin;

import database.entities.Users;
import database.entityControler.GroupsFacade;
import database.entityControler.UsersFacade;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import tool.Crypto;

/**
 *
 * @author yuri
 */
@Named(value = "adminBean")
@RequestScoped
public class AdminBean {

    private static final Logger LOG = Logger.getLogger(AdminBean.class.getName());

    @EJB
    UsersFacade usersFacade;

    @EJB
    GroupsFacade groupsFacade;

    /**
     * Creates a new instance of AdminBean
     */
    public AdminBean() {
    }

    private Users user;

    private int status;

    private String description = "";

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the value of status
     *
     * @return the value of status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Set the value of status
     *
     * @param status new value of status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public Users getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(Users user) {
        
            this.status = user.getStatus();

        this.user = user;
    }

    public List<Users> getUsers() {

        LOG.log(Level.SEVERE, String.format("getusers %d\n", usersFacade.findAll().size()));

        return usersFacade.findAll();
    }

    public String actionUserEdit() {
        String url = "/admin/user/list.xhtml?faces-redirect=true";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, 2);
        if (status == 0) {
            if (user.getStatus() == 0) {
                return url;
            }
            user.setActivate(true);
            user.setToken("");
            user.setExpiryDate(null);
            user.setStatus((byte) 0);
            user.setDescription("");
            usersFacade.edit(user);
        }

        if (status == 1) {
            if (user.getStatus() == 1) {
                return url;
            }
            user.setActivate(true);
            user.setToken("");
            user.setExpiryDate(null);
            user.setStatus((byte) 1);
            user.setDescription(description + "\n" + dateFormat.format(date));
            usersFacade.edit(user);
        }

        if (status == 2) {
            if (user.getStatus() == 2) {
                return url;
            }
            String token = Crypto.sha512(String.valueOf(cal.getTimeInMillis()) + user.getEmail() + user.getUsername());
            user.setActivate(false);
            user.setToken(token);
            user.setExpiryDate(cal.getTime());
            user.setStatus((byte) 2);
            user.setDescription("");
            usersFacade.edit(user);
        }

        return "/admin/user/list.xhtml?faces-redirect=true";
    }

}
