/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ar.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.ar.mrm.dao.ApplicantDAO;
import com.ar.mrm.dao.GenericDaoImplementation;
import com.ar.mrm.entities.Applicant;
import com.ar.mrm.entities.Profile;
import com.ar.mrm.entities.Seniority;
import com.ar.mrm.entities.Technology;
import com.ar.mrm.entities.TestModel;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author Santiago.Arias
 */
public class QuestionCreationInfoAction extends ActionSupport {

	private static final long serialVersionUID = 8992503535370302196L;
	
	protected List<Profile> profiles = new ArrayList<Profile>();
	protected List<Seniority> seniorities = new ArrayList<Seniority>();
	protected List<Applicant> applicants = new ArrayList<Applicant>();
	protected List<Technology> technologies = new ArrayList<Technology>();
    protected List<TestModel> testModels = new ArrayList<TestModel>();
	protected GenericDaoImplementation profilesDao = new GenericDaoImplementation(Profile.class);
	protected GenericDaoImplementation seniorityDao = new GenericDaoImplementation(Seniority.class);
    //protected GenericDaoImplementation applicantDao = new GenericDaoImplementation(Applicant.class);
	protected ApplicantDAO applicantDao; 
    protected GenericDaoImplementation technologiesDao = new GenericDaoImplementation(Technology.class);
    protected GenericDaoImplementation testModelsDao = new GenericDaoImplementation(TestModel.class);
    private Logger logger = Logger.getLogger(QuestionCreationInfoAction.class);
    private String editMode;

    private Date todayDate = new Date();
  
    
    @Override
       public String execute() throws Exception {
    	
    	logger.debug("QuestionCreationInfoAction");
    	
    	try{
    		profiles = profilesDao.getAll();
        }catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while reading profiles", e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("ERROR - While reading a profiles", e);
			e.printStackTrace();
		}
    	
    	try{
    		seniorities = seniorityDao.getAll();
    	}catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while reading seniorities", e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("ERROR - While reading a seniorities", e);
			e.printStackTrace();
		}

    	try{
    		applicants = applicantDao.getAll();
    	}catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while reading applicants", e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("ERROR - While reading a applicants", e);
			e.printStackTrace();
		}

    	try {
    		technologies = technologiesDao.getAll();
		} catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while reading technologies", e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("ERROR - While reading a technologies", e);
			e.printStackTrace();
		}
        
    	try{
    		testModels = testModelsDao.executeQuery(
    				"FROM TestModel as tm " +
    						"WHERE tm.id = (SELECT MAX(f.id) FROM TestModel AS f WHERE f.seniority.id = tm.seniority.id AND f.profile.id = tm.profile.id)"
    				);
    	}catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while reading test Models", e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("ERROR - Hibernate Exception while reading test Models", e);
			e.printStackTrace();
		}
        return SUCCESS;
           
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

    public List<Applicant> getApplicants() {
        return applicants;
    }

    public void setApplicants(List<Applicant> applicants) {
        this.applicants = applicants;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }

    public List<TestModel> getTestModels() {
        return testModels;
    }

    public void setTestModels(List<TestModel> testModels) {
        this.testModels = testModels;
    }

	public Date getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(Date todayDate) {
		this.todayDate = todayDate;
	}


	public GenericDaoImplementation getProfilesDao() {
		return profilesDao;
	}


	public void setProfilesDao(GenericDaoImplementation profilesDao) {
		this.profilesDao = profilesDao;
	}


	public GenericDaoImplementation getSeniorityDao() {
		return seniorityDao;
	}


	public void setSeniorityDao(GenericDaoImplementation seniorityDao) {
		this.seniorityDao = seniorityDao;
	}

	public ApplicantDAO getApplicantDao() {
		return applicantDao;
	}


	public void setApplicantDao(ApplicantDAO applicantDao) {
		this.applicantDao = applicantDao;
	}


	public GenericDaoImplementation getTechnologiesDao() {
		return technologiesDao;
	}


	public void setTechnologiesDao(GenericDaoImplementation technologiesDao) {
		this.technologiesDao = technologiesDao;
	}


	public GenericDaoImplementation getTestModelsDao() {
		return testModelsDao;
	}


	public void setTestModelsDao(GenericDaoImplementation testModelsDao) {
		this.testModelsDao = testModelsDao;
	}


	public String getEditMode() {
		return editMode;
	}


	public void setEditMode(String editMode) {
		this.editMode = editMode;
	}



}
