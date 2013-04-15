/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ar.actions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.ar.mrm.dao.ApplicantDAO;
import com.ar.mrm.dao.GenericDaoImplementation;
import com.ar.mrm.dao.ProfileDAO;
import com.ar.mrm.dao.SeniorityDAO;
import com.ar.mrm.entities.Applicant;
import com.ar.mrm.entities.Profile;
import com.ar.mrm.entities.Seniority;
import com.ar.mrm.entities.TestModel;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author Santiago.Arias
 */
@SuppressWarnings("serial")
public class CreateApplicantAction extends ActionSupport implements SessionAware {
	private static Logger logger = Logger.getLogger(CreateApplicantAction.class);
	
    private static final int NUMBER_OF_RETRIES = 10000;
	private static final boolean TRY_NUMBER_EXCEDED = false;
	private String firstName;
    private String lastName;
    //hack
    private Date birthDate;
    private String profile;
    private String seniority;
    @SuppressWarnings("rawtypes")
	private Map session;
    private String code;
    private List<Profile> profiles = new ArrayList<Profile>();
    private List<Seniority> seniorities = new ArrayList<Seniority>();
    @SuppressWarnings({ "rawtypes", "unchecked" })
	//GenericDaoImplementation profileDao = new GenericDaoImplementation(Profile.class);
    private ProfileDAO profileDao;
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private SeniorityDAO seniorityDao;
    GenericDaoImplementation genericDao = new GenericDaoImplementation(TestModel.class);
    //@SuppressWarnings({ "rawtypes", "unchecked" })
	//GenericDaoImplementation applicantDao = new GenericDaoImplementation(Applicant.class);
    
    private ApplicantDAO applicantDao;

	private TestModel testModelRetreived;
    
    
    SimpleDateFormat df;
    String nowDateTimeString;
    Date nowDateTime;
    
    private Date testDate;
    private String fecha;
    private String horario;
    
    @SuppressWarnings("unchecked")
	@Override
    public String execute() throws Exception {
    	
    	//Obtencion de la fecha-hora actual y presisa de este instante.
    	df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    	df.setTimeZone(TimeZone.getTimeZone("GMT-3:00"));

    	nowDateTimeString = df.format(new Date());
    	nowDateTime = new Date(nowDateTimeString);   	
	
        if (!createApplicant()) {
        	session.put("error", "Sorry, the applicant you are trying to create seems to already exist in our database, please try again...");
            return ERROR;
        }
        session.put("sucess", "Applicant  " + firstName + " " + lastName + "  Created Ok!");
        return SUCCESS;
    }

    public boolean existApplicantCode(String code) {
    	if(!((List<Applicant>)applicantDao.getApplicantsForCode(code)).isEmpty()) {
    		return true;
    	}
    return false;
    }
    
    @SuppressWarnings("unchecked")
	public boolean createApplicant() {
    	logger.debug("public boolean createApplicant()");
        boolean result = false;
        Random generator = new Random();
        int randomNumber = generator.nextInt(1000) + 1;
        String cod = getFirstName() + "." + getLastName() + randomNumber;
        cod = cod.replace(" ", "_");
        int retries = 0;
        while (existApplicantCode(cod) && retries < NUMBER_OF_RETRIES){
        	retries++;
        	randomNumber = generator.nextInt(1000) + 1;
        	cod = getFirstName() + "." + getLastName() + randomNumber;
        	cod = cod.replace(" ", "_");
        }
        if (retries == NUMBER_OF_RETRIES)
        	return TRY_NUMBER_EXCEDED;
        
        profiles = profileDao.getAll();
        seniorities = seniorityDao.getAll();
        if (checkApplicantName() && checkProfilesAndSeniority() && checkTestDate()) {
            Applicant applicant = new Applicant();
            
            String profile_1 = profile.replaceAll(",", "").trim();
            Profile prof = (Profile) profileDao.read(Integer.parseInt(profile_1));
            
            String seniority_1 = seniority.replaceAll(",", "").trim();
            
            Seniority sen = (Seniority) seniorityDao.read(Integer.parseInt(seniority_1));
            applicant.setProfile(prof);
            applicant.setSeniority(sen);
            applicant.setFirstName(getFirstName());
            applicant.setLastName(getLastName());            
            applicant.setCode(cod);
            applicant.setBirthDate(nowDateTime);
            applicant.setCreateDate(nowDateTime);
            applicant.setTestDate(testDate);
            code = applicant.getCode();
            applicantDao.create(applicant);
            if (createTestForApplicant(applicant, prof, sen)) {                
                result = true;
            } else {
                applicantDao.delete(applicant);
            }
        }
        return result;
    }

    private boolean createTestForApplicant(Applicant applicant, Profile prof, Seniority sen) {
    	logger.debug("private boolean createTestForApplicant(Applicant applicant, Profile prof, Seniority sen)");
        boolean result = true;
        TestModel testMod = getTestModelForApplicant(prof, sen);
        if (testMod == null) {
            result = false;
        } else {
            CreateTestAction createTest = new CreateTestAction(testMod, applicant);
            try {
                if (!createTest.execute()) {
                    result = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return result;

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private TestModel getTestModelForApplicant(Profile prof, Seniority sen) {
    	logger.debug("private TestModel getTestModelForApplicant(Profile prof, Seniority sen)");
        TestModel testModel = null;
        List<TestModel> testModelsForUser = (List<TestModel>) genericDao.executeQuery("from TestModel where profile.id = " + prof.getId()
                + " and seniority.id = " + sen.getId());
        if (testModelsForUser.isEmpty()) {
            getSession().put("error", " There are no Test Models for " + prof.getDescription() + " " + sen.getDescription());
            return testModel;
        }
        logger.debug("testModel id retreivesd  " + testModelsForUser.get(testModelsForUser.size()-1).getId());
        logger.debug("testModelsForUser.get(testModelsForUser.size()-1)" + testModelsForUser.get(testModelsForUser.size()-1));
        logger.debug("testModelsForUser.size()" + testModelsForUser.size());
        return testModelsForUser.get(testModelsForUser.size()-1);
    }
    
    private boolean checkTestDate() {
    	if(this.fecha != null && this.horario != null && !this.horario.trim().equals("") && (fecha + " " + horario.substring(11, 16)).compareTo(nowDateTimeString) >= 0) {  		
    		testDate = new Date(fecha + " " + horario.substring(11, 16));
			return true;
    	}else{
    		testDate = nowDateTime;
    		return true;
    	}
    	
    }

    @SuppressWarnings("unchecked")
	private boolean checkApplicantName() {
        boolean result = true;
        if((getFirstName() == null || getFirstName().equals("")) || getLastName().equals("")){
        session.put("error", "FirstName or LastName invalid");
        result = false;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
	private boolean checkProfilesAndSeniority() {
        boolean result = true;
        if (getSeniority().equalsIgnoreCase("0") || getProfile().equalsIgnoreCase("0")) {
            getSession().put("error", "You need to select a valid Profile and Seniority to create The Applicant option");
            result = false;
        }
        return result;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @SuppressWarnings("rawtypes")
	public void setSession(Map session) {
        this.session = session;
    }

    @SuppressWarnings("rawtypes")
	public Map getSession() {
        return this.session;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public TestModel getTestModelRetreived() {
		return testModelRetreived;
	}

	public void setTestModelRetreived(TestModel testModelRetreived) {
		this.testModelRetreived = testModelRetreived;
	}

	public ProfileDAO getProfileDao() {
		return profileDao;
	}

	public void setProfileDao(ProfileDAO profileDao) {
		this.profileDao = profileDao;
	}

	public ApplicantDAO getApplicantDao() {
		return applicantDao;
	}

	public void setApplicantDao(ApplicantDAO applicantDao) {
		this.applicantDao = applicantDao;
	}

	public SeniorityDAO getSeniorityDao() {
		return seniorityDao;
	}

	public void setSeniorityDao(SeniorityDAO seniorityDao) {
		this.seniorityDao = seniorityDao;
	}
	
}
