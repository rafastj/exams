package com.ar.actions;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ar.mrm.dao.GenericDaoImplementation;
import com.ar.mrm.dao.TestModelDao;
import com.ar.mrm.dao.TestModelQuestionQtyDAO;
import com.ar.mrm.entities.Seniority;
import com.ar.mrm.entities.Technology;
import com.ar.mrm.entities.TestModel;
import com.ar.mrm.entities.TestModelQuestionqty;
import com.opensymphony.xwork2.ActionSupport;

public class CreateTestModelQuestionQtyAction extends ActionSupport  implements SessionAware {
    private String testModel;
    private Map session ;
    TestModelDao testModelDao = new TestModelDao();
    private TestModelQuestionQtyDAO testModelQuestionqtyDAO = new TestModelQuestionQtyDAO();
	private GenericDaoImplementation seniorityDao = new GenericDaoImplementation(Seniority.class);
	private GenericDaoImplementation technologyDao = new GenericDaoImplementation(Technology.class);
	private String technologyrow;
	private String seniorityrow;
	private String qty;
	private Logger logger = Logger.getLogger(CreateTestModelQuestionQtyAction.class);
    
	public CreateTestModelQuestionQtyAction() {
		super();
	}
    @Override
    public String execute() throws Exception {
    	logger.debug("CreateTestModelQuestionQtyAction");
    	
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
    	
        hibernateSession.beginTransaction();
        
    	Integer idTestModel = Integer.parseInt(this.testModel);
        TestModel testModel = testModelDao.getTest(idTestModel,hibernateSession);
    	
        TestModelQuestionqty testModelQuestionqty = new TestModelQuestionqty();
    	
    	Integer idTechnology = Integer.parseInt(this.technologyrow);
    	Integer idSeniority = Integer.parseInt(this.seniorityrow);
        
    	Integer amoutOfQuestions = Integer.parseInt(this.qty);
        
    	Technology t = (Technology) technologyDao.read(idTechnology);
    	Seniority s = (Seniority)seniorityDao.read(idSeniority);
    	testModelQuestionqty.setTechnology(t);
    	testModelQuestionqty.setSeniority(s);
    	testModelQuestionqty.setQtyQuestions(amoutOfQuestions);
    	testModelQuestionqty.setTestModel(testModel);
        
    	try{
    		testModelQuestionqtyDAO.create(testModelQuestionqty,hibernateSession);
    		
    	}catch (HibernateException e) {
    		logger.error("ERROR - Hibernate Exception while creating a technology", e);
		}catch (Exception e) {
    		logger.error("ERROR - While creating a technology", e);
		}
    	
    	testModel.getTestModelQuestionqties().add(testModelQuestionqty);

    	try{
    		testModelDao.update(testModel,hibernateSession);
    	}catch (HibernateException e) {
    		logger.error("ERROR - Hibernate Exception while updating a test model", e);
		}catch (Exception e) {
    		logger.error("ERROR - While updating a test model", e);
		}
    	
		hibernateSession.getTransaction().commit();
		
    	return "redirect";
    }
	
	public String getTestModel() {
		return testModel;
	}
	public void setTestModel(String testModel) {
		this.testModel = testModel;
	}
	public Map getSession() {
		return session;
	}
	public void setSession(Map session) {
		this.session = session;
	}
	public TestModelDao getTestModelDao() {
		return testModelDao;
	}
	public void setTestModelDao(TestModelDao testModelDao) {
		this.testModelDao = testModelDao;
	}
	public TestModelQuestionQtyDAO getTestModelQuestionqtyDAO() {
		return testModelQuestionqtyDAO;
	}
	public void setTestModelQuestionqtyDAO(
			TestModelQuestionQtyDAO testModelQuestionqtyDAO) {
		this.testModelQuestionqtyDAO = testModelQuestionqtyDAO;
	}
	public GenericDaoImplementation getSeniorityDao() {
		return seniorityDao;
	}
	public void setSeniorityDao(GenericDaoImplementation seniorityDao) {
		this.seniorityDao = seniorityDao;
	}
	public GenericDaoImplementation getTechnologyDao() {
		return technologyDao;
	}
	public void setTechnologyDao(GenericDaoImplementation technologyDao) {
		this.technologyDao = technologyDao;
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
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
    

}
