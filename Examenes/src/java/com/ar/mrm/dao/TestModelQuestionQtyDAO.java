package com.ar.mrm.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ar.mrm.entities.TestModelQuestionqty;

public class TestModelQuestionQtyDAO extends GenericDaoImplementation<TestModelQuestionqty, Serializable>{
    //Session session = HibernateUtil.getSessionFactory().openSession();
	public TestModelQuestionQtyDAO() {
		super(TestModelQuestionqty.class);
	}

	
    public TestModelQuestionqty getTest(Integer id){
    	Query q = session.createQuery("FROM TestModelQuestionqty as tmq WHERE tmq.id = "+id);
    	List list = q.list();
    	return (TestModelQuestionqty) list.get(0);
    }
    
    public void update(TestModelQuestionqty o) {
        Transaction tx = session.beginTransaction();
        session.update(o);
        tx.commit();
        
    }
    
    public Integer create(TestModelQuestionqty o,Session sess) {
    	Integer pk =  (Integer) sess.save(o);
        return pk;
    }
    
    public Long create(TestModelQuestionqty o) {
        Transaction tx = session.beginTransaction();
        Long pk =  (Long) session.save(o);
        tx.commit();
        return pk;
    }
    
    public void delete(TestModelQuestionqty o,Session sess) {
    	sess.delete(o);
        
    }

}
