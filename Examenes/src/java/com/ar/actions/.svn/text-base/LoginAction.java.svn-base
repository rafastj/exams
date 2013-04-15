/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ar.actions;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Santi
 */
public  class LoginAction  extends ActionSupport  implements SessionAware {
 private Map session ;
 private Logger logger = Logger.getLogger(LoginAction.class);
 
	 public String execute() throws Exception {
	     System.out.println("Validating login");
	     logger.info("user: "+getUsername());
	     
		 getSession().put("user", getUsername());
	     
	     return SUCCESS; //solo para dev para deshabilitar el password

	   //para habilitar el password descomentar esta linea
	     /*
		 if(!getUsername().equals("Admin") || !getPassword().equals("Mrm2010")){
		        addActionError("Invalid user name or password! Please try again!");
	     		logger.error("Invalid user name or password! Please try again!");
		         return ERROR;
		 }else{
		 getSession().put("user", getUsername());
	     logger.info("user: "+getUsername());
		 	return SUCCESS;  
		 }
	      */
	 
	}


    // ---- Username property ----

    /**
     * <p>Field to store User username.</p>
     * <p/>
     */
    private String username = null;


    /**
     * <p>Provide User username.</p>
     *
     * @return Returns the User username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * <p>Store new User username</p>
     *
     * @param value The username to set.
     */
    public void setUsername(String value) {
        username = value;
    }

    // ---- Username property ----

    /**
     * <p>Field to store User password.</p>
     * <p/>
     */
    private String password = null;


    /**
     * <p>Provide User password.</p>
     *
     * @return Returns the User password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * <p>Store new User password</p>
     *
     * @param value The password to set.
     */
    public void setPassword(String value) {
        password = value;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public Map getSession() {
        return this.session;
    }

}