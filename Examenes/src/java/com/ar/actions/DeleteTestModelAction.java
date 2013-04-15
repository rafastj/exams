package com.ar.actions;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ar.mrm.dao.TestModelDao;
import com.ar.mrm.dao.TestModelQuestionQtyDAO;
import com.ar.mrm.entities.TestModel;
import com.ar.mrm.entities.TestModelQuestionqty;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteTestModelAction extends ActionSupport  implements SessionAware {
	
    private String testModel;
	private Integer idTestModel;
    private Integer idQuestion;
    //private List<TestModel> testModels;
    private Map session ;    
	private Logger logger = Logger.getLogger(DeleteTestModelAction.class);

    @Override
    public String execute() throws Exception {
        logger.debug("DeleteTestModelAction");
    	
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
    	
        TestModelDao testModelDao = new TestModelDao();
        TestModelQuestionQtyDAO testModelQuestionqtyDAO = new TestModelQuestionQtyDAO();
        
        //cargo un test model por su id
        TestModel testModel = testModelDao.getTest(idTestModel,session);
        
        //obtengo la coleccion de preguntas
        Set<TestModelQuestionqty> set =  testModel.getTestModelQuestionqties();
        
        TestModelQuestionqty questionToRemove = null;
        for (TestModelQuestionqty testModelQuestionqty : set) {
			if(testModelQuestionqty.getId().equals(idQuestion)){
				//seteo las preguntas a remover
				questionToRemove = testModelQuestionqty;
				questionToRemove.setTestModel(null);
			}
		}
        try{
        	//borro la pregunta de la base
        	testModelQuestionqtyDAO.delete(questionToRemove,session);
        	
        }catch (HibernateException e) {
    		logger.error("ERROR - Hibernate Exception while deleting a technology", e);
        	
		}catch (Exception e) {
    		logger.error("ERROR - While deleting a technology", e);
			
		}
        
        //remuevo la pregunta de la coleccion en testModel
        testModel.getTestModelQuestionqties().remove(questionToRemove);
        try{
        	//update de testModel
        	testModelDao.update(testModel,session);
        	
    	}catch (HibernateException e) {
    		logger.error("ERROR - Hibernate Exception while updating a test model", e);
		}catch (Exception e) {
    		logger.error("ERROR - While updating a test model", e);
		}
        
        session.getTransaction().commit();
        
        return "redirect";
        //return SUCCESS;
    }

	public Integer getIdTestModel() {
		return idTestModel;
	}

	public void setIdTestModel(Integer idTestModel) {
		this.idTestModel = idTestModel;
	}

	public Integer getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(Integer idQuestion) {
		this.idQuestion = idQuestion;
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public String getTestModel() {
		return testModel;
	}

	public void setTestModel(String testModel) {
		this.testModel = testModel;
	}
	
}
