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
public class LogOutAction extends ActionSupport implements SessionAware {

    private Map session;
    private Logger logger = Logger.getLogger(LoginAction.class);

    
    @Override
    public String execute() throws Exception {      
        getSession().clear();
        logger.info("Logged Out");
        getSession().put("sucess", "Logged Out!");
        
        return SUCCESS;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public Map getSession() {
        return this.session;
    }
}
