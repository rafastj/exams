package com.ar.actions;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;

import com.ar.mrm.dao.TestModelDao;
import com.ar.mrm.entities.TestModel;
import com.opensymphony.xwork2.ActionSupport;

public class EditTestTimeAction extends ActionSupport implements SessionAware {
    private Integer idTestModel;
    private String minutesToFinish;
    private Map session ;
	private Logger logger = Logger.getLogger(EditTestTimeAction.class);

    
    public String execute() throws Exception {
    	logger.debug("EditTestTimeAction");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
    	
	        TestModelDao testModelDao = new TestModelDao();
	        TestModel testModel = testModelDao.getTest(idTestModel,session);
	        try{
	        	testModel.setMinutesToFinish(Integer.parseInt(minutesToFinish));
	        	
	        }catch (NumberFormatException e) {
	        	logger.error("minitus to finish is not a number", e);
				e.printStackTrace();
			}
	        
        session.getTransaction().commit();
        
        return "redirect";
    }
    
	public EditTestTimeAction() {
		super();
	}

	public Integer getIdTestModel() {
		return idTestModel;
	}

	public void setIdTestModel(Integer idTestModel) {
		this.idTestModel = idTestModel;
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public String getMinutesToFinish() {
		return minutesToFinish;
	}

	public void setMinutesToFinish(String minutesToFinish) {
		this.minutesToFinish = minutesToFinish;
	}    

    
}
