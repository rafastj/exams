package com.ar.actions;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ar.mrm.dao.OptionDAO;
import com.ar.mrm.dao.QuestionDAO;
import com.ar.mrm.entities.Option;
import com.ar.mrm.entities.Question;
import com.opensymphony.xwork2.ActionSupport;

public class EliminarQuestionAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 8120332311809501594L;
	private Map session ;
    private String id_q;
    private OptionDAO optionDao = new OptionDAO();
    private QuestionDAO questionDao = new QuestionDAO();
	private Logger logger = Logger.getLogger(EliminarQuestionAction.class);
    
    @Override
    public String execute() throws Exception {
    	logger.debug("EliminarQuestionAction");
    	
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	session.beginTransaction();
        Question question = null;
    	try{
    		Integer questionId = Integer.parseInt(id_q);
    		question = (Question) questionDao.read(questionId,session);
    	}catch (NumberFormatException e) {
			e.printStackTrace();
			logger.error("number format exception", e);
		}catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while reading question", e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("ERROR - While reading question", e);
			e.printStackTrace();
		}
    	
    	try{
    		for (Option o: question.getOptions()) {
    			optionDao.delete(o,session);
    		}
    		
    	}catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while deleting an option", e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("ERROR - While deleting an option", e);
			e.printStackTrace();
		}
    
    	try{
    		questionDao.delete(question,session);
    	}catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while deleting a question", e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("ERROR - While deleting a question", e);
			e.printStackTrace();
		}
    	session.getTransaction().commit();
    	return "redirect";
    }
    
    
	public void setSession(Map sess) {
		this.session = sess;
	}

	public String getId_q() {
		return id_q;
	}

	public void setId_q(String id_q) {
		this.id_q = id_q;
	}

	
}
