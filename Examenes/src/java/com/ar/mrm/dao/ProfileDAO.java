package com.ar.mrm.dao;

import java.io.Serializable;

import com.ar.mrm.entities.Profile;

public class ProfileDAO extends GenericDaoImplementation<Profile, Serializable>{
	public ProfileDAO() {
		super(Profile.class);
	}

}
