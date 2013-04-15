package com.ar.mrm.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ar.mrm.entities.TestModel;


public class TestModelDao extends GenericDaoImplementation<TestModel, Serializable>{

	public TestModelDao() {
		super(TestModel.class);
	}
	
    public TestModel getTest(Integer id,Session sess){
    	//Query q = session.createQuery("FROM TestModel as tm WHERE tm.id = "+id);
    	Query q = sess.createQuery("FROM TestModel as tm WHERE tm.id = "+id);
    	
    	List list = q.list();
    	return (TestModel) list.get(0);
    }
    
    public void update(TestModel o, Session sess) {
        //Transaction tx = session.beginTransaction();
        sess.saveOrUpdate(o);
        //tx.commit();
        
    }
    
    public Long create(TestModel o,Session sess) {
        Transaction tx = sess.beginTransaction();
        Long pk =  (Long) sess.save(o);
        tx.commit();
        return pk;
    }
    
    
}
