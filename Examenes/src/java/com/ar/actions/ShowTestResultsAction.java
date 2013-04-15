/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ar.actions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.ar.mrm.dao.ApplicantDAO;
import com.ar.mrm.dao.GenericDaoImplementation;
import com.ar.mrm.dao.TechnologyDAO;
import com.ar.mrm.entities.Applicant;
import com.ar.mrm.entities.QuestionAnswered;
import com.ar.mrm.entities.TechScore;
import com.ar.mrm.entities.Technology;
import com.ar.mrm.entities.Test;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author Santiago.Arias
 */
public class ShowTestResultsAction extends ActionSupport  implements SessionAware {

	private static final long serialVersionUID = -4793399897348301519L;

	private Logger logger = Logger.getLogger(ShowTestResultsAction.class);
	
    private String applicant; 
    private String applicantName=null;
    private String applicantLastName=null;
    private Date beginDate=null;
    private Date endDate=null;
    private boolean onlyResult;

    private String testTaked;
    
	private List<QuestionAnswered> questionsAnswered = new ArrayList<QuestionAnswered>();
    private List<Applicant> applicants = new ArrayList<Applicant>();
    private List<Technology> technologies = new ArrayList<Technology>();
    private ApplicantDAO applicantDao;
    private TechnologyDAO technologyDao;
    private List<TechScore> testScores = new ArrayList<TechScore>();
    private List testScoresList = new ArrayList();
    private Test test;
    private Map session ;
    GenericDaoImplementation techScoreDao = new GenericDaoImplementation(TechScore.class);
    GenericDaoImplementation testDao = new GenericDaoImplementation(Test.class);

    public ShowTestResultsAction() {
    }

    @Override
    public String execute() throws Exception {
    	
    	
    	List<Applicant> applicantList = null;
    	applicantList = getAplicantSelecteds(applicantName, applicantLastName, beginDate, endDate, testTaked);
    			
    	if (applicantName!=null&&(applicantList==null&&!validateApplicant())) {
            return ERROR;
        }
    	
	    if(!applicants.isEmpty()&&applicants.size()<2){
	    	// encontro solo un resultado y no muestra una lista , muestra directamente el detalle del examen
	    	
	    	if(applicantName!=null){
	    		test = getTestForUser(applicants.get(0).getId().toString());
	    	}else{
	    		test = getTestForUser(applicant);
	    	}
	    	onlyResult=true;
	    	technologies = technologyDao.getAll();
	        if(test == null){
	    		logger.error("no test for user, test is null");
	            return ERROR;
	        }
	        for (QuestionAnswered q : test.getQuestionAnswereds()) {
	            questionsAnswered.add(q);
	        }
	        
	        return SUCCESS;
	    }else{
	    	// hay mas de un resultado y muestra una lista de applicantes
	    	
	    	onlyResult=false;
	    	return SUCCESS;
	    }

    }
    
    public String showTest() {
    	if(applicantName!=null){
    		test = getTestForUser(applicants.get(0).getId().toString());
    	}else{
    		test = getTestForUser(applicant);
    	}
    	technologies = technologyDao.getAll();
        if(test == null){
    		logger.error("no test for user, test is null");
             return ERROR;
        }
        for (QuestionAnswered q : test.getQuestionAnswereds()) {
            questionsAnswered.add(q);
        }
        onlyResult=true;
        return SUCCESS;
    }

    private boolean validateApplicant() {
        /*if (getApplicant().equalsIgnoreCase("0")) {
            return false;
        }*/
        return true;
    }
    
    private List<Applicant> getAplicantSelecteds(String firstName,  String lastName, Date beginDate, Date endDate, String testTaked){
    	applicants = applicantDao.getApplicantsForName(firstName, lastName, beginDate, endDate, testTaked);
		return applicants;    	
    }

    private Test getTestForUser(String applicant) {
        List<Test> allTestForUser = (List<Test>) testDao.executeQuery("from Test where applicant.id=" + applicant);
        if(allTestForUser.isEmpty()){
        	logger.error("No tests taken to this Applicant");
            getSession().put("error", "No tests taken to this Applicant");
            return null;
        }
        Test t = allTestForUser.get(allTestForUser.size()-1);
        testScores = (List<TechScore>) techScoreDao.executeQuery("from TechScore where test.id=" + t.getId());
        logger.debug("testScores size -- > " + testScores.size());
        return t;
    }
    
    public boolean isOnlyResult() {
		return onlyResult;
	}

	public void setOnlyResult(boolean onlyResult) {
		this.onlyResult = onlyResult;
	}

    public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
    
    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }


    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }


    public List<Applicant> getApplicants() {
        return applicants;
    }

    public void setApplicants(List<Applicant> applicants) {
        this.applicants = applicants;
    }

    public List<QuestionAnswered> getQuestionsAnswered() {
        return questionsAnswered;
    }

    public void setQuestionsAnswered(List<QuestionAnswered> questionsAnswered) {
        this.questionsAnswered = questionsAnswered;
    }

     public void setSession(Map session) {
        this.session = session;
    }

    public Map getSession() {
        return  this.session;
    }

    public List<TechScore> getTestScores() {
        return testScores;
    }

    public void setTestScores(List<TechScore> testScores) {
        this.testScores = testScores;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }

	public String getApplicantLastName() {
		return applicantLastName;
	}

	public void setApplicantLastName(String applicantLastName) {
		this.applicantLastName = applicantLastName;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getFormattedScore(Double score) {
		return new DecimalFormat("##.##").format(score*100);
	}

	public String getTestTaked() {
		return testTaked;
	}

	public void setTestTaked(String testTaked) {
		this.testTaked = testTaked;
	}

	public List getTestScoresList() {
		return testScoresList;
	}

	public void setTestScoresList(List testScoresList) {
		this.testScoresList = testScoresList;
	}

	public ApplicantDAO getApplicantDao() {
		return applicantDao;
	}

	public void setApplicantDao(ApplicantDAO applicantDao) {
		this.applicantDao = applicantDao;
	}

	public TechnologyDAO getTechnologyDao() {
		return technologyDao;
	}

	public void setTechnologyDao(TechnologyDAO technologyDao) {
		this.technologyDao = technologyDao;
	}

    
}
