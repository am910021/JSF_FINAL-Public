/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import EditorView.IndexBean;
import database.entities.Users;
import database.entityControler.UsersFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import tool.Crypto;
import tool.Smtp;
import validator.user.EmailValidator;

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

    private String nickname;

    private String page;
    
        private String token;

    /**
     * Get the value of token
     *
     * @return the value of token
     */
    public String getToken() {
        return token;
    }

    /**
     * Set the value of token
     *
     * @param token new value of token
     */
    public void setToken(String token) {
        this.token = token;
    }


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
     * Get the value of nickname
     *
     * @return the value of nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Set the value of nickname
     *
     * @param nickName new value of nickname
     */
    public void setNickname(String nickName) {
        this.nickname = nickName;
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
        return "/index.xhtml?faces-redirect=true";
    }
    
    public String actionLogin() {
        return "/index.xhtml?faces-redirect=true";
    }

    public String actionSignUp() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, 2);

        String token = Crypto.sha512(String.valueOf(cal.getTimeInMillis()) + email + username);

        Users user = new Users();
        user.setEmail(email);
        user.setNickName(nickname);
        user.setUsername(username);
        user.setPassword(Crypto.sha512(password));
        user.setDescription("");
        user.setExpiryDate(cal.getTime());
        user.setToken(token);
        usersFacade.create(user);

        String url = request.getRequestURL().toString();
        String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";

        String activate = "faces/service/activate.xhtml?token=" + token;

        String subject = "啟用你的帳號";
        String text = String.format("親愛的%s:\n", username)
                + String.format("感謝您於%s註冊本網站%s,本網站網址：%s \n", dateFormat.format(date), IndexBean.SITE_NAME, baseURL)
                + "啟用到期日為 " + dateFormat.format(cal.getTime()) + " 您需要在48小時內啟用帳號，不然此次註冊將會無效。\n如果您未註冊過本網站的帳號，可以不用理會這封信。"
                + "以下是您的啟用帳號連結：" + baseURL + activate;

        if (!Smtp.send(email, subject, text)) {
            usersFacade.remove(user);
        }

        return page + "?faces-redirect=true";
    }

    
    public boolean isActivate(){
        List<Users> l = usersFacade.getEM().createNamedQuery("Users.findUserByToken").setParameter("token", token).getResultList();
        if(l.isEmpty()){
            return false;
        }
        Users user = l.get(0);
        
        //user.setActivate(true);
        //user.setToken("");
        //user.setExpiryDate(null);
        
        //usersFacade.edit(user);
        
        
        return true;
    }
    
    
    private void clean() {
        username = null;
        password = null;
        confirmPassword = null;
        email = null;
        nickname = null;
    }

}
