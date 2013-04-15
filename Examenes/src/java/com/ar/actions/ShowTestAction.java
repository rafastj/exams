/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ar.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.ar.mrm.dao.GenericDaoImplementation;
import com.ar.mrm.entities.Applicant;
import com.ar.mrm.entities.Option;
import com.ar.mrm.entities.Question;
import com.ar.mrm.entities.QuestionAnswered;
import com.ar.mrm.entities.Technology;
import com.ar.mrm.entities.Test;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author Santiago.Arias
 */
public class ShowTestAction  extends ActionSupport implements SessionAware {

	private Logger logger = Logger.getLogger(ShowTestAction.class);
	
    private static final int TIMELIMIT_DEFAULT_VALUE = 120;
	private static final int NO_TIMELIMIT_SET = 0;
	private Map session;
    String code;
    private Integer testTime;
    private Technology tecnologiaActual;
    private Integer preguntaActual = -1;
    GenericDaoImplementation technologiesDao = new GenericDaoImplementation(Technology.class);
    GenericDaoImplementation testDao = new GenericDaoImplementation(Test.class);
    
    private Applicant applicant;
    
    public Integer getPreguntaActual() {
		return preguntaActual;
	}

	public void setPreguntaActual(Integer preguntaActual) {
		this.preguntaActual = preguntaActual;
	}

	private Map<Technology, ArrayList<Question>> todasLasPreguntas = new HashMap<Technology, ArrayList<Question>>();
    
    public ShowTestAction() {
    }

     @Override
    public String execute() throws Exception {
        if(!loadQuestionsForTest()){
        	return  ERROR;
        }
        
        TimerTask timerTask = new TimerTask() 
        { 
        	public void run(){ 
        		getSession().put("testTimeOut", new Boolean(true));
        	} 
        };
        
        Timer timer = new Timer();
		if(NO_TIMELIMIT_SET == testTime) {
			timer.schedule(timerTask, 1000*60*TIMELIMIT_DEFAULT_VALUE);
		} else {
			timer.schedule(timerTask, 1000*60*testTime);
			
		}
		
		
		
        return SUCCESS;
    }

    public boolean loadQuestionsForTest() {
        boolean result = true;
        List<Question> allQuestions = new ArrayList<Question>();
        List<Technology> technologies = new ArrayList<Technology>();
        
        try {
            technologies = technologiesDao.getAll();
            
            if(code ==  null || code.equals("")){
            	logger.error("ERROR - invalid code");
                getSession().put("error", "invalid code");
                return false;
            }
            logger.debug("code " + code);
            List<Test> allTestForUser = (List<Test>) testDao.executeQuery("from Test where applicant.code = '" + code + "'");
            if(allTestForUser.isEmpty()){
                logger.error("invalid code " + code);
                getSession().put("error", "invalid code " + code);
                return false;
            }
            Test test =  allTestForUser.get(0);
            this.testTime = test.getTestModel().getMinutesToFinish();
            if(test.getApplicant().isTestTaked()) {
                 logger.error("test already taken for " + test.getApplicant().getFirstName() + " " + test.getApplicant().getLastName());
                 getSession().put("error", "test already taken for " + test.getApplicant().getFirstName() + " " + test.getApplicant().getLastName());
                 return false;
            }
            applicant = test.getApplicant();
            getSession().put("applicant", applicant);
            
            List<Question> questionsForTest = new ArrayList<Question>(test.getQuestions());
            getSession().put("questions", questionsForTest);
            allQuestions = questionsForTest;
                        
            //Un mapa con clave tecnologia y valor todas las preguntas de esa tecnologia
            for(Technology t :technologies) {
            	ArrayList<Question> preguntas = new ArrayList<Question>();
            	for(Question q :allQuestions) {
            		if(q.getTechnology().equals(t)) {
            			preguntas.add(q);
            		}
            	}
            	if(!preguntas.isEmpty()) {
            		todasLasPreguntas.put(t, preguntas);
            	}
            }
            
                      
            //obtengo todas las tecnologias del test
            Set<Technology> tecnologiasDelTest = todasLasPreguntas.keySet();
            Iterator<Technology> i = tecnologiasDelTest.iterator();
            
            if(i.hasNext()) {
            	tecnologiaActual = (Technology) i.next();
            }
            
            getSession().put("tecnologiasDelTest", tecnologiasDelTest);
            
            //Tambien son atributos del action
            getSession().put("tecnologiaActual", tecnologiaActual);
            getSession().put("todasLasPreguntas", todasLasPreguntas);
            
            getSession().put("questionsAnswered", new ArrayList<QuestionAnswered>());
            getSession().put("optionsSelected", new HashMap<String, Option>());
            
            getSession().put("preguntaActual", preguntaActual);
            
            Integer testId = (Integer) session.get("testId");
            getSession().put("testId",test.getId());
            getSession().put("finishedTestFlag",new Boolean(false));
            
       	 	getSession().put("testTimeOut", new Boolean(false));

           
        } catch (Exception e) {
        	logger.error("error", e);
            e.printStackTrace();
            result = false;
        }
        return result;
    }


    public void setSession(Map map) {
        this.session = map;
    }

    public Map<String,Object> getSession(){
        return this.session;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public Map<Technology, ArrayList<Question>> getTodasLasPreguntas() {
		return todasLasPreguntas;
	}

	public void setTodasLasPreguntas(
			Map<Technology, ArrayList<Question>> todasLasPreguntas) {
		this.todasLasPreguntas = todasLasPreguntas;
	}

	public Technology getTecnologiaActual() {
		return tecnologiaActual;
	}

	public void setTecnologiaActual(Technology tecnologiaActual) {
		this.tecnologiaActual = tecnologiaActual;
	}

	public Integer getTestTime() {
		return testTime;
	}

	public void setTestTime(Integer testTime) {
		this.testTime = testTime;
	}
	
}
