package com.ar.mrm.dao;

import org.hibernate.Session;

import com.ar.mrm.entities.Option;

public class OptionDAO {

    public void update(Option o, Session sess) {
        sess.update(o);
    }
    
    public Long create(Option o, Session sess) {
        Long pk =  (Long) sess.save(o);
        return pk;
    }
	public void delete(Option o, Session sess){
		sess.delete(o);
	}
    public Option read(Integer id, Session sess) {
    	Option t =  (Option) sess.get(Option.class, id);
        return t;
    }
	
	
}
