package com.ar.actions;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;

import com.ar.mrm.dao.GenericDaoImplementation;
import com.ar.mrm.entities.Applicant;
import com.ar.mrm.entities.Profile;
import com.ar.mrm.entities.Seniority;
import com.ar.mrm.entities.Technology;
import com.ar.mrm.entities.TestModel;
import com.ar.mrm.entities.TestModelQuestionqty;
import com.opensymphony.xwork2.ActionSupport;


public class ReloadActionWithTestModel extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -968827611006330582L;
	
    @SuppressWarnings("rawtypes")
	private Map session;
	
	protected List<Profile> profiles;
	protected List<Seniority> seniorities;
	protected List<Applicant> applicants;
	protected List<Technology> technologies;
    protected List<TestModel> testModels;
	protected GenericDaoImplementation profilesDao = new GenericDaoImplementation(Profile.class);
	protected GenericDaoImplementation seniorityDao = new GenericDaoImplementation(Seniority.class);
    protected GenericDaoImplementation applicantDao = new GenericDaoImplementation(Applicant.class);
    protected GenericDaoImplementation technologiesDao = new GenericDaoImplementation(Technology.class);
    protected GenericDaoImplementation testModelsDao = new GenericDaoImplementation(TestModel.class);
    private Logger logger = Logger.getLogger(QuestionCreationInfoAction.class);
	private String profile;
    private String seniority;
	private TestModel testModelRetreived;
	private Set<TestModelQuestionqty> questionList;
	
    @Override
    public String execute() throws Exception {

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
        
    	if((seniority==null)||(profile==null)||(profile.equalsIgnoreCase("0"))||(seniority.equalsIgnoreCase("0"))){
    		//return SUCCESS;
        	return "NO_TEST_MODEL_AVAILABLE";
    	}
    	
        
    	
    	
        //Profile prof = (Profile) profilesDao.read(Integer.parseInt(profile));
        //Seniority sen = (Seniority) seniorityDao.read(Integer.parseInt(seniority));
    	
        List<TestModel> testModelsForUser = (List<TestModel>) testModelsDao.executeQuery("from TestModel where profile.id=" + profile
                + " and seniority.id = " + seniority);
        
        if((testModelsForUser!=null)&&(!testModelsForUser.isEmpty())){
        	testModelRetreived = testModelsForUser.get(0);
        	setQuestionList(testModelRetreived.getTestModelQuestionqties());
        }else{
        	return "NO_TEST_MODEL_AVAILABLE";
        }
        
    	return SUCCESS;
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



	public TestModel getTestModelRetreived() {
		return testModelRetreived;
	}


	public void setTestModelRetreived(TestModel testModelRetreived) {
		this.testModelRetreived = testModelRetreived;
	}


	public Map getSession() {
		return session;
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


	public GenericDaoImplementation getApplicantDao() {
		return applicantDao;
	}


	public void setApplicantDao(GenericDaoImplementation applicantDao) {
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


    @SuppressWarnings("rawtypes")
	public void setSession(Map session) {
        this.session = session;
    }


	public Set<TestModelQuestionqty> getQuestionList() {
		return questionList;
	}


	public void setQuestionList(Set<TestModelQuestionqty> questionList) {
		this.questionList = questionList;
	}

}
