/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ar.actions;

import com.ar.mrm.dao.GenericDaoImplementation;
import com.ar.mrm.entities.Profile;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Santiago.Arias
 */
@SuppressWarnings("serial")
public class GetProfilesActionIntranet extends ActionSupport implements SessionAware {
	private static Logger logger = Logger.getLogger(GetProfilesActionIntranet.class);

	@SuppressWarnings({ "unchecked" })
	GenericDaoImplementation profileDao = new GenericDaoImplementation(Profile.class);
	
	List<Profile> profiles;
	
	@SuppressWarnings({ "unchecked" })
	private Map session;
	
    @SuppressWarnings("unchecked")
	public String execute() throws Exception {
    	profiles = null;
    	
        if (!hasProfiles()) {
        	session.put("codeProfilesError", "Sorry, not profiles availables...");
            return ERROR;
        }
        
        session.put("profiles", profiles);
        
        return SUCCESS;
    }
   
    @SuppressWarnings("unchecked")
	public boolean hasProfiles() {
    	logger.debug("public boolean hasProfiles()");
        boolean result = false;
        
        profiles = profileDao.getAll();
        
        if(profiles != null && !profiles.isEmpty()) 
        {
        	result = true;
        }
        
        return result;
    }

    @SuppressWarnings("unchecked")
	public void setSession(Map session) {
        this.session = session;
    }

    @SuppressWarnings("unchecked")
	public Map getSession() {
        return this.session;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }
}
