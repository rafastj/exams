package com.ar.actions;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ar.mrm.dao.OptionDAO;
import com.ar.mrm.entities.Option;
import com.opensymphony.xwork2.ActionSupport;

public class EliminarOpcionAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	private Map session ;
    private OptionDAO optionDao = new OptionDAO();
    private String id_q;
    private String id_option;
	private Logger logger = Logger.getLogger(EliminarOpcionAction.class);
    
    
    
    public String execute() throws Exception {
    	logger.debug("EliminarOpcionAction");
    	
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	Option option= null;
    	try{
    		Integer optionId = Integer.parseInt(id_option);
    		option = (Option) optionDao.read(optionId,session);
    	}catch (NumberFormatException e) {
			e.printStackTrace();
			logger.error("number format exception", e);
		}catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while reading option", e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("ERROR - While reading option", e);
			e.printStackTrace();
		}
   	
    	return "redirect";
    }

	public void setSession(Map sess) {
		this.session = sess;
		
	}
    
    
}
