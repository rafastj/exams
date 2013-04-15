/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ar.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.ar.mrm.dao.GenericDaoImplementation;
import com.ar.mrm.dao.ProfileDAO;
import com.ar.mrm.entities.Profile;
import com.ar.mrm.entities.TestModel;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author Santiago.Arias
 */
public class CreateProfileAction extends ActionSupport implements SessionAware {

	private final Logger logger = Logger.getLogger(getClass());
    private Map session;
    private String profileName;
    private Boolean profileExists = false;
    private List<Profile> profiles = new ArrayList<Profile>();
    GenericDaoImplementation dao = new GenericDaoImplementation(Profile.class);
    private ProfileDAO profileDAO;
	
    public Boolean getProfileExists() {
		return profileExists;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public void setProfileExists(Boolean profileExists) {
		this.profileExists = profileExists;
	}

	@Override
    public String execute() throws Exception {
		logger.debug("CreateProfileAction");
		
         //validar la existencia de un test model para ese perfil 
         if (profileName.equals("") || !validateProfileDoesNotExist()) {
        	 fillProfiles();
        	 profileExists = true;
			 logger.error("ya tecnologia ya existe para este perfil");

         	 return SUCCESS;
         }
    	
    	peristProfile();
        return SUCCESS;
    }

    private Boolean validateProfileDoesNotExist() {
   	 List<TestModel> result = dao.getProfileByName(profileName);
   	 return result.isEmpty();
	}    
    
    private void fillProfiles() {
    	profiles = profileDAO.getAll();
   	}
    
    private boolean peristProfile(){
        boolean result = true;
        try{
            Profile profile = new Profile();
            profile.setDescription(getProfileName());
            profileDAO.create(profile);
            getSession().put("sucess", "Pfoile " + getProfileName() + " created!");
        } catch (Exception e) {
            e.printStackTrace();
			 logger.error("Error persisting profile",e);
            getSession().put("error", "Error persisting profile");
            result = false;
        }
        return result;
    }

    public void setSession(Map map) {
        this.session = map;
    }

    public Map getSession() {
        return this.session;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

	public ProfileDAO getProfileDAO() {
		return profileDAO;
	}

	public void setProfileDAO(ProfileDAO profileDAO) {
		this.profileDAO = profileDAO;
	}
    
    
}
