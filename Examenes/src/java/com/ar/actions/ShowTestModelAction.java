/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ar.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;

import com.ar.mrm.comparator.SenioritiesComparator;
import com.ar.mrm.comparator.TechnollogiesComparator;
import com.ar.mrm.comparator.TestModelQuestionQtyComparator;
import com.ar.mrm.dao.GenericDaoImplementation;
import com.ar.mrm.dto.TestModelQuestionQtyDTO;
import com.ar.mrm.entities.Profile;
import com.ar.mrm.entities.Question;
import com.ar.mrm.entities.Seniority;
import com.ar.mrm.entities.Technology;
import com.ar.mrm.entities.TestModel;
import com.ar.mrm.entities.TestModelQuestionqty;
import com.ar.mrm.exception.TechnologyAlreadyExistsException;
import com.ar.mrm.exception.TestModelWithoutTechnollogiesException;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author Santiago.Arias
 */
public class ShowTestModelAction extends ActionSupport  implements SessionAware {

	private Logger logger = Logger.getLogger(ShowTestModelAction.class);
    private String testModel;
    //private Integer idTestModel;
    private TestModel testModelRetreived;
    private List<TestModel> testModels = new ArrayList<TestModel>();
    private Boolean editTestModel = false;
    private Map session ;    
	private Map<Integer, TestModelQuestionQtyDTO> newQuestionQtyDTOMap = new HashMap<Integer, TestModelQuestionQtyDTO>();

    
    private GenericDaoImplementation seniorityDao = new GenericDaoImplementation(Seniority.class);
    private GenericDaoImplementation profileDao = new GenericDaoImplementation(Profile.class);
    private GenericDaoImplementation technologyDao = new GenericDaoImplementation(Technology.class);
    private GenericDaoImplementation questionsDao = new GenericDaoImplementation(Question.class);
    GenericDaoImplementation testModelDao = new GenericDaoImplementation(TestModel.class);

    
	private List<Technology> technologiesrow = new ArrayList<Technology>();
	private List<Seniority> senioritiesrow = new ArrayList<Seniority>();

	private List<Profile> profiles = new ArrayList<Profile>();
	private List<Seniority> seniorities = new ArrayList<Seniority>();

	private String technologyrow;
	private String seniorityrow;
	
    private String editMode;
    
	private List<TestModelQuestionqty> questionList = new ArrayList<TestModelQuestionqty>(); 

    public List<TestModelQuestionqty> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<TestModelQuestionqty> questionList) {
		this.questionList = questionList;
	}
	private Map testModelQuestionQtyDTOMap;
    
    public ShowTestModelAction() {
    }

    @Override
    public String execute() throws Exception {
    	
    	if(editMode.equalsIgnoreCase("true")){
    		editTestModel = true;
    	}
         
     	//technologiesrow = technologyDao.getAll();
     	//senioritiesrow = seniorityDao.getAll();
     	//profiles = profileDao.getAll();
     	//seniorities = seniorityDao.getAll();

     	
    	try {
    		technologiesrow = technologyDao.getAll();
    		
    		Collections.sort(technologiesrow, new TechnollogiesComparator());
		} catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while reading technologies", e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("ERROR - While reading a technologies", e);
			e.printStackTrace();
		}
    	try{
    		senioritiesrow = seniorityDao.getAll();
    		
    		Collections.sort(senioritiesrow, new SenioritiesComparator());
    		
    	}catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while reading seniorities", e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("ERROR - While reading a seniorities", e);
			e.printStackTrace();
		}
    	try{
    		profiles = profileDao.getAll();
    		
    	}catch (TestModelWithoutTechnollogiesException e) {
			logger.error("no hay tecnologias en el test model", e);
			e.printStackTrace();
		}catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while reading profiles", e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("ERROR - While reading a profiles", e);
			e.printStackTrace();
		}
    	seniorities = senioritiesrow;
     	
     	
     	
     	
        testModels = testModels = testModelDao.executeQuery(
                "FROM TestModel as tm " +
                "WHERE tm.id = (SELECT MAX(f.id) FROM TestModel AS f WHERE f.seniority.id = tm.seniority.id AND f.profile.id = tm.profile.id)"
                );
        retrieveTestModel();
        
        //List questionsRetreived = retreiveQuestionsFromDb(technologyrow, seniorityrow);
        
        return SUCCESS;
    }

    private boolean retrieveTestModel(){
        boolean result = true;
        try{
        testModelRetreived = (TestModel) testModelDao.read(Integer.parseInt(getTestModel()));
        
        createDTOMap(testModelRetreived);
        
        
        } catch (TestModelWithoutTechnollogiesException e) {
            e.printStackTrace();
            logger.error("invalid parameters", e);
            getSession().put("error", "there are no technollogies available.");
            result = false;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("invalid parameters", e);
            getSession().put("error", "invalid parameters");
            result = false;
        }
        return result;
    }

    private List<Question> retreiveQuestionsFromDb(String questionTechDescription, String questionTechDescription2) {
        List<Question> result = new ArrayList<Question>();
        result = questionsDao.executeQuery("FROM Question where technology.id = " + questionTechDescription + " AND seniority.id = " + questionTechDescription2 + "");
        return result;

    }
    
    
    private void createDTOMap(TestModel testModel) {
    	Set<TestModelQuestionqty> questions = testModel.getTestModelQuestionqties();
    	this.testModelQuestionQtyDTOMap = new HashMap<Integer, TestModelQuestionQtyDTO>();
    	TestModelQuestionQtyDTO questionDTO = null;
    	
    	List questionsRetreived = null;
    	//retreiveQuestionsFromDb(technologyrow, seniorityrow);
    	//List<TestModelQuestionQtyDTO> listaPreguntasAOrdenar = new ArrayList<TestModelQuestionQtyDTO>();
    	
    	
    	for (TestModelQuestionqty testModelQuestionqty : questions) {
    		questionDTO = new TestModelQuestionQtyDTO();
    		
    		if(this.editTestModel){
    			questionsRetreived = retreiveQuestionsFromDb(testModelQuestionqty.getTechnology().getId().toString(), testModelQuestionqty.getSeniority().getId().toString());
    			questionDTO.setTotalAmountOfQuestions(questionsRetreived.size());
    		}
    		
    		
			questionDTO.setId(testModelQuestionqty.getId());
			questionDTO.setSeniorityId(testModelQuestionqty.getSeniority().getId());
			questionDTO.setSeniorityDescription(testModelQuestionqty.getSeniority().getDescription());
			questionDTO.setTechnologyId(testModelQuestionqty.getTechnology().getId());
			questionDTO.setTechnologyDescription(testModelQuestionqty.getTechnology().getDescription());
			questionDTO.setQtyQuestions(testModelQuestionqty.getQtyQuestions().toString());
			
			questionList.add(testModelQuestionqty);
			testModelQuestionQtyDTOMap.put(testModelQuestionqty.getId(), questionDTO);
		}
    	
    	if((questionList==null)||(questionList.isEmpty())){
			throw new TestModelWithoutTechnollogiesException();
    	}
    	Collections.sort( questionList, new TestModelQuestionQtyComparator());
	}

	public String getTestModel() {
        return testModel;
    }

    public void setTestModel(String testModel) {
        this.testModel = testModel;
    }

    public void setSession(Map map) {
        this.session = map;
    }
    
    public Map getSession(){
        return this.session;
    }

    public TestModel getTestModelRetreived() {
        return testModelRetreived;
    }

    public void setTestModelRetreived(TestModel testModelRetreived) {
        this.testModelRetreived = testModelRetreived;
    }

    public List<TestModel> getTestModels() {
        return testModels;
    }

    public void setTestModels(List<TestModel> testModels) {
        this.testModels = testModels;
    }

	public Boolean getEditTestModel() {
		return editTestModel;
	}

	public void setEditTestModel(Boolean editTestModel) {
		this.editTestModel = editTestModel;
	}

	public List<Technology> getTechnologiesrow() {
		return technologiesrow;
	}

	public void setTechnologiesrow(List<Technology> technologiesrow) {
		this.technologiesrow = technologiesrow;
	}

	public List<Seniority> getSenioritiesrow() {
		return senioritiesrow;
	}

	public void setSenioritiesrow(List<Seniority> senioritiesrow) {
		this.senioritiesrow = senioritiesrow;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public List<Seniority> getSeniorities() {
		return seniorities;
	}

	public void setSeniorities(List<Seniority> seniorities) {
		this.seniorities = seniorities;
	}

	public String getTechnologyrow() {
		return technologyrow;
	}

	public void setTechnologyrow(String technologyrow) {
		this.technologyrow = technologyrow;
	}

	public String getSeniorityrow() {
		return seniorityrow;
	}

	public void setSeniorityrow(String seniorityrow) {
		this.seniorityrow = seniorityrow;
	}

	public Map getTestModelQuestionQtyDTOMap() {
		return testModelQuestionQtyDTOMap;
	}

	public void setTestModelQuestionQtyDTOMap(Map testModelQuestionQtyDTOMap) {
		this.testModelQuestionQtyDTOMap = testModelQuestionQtyDTOMap;
	}

	public Map<Integer, TestModelQuestionQtyDTO> getNewQuestionQtyDTOMap() {
		newQuestionQtyDTOMap = new HashMap<Integer, TestModelQuestionQtyDTO>();
		
		for (Integer i = 0; i < 10; i++) {
			newQuestionQtyDTOMap.put(i, new TestModelQuestionQtyDTO());
		}
		return newQuestionQtyDTOMap;
	}

	public void setNewQuestionQtyDTOMap(
			Map<Integer, TestModelQuestionQtyDTO> newQuestionQtyDTOMap) {
		this.newQuestionQtyDTOMap = newQuestionQtyDTOMap;
	}

	public String getEditMode() {
		return editMode;
	}

	public void setEditMode(String editMode) {
		this.editMode = editMode;
	}


}
