package com.ar.mrm.dao;

import org.hibernate.Session;

import com.ar.mrm.entities.Option;
import com.ar.mrm.entities.Question;

public class QuestionDAO {

    public void update(Question o, Session sess) {
        sess.update(o);
        
    }
    
    public Long create(Question o, Session sess) {
        Long pk =  (Long) sess.save(o);
        return pk;
    }
	public void delete(Question o, Session sess){
		sess.delete(o);
	}
    public Question read(Integer id, Session sess) {
    	Question t =  (Question) sess.get(Question.class, id);
        return t;
    }
	
}
