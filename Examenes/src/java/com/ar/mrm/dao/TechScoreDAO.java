package com.ar.mrm.dao;

import java.io.Serializable;

import com.ar.mrm.entities.TechScore;

public class TechScoreDAO extends GenericDaoImplementation<TechScore, Serializable>{
	
	public TechScoreDAO() {
		super(TechScore.class);
	}

}
