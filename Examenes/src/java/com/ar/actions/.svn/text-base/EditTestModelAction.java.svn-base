package com.ar.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ar.mrm.dao.GenericDaoImplementation;
import com.ar.mrm.dao.TestModelDao;
import com.ar.mrm.dao.TestModelQuestionQtyDAO;
import com.ar.mrm.dto.TestModelQuestionQtyDTO;
import com.ar.mrm.entities.Seniority;
import com.ar.mrm.entities.Technology;
import com.ar.mrm.entities.TestModel;
import com.ar.mrm.entities.TestModelQuestionqty;
import com.ar.mrm.exception.QuantityFieldEmptyException;
import com.ar.mrm.exception.TechnologyAlreadyExistsException;
import com.ar.mrm.exception.TotalAmountOfQuestionsException;
import com.opensymphony.xwork2.ActionSupport;

public class EditTestModelAction extends ActionSupport  implements SessionAware {
	private static final long serialVersionUID = 5691325644245470665L;

	private Logger logger = Logger.getLogger(EditTestModelAction.class);
	private Integer idTestModel;
    private Integer idQuestion;
    private List<TestModel> testModels;
    private String testModel;
    private Map session ;    
    private String minutesToFinish;
    private Boolean editTestModel = true;
    private Boolean newTechnology = false;    
    private Map<Integer, TestModelQuestionQtyDTO> testModelQuestionQtyDTOMap = new HashMap<Integer, TestModelQuestionQtyDTO>();
	private Map<Integer, TestModelQuestionQtyDTO> newQuestionQtyDTOMap = new HashMap<Integer, TestModelQuestionQtyDTO>();
	private GenericDaoImplementation seniorityDao = new GenericDaoImplementation(Seniority.class);
	private GenericDaoImplementation technologyDao = new GenericDaoImplementation(Technology.class);
	private List<TestModelQuestionqty> tecnologiasQuePoseeElTestModel = new ArrayList<TestModelQuestionqty>();
	private List<TestModelQuestionqty> nuevasTecnologiasACrearEnElTestModel;
	private String newTechnologyId;
	private String newSecurityId;
	private String newQty;
    private String idsChanged;

	public EditTestModelAction() {
		super();
		
		for (int i = 0; i < 50; i++) {
			newQuestionQtyDTOMap.put(i, new TestModelQuestionQtyDTO());
		}
		
	}

	@Override
	public void validate() {
		super.validate();
	}
	
    @Override
    public String execute() throws Exception {

    	try{
    		logger.debug("EditTestModelAction");
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
        	
            
            /**
             * 
             * aca tendria que controllar que el mapa de edicion que me llega de la pantalla no repita ningun id
             * 
             * una vez que eso sucede, controllar que el mapa de nuevos no tenga ninguno que ya este en el mapa de modificados, 
             * 
             * recordar que el mapa de modificados es una copia de la collection que tiene el test model entonces 
             * 
             * es como comprar con el testmodel.
             * 
             */
            
            checkTechnologiesRepetition();
            
            
            TestModelDao testModelDao = new TestModelDao();
            TestModelQuestionQtyDAO testModelQuestionqtyDAO = new TestModelQuestionQtyDAO();
            
            TestModel testModel = testModelDao.getTest(idTestModel,session);
        
            tecnologiasQuePoseeElTestModel.addAll(testModel.getTestModelQuestionqties());
            
            //edito los minutos para terminar el examen
            Integer minutesToFin = Integer.parseInt(minutesToFinish);
            if((this.minutesToFinish!=null)&&(minutesToFin!=null)){
            	testModel.setMinutesToFinish(minutesToFin);
            }else{
            	logger.error("Runtime Exception - minutes to finish es menor que cero");
            	throw new RuntimeException("minutes to finish es menor que cero");
            }
            
            this.testModel = idTestModel.toString();
            Set<TestModelQuestionqty> set =  testModel.getTestModelQuestionqties();
            
            TestModelQuestionqty questionToRemove = null;
            TestModelQuestionqty testModelQuestionqty = null;
            
            List<TestModelQuestionqty> toDeleteList = new ArrayList<TestModelQuestionqty>();
            List<TestModelQuestionqty> toAddList = new ArrayList<TestModelQuestionqty>();
            
            Technology t = null;
            Seniority s = null;
            
            Set<Integer> keys = testModelQuestionQtyDTOMap.keySet();
            
            for (Integer id : keys) {
            	TestModelQuestionqty result = findTestModelQuestionQty(set,id);
            	
            	TestModelQuestionQtyDTO tmqDTO = testModelQuestionQtyDTOMap.get(result.getId());
            	
            	testModelQuestionqty = new TestModelQuestionqty();

            	try{
            		t = (Technology) technologyDao.read(tmqDTO.getTechnologyId());
            		
            	}catch (HibernateException e) {
            		logger.error("ERROR - Hibernate Exception while reading a technology", e);
        		}catch (Exception e) {
            		logger.error("ERROR - While reading a technology", e);
        		}
            	
            	try{
            		s = (Seniority)seniorityDao.read(tmqDTO.getSeniorityId());
            		
            	}catch (HibernateException e) {
            		logger.error("ERROR - Hibernate Exception while reading a seniority", e);
        		}catch (Exception e) {
            		logger.error("ERROR - While reading a seniority", e);
        		}
            	
            	testModelQuestionqty.setTechnology(t);
            	testModelQuestionqty.setSeniority(s);
            	testModelQuestionqty.setQtyQuestions(new Integer(tmqDTO.getQtyQuestions()));
            	testModelQuestionqty.setTestModel(testModel);
            	
            	toAddList.add(testModelQuestionqty);        	
            	
			}
            
            // borro los testModelQuestionQty viejos de la base
            try{
            	for (TestModelQuestionqty tmq : set) {
            		tmq.setTestModel(null);
            		testModelQuestionqtyDAO.delete(tmq,session);
            	}
            }catch (HibernateException e) {
        		logger.error("ERROR - Hibernate Exception while deleting a technology", e);
            	
    		}catch (Exception e) {
        		logger.error("ERROR - While deleting a technology", e);
    			
    		}
            
            try{
            	testModelDao.update(testModel,session);
        	}catch (HibernateException e) {
        		logger.error("ERROR - Hibernate Exception while updating a test model", e);
    		}catch (Exception e) {
        		logger.error("ERROR - While updating a test model", e);
    		}
            
            
            // tengo que crear los testModelQuestionQty nuevos en la base antes de setearselos al test model
            for (TestModelQuestionqty testModelQuestionqty2 : toAddList) {
            	testModelQuestionqtyDAO.create(testModelQuestionqty2,session);
            	testModel.getTestModelQuestionqties().add(testModelQuestionqty2);
    		}
            
            if(newTechnology){
            	
            	Set<Integer> keySet = newQuestionQtyDTOMap.keySet();
            	
            	for (Integer id : keySet) {
            		TestModelQuestionQtyDTO dto =  newQuestionQtyDTOMap.get(id);
            		
            		if(dto.getTechnologyId()!=null){
            			
            			if(dto.getQtyQuestions()==null){
            				throw new QuantityFieldEmptyException();
            			}
            			
            			TestModelQuestionqty tmq = new TestModelQuestionqty();
            			
            			try{
            				
            				t = (Technology) technologyDao.read(dto.getTechnologyId());
                    	}catch (HibernateException e) {
                    		logger.error("ERROR - Hibernate Exception while reading a technology", e);
                		}catch (Exception e) {
                    		logger.error("ERROR - While reading a technology", e);
                		}
            			
            			try{
            				s = (Seniority)seniorityDao.read(dto.getSeniorityId());
            				
                    	}catch (HibernateException e) {
                    		logger.error("ERROR - Hibernate Exception while reading a seniority", e);
                		}catch (Exception e) {
                    		logger.error("ERROR - While reading a seniority", e);
                		}
            			tmq.setTechnology(t);
            			tmq.setSeniority(s);
            			tmq.setQtyQuestions(new Integer(dto.getQtyQuestions()));
            			tmq.setTestModel(testModel);
            			
            			testModelQuestionqtyDAO.create(tmq,session);
            			testModel.getTestModelQuestionqties().add(tmq);
            		}
				}
            	
            	
            }
            
            
            try{
            	testModelDao.update(testModel,session);
            	
        	}catch (HibernateException e) {
        		logger.error("ERROR - Hibernate Exception while updating a test model", e);
    		}catch (Exception e) {
        		logger.error("ERROR - While updating a test model", e);
    		}
            
            session.getTransaction().commit();
            
            return "redirect";
            //return SUCCESS;
    		
    	}catch (QuantityFieldEmptyException e) {
    		logger.error("quantity field not completed.", e);
			getSession().put("error", "quantity field not completed.");
			//return SUCCESS;
			return "redirect";
			
		}catch (NumberFormatException e) {
    		logger.error("se ha ingresado texto en un campo numerico.", e);
			getSession().put("error", "se ha ingresado texto en un campo numerico");
			//return SUCCESS;
			return "redirect";
    		
		}catch (TechnologyAlreadyExistsException e) {
    		logger.error("Technology Already inserted!", e);
			getSession().put("error", "Technology Already inserted!");
			//return SUCCESS;
    		return "TECHNOLOGY_ALREADY_EXISTS_ERROR";
		}catch (TotalAmountOfQuestionsException e){
			logger.error("TotalAmountOfQuestionsException",e);
			getSession().put("error", "La cantidad de preguntas disponibles en la tecnologia es menor a la cantidad seteada.");
    		//return "TECHNOLOGY_ALREADY_EXISTS_ERROR";
			return "redirect";
		}
        //return SUCCESS;
    }

	private TestModelQuestionqty findTestModelQuestionQty(Set<TestModelQuestionqty> set, Integer id) {
		
		TestModelQuestionqty result = null;
		for (TestModelQuestionqty testModelQuestionqty : set) {
			if(testModelQuestionqty.getId().equals(id)){
				result = testModelQuestionqty;
				break;
			}
		}
		
		return result;
	}

	private void checkTechnologiesRepetition() {
		Set<Integer> keys = testModelQuestionQtyDTOMap.keySet();
		Set<TestModelQuestionQtyDTO> testModelQtyDTOSet = new HashSet<TestModelQuestionQtyDTO>();
		
		for (Integer key : keys) {
			
			if(!testModelQtyDTOSet.contains(testModelQuestionQtyDTOMap.get(key))){
				testModelQtyDTOSet.add(testModelQuestionQtyDTOMap.get(key));
			}else{
				throw new TechnologyAlreadyExistsException();
			}
		}
		Set<Integer> newkeys = newQuestionQtyDTOMap.keySet();
		
		for (Integer key : newkeys) {
			if(newQuestionQtyDTOMap.get(key).getTechnologyId()!=null){
				if(!testModelQtyDTOSet.contains(newQuestionQtyDTOMap.get(key))){
					
					//TestModelQuestionQtyDTO question = testModelQuestionQtyDTOMap.get(key);
					
					TestModelQuestionQtyDTO question = newQuestionQtyDTOMap.get(key);
					 
					Integer questionsQty = new Integer(question.getQtyQuestions());
					
					if(question.getTotalAmountOfQuestions() < questionsQty){
						throw new TotalAmountOfQuestionsException();
					}
					
					testModelQtyDTOSet.add(newQuestionQtyDTOMap.get(key));
				}else{
					throw new TechnologyAlreadyExistsException();
				}
			}
		}
		
	}
	
	
	private void controlarTecnologiasQuePoseeElTestModel(List<TestModelQuestionqty> listaDeTecnologias,TestModelQuestionqty tmq) {
		Boolean encontro = false;
		if(listaDeTecnologias.isEmpty()){
			listaDeTecnologias.add(tmq);
		}else{
			List<TestModelQuestionqty> toAddlist = new ArrayList<TestModelQuestionqty>();
			
			for (TestModelQuestionqty tmqExistente : tecnologiasQuePoseeElTestModel) {
				if(tmqExistente.getTechnology().getId().equals(tmq.getTechnology().getId())){
					encontro = true;
				}
			}
			if(!encontro){
				listaDeTecnologias.addAll(toAddlist);
			}else{
				//la tecnologia ya existe y esta agregada, entonces se hizo mal la edicion
				// y tiene que tirar un error en pantaia
				throw new TechnologyAlreadyExistsException();
			}
			
		}
		
	}

	public void setSession(Map session) {
		this.session = session;
		
	}
	public Map getSession() {
		return session;
	}

	public Integer getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(Integer idQuestion) {
		this.idQuestion = idQuestion;
	}

	public Integer getIdTestModel() {
		return idTestModel;
	}

	public void setIdTestModel(Integer idTestModel) {
		this.idTestModel = idTestModel;
	}

	public Boolean getEditTestModel() {
		return editTestModel;
	}

	public void setEditTestModel(Boolean editTestModel) {
		this.editTestModel = editTestModel;
	}

	public Map<Integer, TestModelQuestionQtyDTO> getTestModelQuestionQtyDTOMap() {
		return testModelQuestionQtyDTOMap;
	}

	public void setTestModelQuestionQtyDTOMap(
			Map<Integer, TestModelQuestionQtyDTO> testModelQuestionQtyDTOMap) {
		this.testModelQuestionQtyDTOMap = testModelQuestionQtyDTOMap;
	}

	public String getTestModel() {
		return testModel;
	}

	public void setTestModel(String testModel) {
		this.testModel = testModel;
	}

	public String getMinutesToFinish() {
		return minutesToFinish;
	}

	public void setMinutesToFinish(String minutesToFinish) {
		this.minutesToFinish = minutesToFinish;
	}

	public GenericDaoImplementation getSeniorityDao() {
		return seniorityDao;
	}

	public void setSeniorityDao(GenericDaoImplementation seniorityDao) {
		this.seniorityDao = seniorityDao;
	}

	public GenericDaoImplementation getTechnologyDao() {
		return technologyDao;
	}

	public void setTechnologyDao(GenericDaoImplementation technologyDao) {
		this.technologyDao = technologyDao;
	}

	public String getNewTechnologyId() {
		return newTechnologyId;
	}

	public void setNewTechnologyId(String newTechnologyId) {
		this.newTechnologyId = newTechnologyId;
	}

	public String getNewSecurityId() {
		return newSecurityId;
	}

	public void setNewSecurityId(String newSecurityId) {
		this.newSecurityId = newSecurityId;
	}

	public String getNewQty() {
		return newQty;
	}

	public void setNewQty(String newQty) {
		this.newQty = newQty;
	}

	public Boolean getNewTechnology() {
		return newTechnology;
	}

	public void setNewTechnology(Boolean newTechnology) {
		this.newTechnology = newTechnology;
	}

	public List<TestModelQuestionqty> getTecnologiasQuePoseeElTestModel() {
		return tecnologiasQuePoseeElTestModel;
	}

	public void setTecnologiasQuePoseeElTestModel(
			List<TestModelQuestionqty> tecnologiasQuePoseeElTestModel) {
		this.tecnologiasQuePoseeElTestModel = tecnologiasQuePoseeElTestModel;
	}

	public List<TestModelQuestionqty> getNuevasTecnologiasACrearEnElTestModel() {
		if(this.nuevasTecnologiasACrearEnElTestModel==null){
			this.nuevasTecnologiasACrearEnElTestModel = new ArrayList<TestModelQuestionqty>();
			
			//TestModelQuestionqty testModelQuestionQty = null;
			/*
			for (int i = 0; i < 50; i++) {
				testModelQuestionQty = new TestModelQuestionqty();
				this.nuevasTecnologiasACrearEnElTestModel.add(testModelQuestionQty);
			}
			*/
		}
		return nuevasTecnologiasACrearEnElTestModel;
	}

	public void setNuevasTecnologiasACrearEnElTestModel(
			List<TestModelQuestionqty> nuevasTecnologiasACrearEnElTestModel) {
		this.nuevasTecnologiasACrearEnElTestModel = nuevasTecnologiasACrearEnElTestModel;
	}

	public Map<Integer, TestModelQuestionQtyDTO> getNewQuestionQtyDTOMap() {
		return newQuestionQtyDTOMap;
	}

	public void setNewQuestionQtyDTOMap(
			Map<Integer, TestModelQuestionQtyDTO> newQuestionQtyDTOMap) {
		this.newQuestionQtyDTOMap = newQuestionQtyDTOMap;
	}

	public String getIdsChanged() {
		return idsChanged;
	}

	public void setIdsChanged(String idsChanged) {
		this.idsChanged = idsChanged;
	}

	public List<TestModel> getTestModels() {
		return testModels;
	}

	public void setTestModels(List<TestModel> testModels) {
		this.testModels = testModels;
	}

	
}
