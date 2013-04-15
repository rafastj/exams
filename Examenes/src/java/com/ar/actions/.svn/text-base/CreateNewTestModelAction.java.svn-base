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

import com.ar.mrm.dao.ProfileDAO;
import com.ar.mrm.dao.SeniorityDAO;
import com.ar.mrm.dao.TechnologyDAO;
import com.ar.mrm.dao.TestModelDao;
import com.ar.mrm.dao.TestModelQuestionQtyDAO;
import com.ar.mrm.entities.Profile;
import com.ar.mrm.entities.Seniority;
import com.ar.mrm.entities.Technology;
import com.ar.mrm.entities.TestModel;
import com.ar.mrm.entities.TestModelQuestionqty;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author Santiago.Arias
 */
public class CreateNewTestModelAction extends ActionSupport implements SessionAware {

	private final Logger logger = Logger.getLogger(getClass());
	private String profile;
	private String seniority;
	private Map session;
	//private GenericDaoImplementation seniorityDao = new GenericDaoImplementation(Seniority.class);
	
	private SeniorityDAO seniorityDao;
	//private GenericDaoImplementation technologyDao = new GenericDaoImplementation(Technology.class);
	private TechnologyDAO technologyDao;
	//private GenericDaoImplementation testModelQuestionqtyDao = new GenericDaoImplementation(TestModelQuestionqty.class);
	private TestModelQuestionQtyDAO testModelQuestionQtyDao;
	//private GenericDaoImplementation profileDao = new GenericDaoImplementation(Profile.class);
	private ProfileDAO profileDao;
	//private GenericDaoImplementation testModelDao = new GenericDaoImplementation(TestModel.class);
	private TestModelDao testModelDao;

	private List<Technology> technologiesrow = new ArrayList<Technology>();
	private List<Seniority> senioritiesrow = new ArrayList<Seniority>();

	private List<Profile> profiles = new ArrayList<Profile>();
	private List<Seniority> seniorities = new ArrayList<Seniority>();

	private String technologyrow;
	private String seniorityrow;

	private String qty;
	
	private String minutesToFinish;
	
	private boolean bloqueado;

	private List<TestModelQuestionqty> actualtechno = new ArrayList<TestModelQuestionqty>();

	public String execute() throws Exception {

		logger.debug("CreateNewTestModelAction");
		List<TestModel> testModelProf = new ArrayList<TestModel>();

		technologiesrow = technologyDao.getAll();
		senioritiesrow = seniorityDao.getAll();
		profiles = profileDao.getAll();
		seniorities = seniorityDao.getAll();

		bloqueado = true;

		if (!validateTestModelDoesNotExist()) {
			
			getSession().put("error", "A Test Model for that Profile and Seniority already exists.");
			logger.error("A Test Model for that Profile and Seniority already exists.");
			return SUCCESS;
			
		}

		if (!createTestModel()) {
			logger.error("no se pudo crear el test model");
			return ERROR;
		}

		return SUCCESS;
	}
	
	private boolean existeTecnologia() {
		boolean existe = false;
		
    	List<TestModel> tm = testModelDao.executeQuery("FROM TestModel as tm " +
                "WHERE tm.seniority.id = " + seniority + "AND tm.profile.id = " + profile + "");
    	
    	List<TestModelQuestionqty> tec = testModelQuestionQtyDao.executeQuery("FROM TestModelQuestionqty as tmc WHERE tmc.testModel.id = "
				+ tm.get(0).getId().toString() + "AND technology.id = " + technologyrow + "AND seniority.id = " + seniorityrow + "");
		
    	if(!(tec.isEmpty())) {
    		existe = true;
			logger.warn("la tecnologia ya existe");
    	}
    	
		return existe;
		
	}

	private Boolean validateTestModelDoesNotExist() {

		Profile profileObject = (Profile) profileDao.read(Integer.parseInt(profile));

		Seniority seniorityObject = (Seniority) seniorityDao.read(Integer.parseInt(seniority));

		List<TestModel> result = testModelDao.getTestModelBySeniorityProfile(seniorityObject, profileObject);

		return result.isEmpty();
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

	private boolean createTestModel() {
		boolean result = true;

		try {
			Integer srtyId = Integer.parseInt(getSeniority());
			Integer profId = Integer.parseInt(getProfile());
			Profile prof = (Profile) profileDao.read(profId);
			Seniority srity = (Seniority) seniorityDao.read(srtyId);
			TestModel testModel = new TestModel();
			testModel.setSeniority(srity);
			testModel.setProfile(prof);
			testModel.setMinutesToFinish(Integer.parseInt(minutesToFinish));
			testModelDao.create(testModel);

			//agregarTecnologia();

			getSession().put(
					"sucess",
					"A new Model for " + prof.getDescription() + " - "
							+ srity.getDescription() + " was created!");
			logger.debug("sucess, A new Model for "+ prof.getDescription() +" - "+ srity.getDescription() + " was created!");
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			getSession().put("error", "invalid parameters");
			logger.error("invalid parameters in Create New Test Model");
			result = false;
		} catch (Exception e) {
			e.printStackTrace();
			getSession().put("error",
					"an error ocurred and could not process the request");
			logger.error("an error ocurred and could not process the request");
			result = false;
		}

		return result;

	}

	public void agregarTecnologia() {
 
    	TestModelQuestionqty testModelQuestionqty = new TestModelQuestionqty();
    	
    	Technology t = (Technology) technologyDao.read(Integer.parseInt(technologyrow));
    	Seniority s = (Seniority)seniorityDao.read(Integer.parseInt(seniorityrow));
    	testModelQuestionqty.setTechnology(t);
    	testModelQuestionqty.setSeniority(s);
    	testModelQuestionqty.setQtyQuestions(Integer.parseInt(qty));
    	
    	List<TestModel> tm = testModelDao.executeQuery("FROM TestModel as tm " +
                "WHERE tm.seniority.id = " + seniority + "AND tm.profile.id = " + profile + "");
    	
    	//relaciono las nuevas preguntas con el testModel existente.
    	testModelQuestionqty.setTestModel(tm.get(0));
    	
    	
		if(minutesToFinish != null){
			try{
				if(tm.get(0)!=null){
					tm.get(0).setMinutesToFinish(new Integer(this.minutesToFinish));
				}
			}catch(NumberFormatException e){
				getSession().put("error", "invalid parameters");
				logger.error("invalid parameters");
			}
			
			testModelDao.update(tm.get(0));
		}
    	
    	
		testModelQuestionQtyDao.create(testModelQuestionqty);
    	actualtechno.add(testModelQuestionqty);
    	
    	getSession().put("sucess", "A new Technology was inserted!");
    	logger.debug("A new Technology was inserted!");
    }



	public SeniorityDAO getSeniorityDao() {
		return seniorityDao;
	}



	public TestModelQuestionQtyDAO getTestModelQuestionQtyDao() {
		return testModelQuestionQtyDao;
	}

	public void setTestModelQuestionQtyDao(
			TestModelQuestionQtyDAO testModelQuestionQtyDao) {
		this.testModelQuestionQtyDao = testModelQuestionQtyDao;
	}

	public ProfileDAO getProfileDao() {
		return profileDao;
	}

	public void setProfileDao(ProfileDAO profileDao) {
		this.profileDao = profileDao;
	}

	public TechnologyDAO getTechnologyDao() {
		return technologyDao;
	}

	public void setTechnologyDao(TechnologyDAO technologyDao) {
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

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public TestModelDao getTestModelDao() {
		return testModelDao;
	}

	public void setTestModelDao(TestModelDao testModelDao) {
		this.testModelDao = testModelDao;
	}

	public List<TestModelQuestionqty> getActualtechno() {
		return actualtechno;
	}

	public void setActualtechno(List<TestModelQuestionqty> actualtechno) {
		this.actualtechno = actualtechno;
	}

	public boolean getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public String getMinutesToFinish() {
		return minutesToFinish;
	}

	public void setMinutesToFinish(String minutesToFinish) {
		this.minutesToFinish = minutesToFinish;
	}

	public void setSeniorityDao(SeniorityDAO seniorityDao) {
		this.seniorityDao = seniorityDao;
	}


}
