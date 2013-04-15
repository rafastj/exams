/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ar.actions;

import com.ar.mrm.dao.GenericDaoImplementation;
import com.ar.mrm.entities.Question;
import com.ar.mrm.entities.Seniority;
import com.ar.mrm.entities.Technology;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.HibernateException;

/**
 *
 * @author Santiago.Arias
 */
public class ShowQuestionsByTechnologyAction extends ActionSupport implements RequestAware {

	private Logger logger = Logger.getLogger(ShowQuestionsByTechnologyAction.class);
	
    private List<Technology> technologies = new ArrayList<Technology>();
    private List<Seniority> seniorities = new ArrayList<Seniority>();
    
    private Map request;
    
    
    private GenericDaoImplementation questionsDao = new GenericDaoImplementation(Question.class);
    private GenericDaoImplementation technologiesDao = new GenericDaoImplementation(Technology.class);
    private GenericDaoImplementation senioritiesDao = new GenericDaoImplementation(Seniority.class);
    
    
    private List<Question> questionsRetreived = new ArrayList<Question>();
    private String technology;
    private String seniority;
        
    @Override
    public String execute() throws Exception {
    	
    	
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
    		seniorities = senioritiesDao.getAll();
    	}catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while reading seniorities", e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("ERROR - While reading a seniorities", e);
			e.printStackTrace();
		}
        
        this.questionsRetreived = retreiveQuestionsFromDb(technology, seniority);
        
        return SUCCESS;
    }

    private List<Question> retreiveQuestionsFromDb(String questionTechDescription, String questionTechDescription2) {
        List<Question> result = new ArrayList<Question>();
        logger.debug("FROM Question where technology.id = " + questionTechDescription + " AND seniority.id = " + questionTechDescription2 + "");
        try{
        	result = questionsDao.executeQuery("FROM Question where technology.id = " + questionTechDescription + " AND seniority.id = " + questionTechDescription2 + "");
        	
    	}catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while reading questions", e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("ERROR - While reading questions", e);
			e.printStackTrace();
		}
        
        //result = questionsDao.executeQuery("FROM Question");
        logger.debug("result size " + result.size());
        return result;

    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    } 

    public GenericDaoImplementation getQuestionsDao() {
        return questionsDao;
    }

    public void setQuestionsDao(GenericDaoImplementation questionsDao) {
        this.questionsDao = questionsDao;
    }

    public List<Question> getQuestionsRetreived() {
        return questionsRetreived;
    }

    public void setQuestionsRetreived(List<Question> questionsRetreived) {
        this.questionsRetreived = questionsRetreived;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }

    public GenericDaoImplementation getTechnologiesDao() {
        return technologiesDao;
    }

    public void setTechnologiesDao(GenericDaoImplementation technologiesDao) {
        this.technologiesDao = technologiesDao;
    }

	public List<Seniority> getSeniorities() {
		return this.seniorities;
	}

	public void setSeniorities(List<Seniority> seniorities) {
		this.seniorities = seniorities;
	}

	public GenericDaoImplementation getSenioritiesDao() {
		return senioritiesDao;
	}

	public void setSenioritiesDao(GenericDaoImplementation senioritiesDao) {
		this.senioritiesDao = senioritiesDao;
	}

	public String getSeniority() {
		return this.seniority;
	}

	public void setSeniority(String seniority) {
		this.seniority = seniority;
	}

	public void setRequest(Map arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

    
}
