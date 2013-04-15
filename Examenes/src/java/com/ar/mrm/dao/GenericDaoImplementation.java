/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ar.mrm.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.ar.actions.HibernateUtil;
import com.ar.mrm.entities.Profile;
import com.ar.mrm.entities.Seniority;

/**
 *
 * @author Santiago.Arias
 */
public class GenericDaoImplementation<T, PK extends Serializable> implements GenericDao<T, PK> {
    //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Session session = HibernateUtil.getSessionFactory().openSession();

    private Logger logger = Logger.getLogger(GenericDaoImplementation.class);
    
    private Class<T> type;

    public GenericDaoImplementation(Class<T> type) {
        this.type = type;
    }

//    public GenericDaoImplementation() { }
    
    public PK create(T o) {
        Transaction tx = session.beginTransaction();
        PK pk =  (PK) getSession().save(o);
        tx.commit();
        return pk;
    }

    public T read(PK id) {
        Transaction tx = session.beginTransaction();
        T t =  (T) getSession().get(type, id);
        tx.commit();
        return t;
    }

    public void update(T o) {
        Transaction tx = session.beginTransaction();
        getSession().update(o);
        tx.commit();
        
    }
    
    public List<T> getAll(){
        Transaction tx = session.beginTransaction();
        logger.debug("FROM " + type.getName());
        Query q = session.createQuery("FROM " + type.getName());
        List<T> list = (List<T>) q.list();
        tx.commit();
        return list;
    }
    
    public List<T> getTestModelBySeniorityProfile(Seniority seniority,Profile profile){
        Transaction tx = session.beginTransaction();
        logger.debug("FROM " + type.getName());
        Criteria crit = session.createCriteria(type);
        
        crit.add(Restrictions.eq("seniority", seniority));
        crit.add(Restrictions.eq("profile", profile));
        
        List<T> list = (List<T>) crit.list();
        tx.commit();
        return list;
    }
    
    public List<T> getProfileByName(String profileName){
        Transaction tx = session.beginTransaction();
        logger.debug("FROM " + type.getName());
        Criteria crit = session.createCriteria(type);
        
        crit.add(Restrictions.eq("description", profileName));
        
        List<T> list = (List<T>) crit.list();
        tx.commit();
        return list;
    }
    
    public List<T> getApplicantsForName(String firstName,  String lastName, Date beginDate, Date endDate, String testTaked){
        Transaction tx = session.beginTransaction();
        
        Criteria crit = session.createCriteria(type);
        if (firstName!=null && !firstName.trim().equals(""))
        	crit.add(Restrictions.like("firstName", ("%"+firstName)+"%").ignoreCase());
        
        if (lastName!=null && !lastName.trim().equals("")) 
        	crit.add(Restrictions.like("lastName", ("%"+lastName)+"%").ignoreCase());
        
        if (beginDate!=null)
        	crit.add(Restrictions.ge("testDate", beginDate));
        
        if (endDate!=null)
        	crit.add(Restrictions.le("testDate", endDate));

        if (testTaked!=null) {
        	if(!testTaked.equalsIgnoreCase("all")) {
        		crit.add(Restrictions.eq("testTaked", Boolean.parseBoolean(testTaked)));
        	}
        }
        
        //Query q = session.createQuery("FROM "+type.getName()+" WHERE upper(firstName) LIKE '%"+firstName.toUpperCase()+"%'");
        //List<T> list = (List<T>) q.list();
        
        List<T> list = (List<T>) crit.list();
        tx.commit();
        return list;
    }
    
    public List<T> getApplicantsForCode(String code){
        Transaction tx = session.beginTransaction();
        
        Criteria crit = session.createCriteria(type);
        if (code!=null && !code.trim().equals(""))
        	crit.add(Restrictions.like("code", ("%"+code)+"%").ignoreCase());
        
        List<T> list = (List<T>) crit.list();
        tx.commit();
        return list;
    }

    public List<T> executeQuery(String query) {
        Transaction tx = session.beginTransaction();
        logger.debug("FROM " + type.getName());
        Query q = session.createQuery(query);
        List<T> list = (List<T>) q.list();
        tx.commit();
        return list;
    }


//    public Date getDate() {
//    	Transaction tx = session.beginTransaction();
//    	Query q = session.createSQLQuery("select sysdate()");
//    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
//    	
//    	try {
//			return df.parse(df.format((Timestamp)q.uniqueResult()));
//	
//    	} catch (HibernateException e) {
//			e.printStackTrace();
//		    return null;
//    	} catch (ParseException e) {
//			e.printStackTrace();
//		    return null;
//    	}
//    }

    public void delete(T o) {
        Transaction tx = session.beginTransaction();
        getSession().delete(o);
        tx.commit();
        
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }


    // Not showing implementations of getSession() and setSessionFactory()
}
