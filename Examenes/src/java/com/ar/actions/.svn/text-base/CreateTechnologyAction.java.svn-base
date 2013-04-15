/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ar.actions;

import com.ar.mrm.dao.GenericDaoImplementation;
import com.ar.mrm.entities.Technology;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Santiago.Arias
 */
public class CreateTechnologyAction extends ActionSupport implements SessionAware {

	private Logger logger = Logger.getLogger(CreateTechnologyAction.class);
    private Map session;
    private String technologyName;
    GenericDaoImplementation technologyDao = new GenericDaoImplementation(Technology.class);

    @Override
    public String execute() throws Exception {
    	logger.debug("CreateTechnologyAction");
        if(!persistTechnology()) return ERROR;
        return SUCCESS;
    }

    private boolean persistTechnology() {
        boolean result = true;
        try {
            List<Technology> alltechs = technologyDao.getAll();
            Technology tech = new Technology();
            tech.setDescription(getTechnologyName());
            for (Technology techno : alltechs) {
                logger.debug(techno.getDescription() + " comparing with " +tech.getDescription());
                if(techno.getDescription().contains(tech.getDescription())){
                    getSession().put("error", "Technology already exists containing the name : " + tech.getDescription() +
                            ", please try another name");
                    logger.debug("ERROR - Technology already exists with that name ");
                    return false;
                }
            }
            technologyDao.create(tech);
            getSession().put("sucess", "Technology " + getTechnologyName() + " created!");
            logger.debug("Technology created.");
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
            getSession().put("error", "Error persisting the Technology");
            logger.error("Error persisting the Technology", e);
        }
        return result;
    }

    public void setSession(Map map) {
        this.session = map;
    }

    public Map getSession() {
        return this.session;
    }

    public String getTechnologyName() {
        return technologyName;
    }

    public void setTechnologyName(String technologyName) {
        this.technologyName = technologyName;
    }
}
