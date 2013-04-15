/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ar.actions;

import com.ar.mrm.dao.GenericDaoImplementation;
import com.ar.mrm.entities.Profile;
import com.ar.mrm.entities.Seniority;
import com.ar.mrm.entities.Technology;
import com.ar.mrm.entities.TestModel;
import com.ar.mrm.entities.TestModelQuestionqty;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;

/**
 *
 * @author Santiago.Arias
 */
public class ShowTechQuestionsForModelAction extends ActionSupport implements SessionAware {

	private Logger logger = Logger.getLogger(ShowTechQuestionsForModelAction.class);
	private String profile;
    private String seniority;
    private Map session;
    private GenericDaoImplementation seniorityDao = new GenericDaoImplementation(Seniority.class);
    private GenericDaoImplementation profileDao = new GenericDaoImplementation(Profile.class);
    private GenericDaoImplementation technologyDao = new GenericDaoImplementation(Technology.class);
    private GenericDaoImplementation testModelDao = new GenericDaoImplementation(TestModel.class);
    private GenericDaoImplementation testModelQuestionqtyDao = new GenericDaoImplementation(TestModelQuestionqty.class);
    private String testModel;
    
    private List<Technology> technologiesrow = new ArrayList<Technology>();
    private List<Seniority> senioritiesrow = new ArrayList<Seniority>();
    
    private List<Profile> profiles = new ArrayList<Profile>();
    private List<Seniority> seniorities = new ArrayList<Seniority>();
    
    private String technologyrow ;
    private String seniorityrow;
    
    private String count;
    
    
    private List<TestModelQuestionqty> actualtechno = new ArrayList<TestModelQuestionqty>();
		
    public String execute() throws Exception {
        
    	List<TestModel>testModelProf = new ArrayList<TestModel>();
    	
    	try {
    		technologiesrow = technologyDao.getAll();
		} catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while reading technologies", e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("ERROR - While reading a technologies", e);
			e.printStackTrace();
		}
    	try{
    		senioritiesrow = seniorityDao.getAll();
    	}catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while reading seniorities", e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("ERROR - While reading a seniorities", e);
			e.printStackTrace();
		}
    	try{
    		profiles = profileDao.getAll();
    		
    	}catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while reading profiles", e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("ERROR - While reading a profiles", e);
			e.printStackTrace();
		}
    	seniorities = senioritiesrow;
    	
    	
    return SUCCESS;
    }

    public void setSession(Map map) {
        this.session = map;
    }

    public Map getSession() {
        return this.session;
    }

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getSeniority() {
		return seniority;
	}

	public void setSeniority(String seniority) {
		this.seniority = seniority;
	}

	public GenericDaoImplementation getSeniorityDao() {
		return seniorityDao;
	}

	public void setSeniorityDao(GenericDaoImplementation seniorityDao) {
		this.seniorityDao = seniorityDao;
	}

	public GenericDaoImplementation getProfileDao() {
		return profileDao;
	}

	public void setProfileDao(GenericDaoImplementation profileDao) {
		this.profileDao = profileDao;
	}

	public GenericDaoImplementation getTechnologyDao() {
		return technologyDao;
	}

	public void setTechnologyDao(GenericDaoImplementation technologyDao) {
		this.technologyDao = technologyDao;
	}

	public List<Technology> getTechnologiesrow() {
		return technologiesrow;
	}

	public void setTechnologiesrow(List<Technology> technologiesrow) {
		this.technologiesrow = technologiesrow;
	}

	public List<Seniority> getSenioritiesrow() {
		return senioritiesrow;
	}

	public void setSenioritiesrow(List<Seniority> senioritiesrow) {
		this.senioritiesrow = senioritiesrow;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public List<Seniority> getSeniorities() {
		return seniorities;
	}

	public void setSeniorities(List<Seniority> seniorities) {
		this.seniorities = seniorities;
	}

	public String getTechnologyrow() {
		return technologyrow;
	}

	public void setTechnologyrow(String technologyrow) {
		this.technologyrow = technologyrow;
	}

	public String getSeniorityrow() {
		return seniorityrow;
	}

	public void setSeniorityrow(String seniorityrow) {
		this.seniorityrow = seniorityrow;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public GenericDaoImplementation getTestModelDao() {
		return testModelDao;
	}

	public void setTestModelDao(GenericDaoImplementation testModelDao) {
		this.testModelDao = testModelDao;
	}

	public GenericDaoImplementation getTestModelQuestionqtyDao() {
		return testModelQuestionqtyDao;
	}

	public void setTestModelQuestionqtyDao(
			GenericDaoImplementation testModelQuestionqtyDao) {
		this.testModelQuestionqtyDao = testModelQuestionqtyDao;
	}

	public List<TestModelQuestionqty> getActualtechno() {
		return actualtechno;
	}

	public void setActualtechno(List<TestModelQuestionqty> actualtechno) {
		this.actualtechno = actualtechno;
	}

	public String getTestModel() {
		return testModel;
	}

	public void setTestModel(String testModel) {
		this.testModel = testModel;
	}
    
    
}


    

    
