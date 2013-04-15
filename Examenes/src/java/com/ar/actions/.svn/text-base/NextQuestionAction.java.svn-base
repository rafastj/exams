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

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;

import com.ar.mrm.dao.GenericDaoImplementation;
import com.ar.mrm.entities.Applicant;
import com.ar.mrm.entities.Option;
import com.ar.mrm.entities.OptionChosed;
import com.ar.mrm.entities.Question;
import com.ar.mrm.entities.QuestionAnswered;
import com.ar.mrm.entities.TechScore;
import com.ar.mrm.entities.Technology;
import com.ar.mrm.entities.Test;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author Santiago.Arias
 */
public class NextQuestionAction extends ActionSupport implements SessionAware {

	private List<QuestionAnswered> questionsAnswered; // = new ArrayList<QuestionAnswered>();
	private Map<String, Option> optionsSelected; // = new HashMap<String, Option>();
	private GenericDaoImplementation technoDao = new GenericDaoImplementation(Technology.class);
	GenericDaoImplementation questionAnsweredDao = new GenericDaoImplementation(QuestionAnswered.class);
	GenericDaoImplementation optionDao = new GenericDaoImplementation(Option.class);
	GenericDaoImplementation optionChosedDao = new GenericDaoImplementation(OptionChosed.class);
	GenericDaoImplementation technologyScoreDao = new GenericDaoImplementation(TechScore.class);
	GenericDaoImplementation testDao = new GenericDaoImplementation(Test.class);
	
	private Test test;
	private double score = 0;
	private Map session;
    private Logger logger = Logger.getLogger(NextQuestionAction.class);
	
	
	private Technology tecnologiaActual;
	private Integer preguntaActual;
	
    public Integer getPreguntaActual() {
		return preguntaActual;
	}

    private Map<Technology, ArrayList<Question>> todasLasPreguntas = new HashMap<Technology, ArrayList<Question>>();
    
    
	public Map<Technology, ArrayList<Question>> getTodasLasPreguntas() {
		return todasLasPreguntas;
	}

	public void setTodasLasPreguntas(
			Map<Technology, ArrayList<Question>> todasLasPreguntas) {
		this.todasLasPreguntas = todasLasPreguntas;
	}

	public void setPreguntaActual(Integer preguntaActual) {
		this.preguntaActual = preguntaActual;
	}

	private Applicant applicant;
    

	@Override
	public String execute() throws Exception {
		logger.debug("NextQuestionAction");
		
		if(((Boolean) getSession().get("testTimeOut"))){
			return "TEST-TIME-OUT";
		}
		
		List<Question> allquestions = new ArrayList<Question>();

		applicant = (Applicant) getSession().get("applicant");
		
		todasLasPreguntas = (HashMap<Technology, ArrayList<Question>>) getSession().get("todasLasPreguntas");
		tecnologiaActual = (Technology) getSession().get("tecnologiaActual");

		Map<String, Object> parameters = ActionContext.getContext().getParameters();
		Integer testId = (Integer) getSession().get("testId");
		
		try{
			test = (Test) testDao.read(testId);
        }catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while reading a test", e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("ERROR - While reading a test", e);
			e.printStackTrace();
		}

		
		questionsAnswered = (List<QuestionAnswered>) getSession().get("questionsAnswered");
		optionsSelected = (Map<String, Option>) getSession().get("optionsSelected");
		preguntaActual = (Integer) getSession().get("preguntaActual");
		
		if (test.getScore() == null) {
			allquestions = (List<Question>) session.get("questions");

			for (Map.Entry<String, Object> entry : parameters.entrySet()) {
				if(entry.getKey().toString().equals("timer")){
					continue;
				}
				
				Option optionSelected = new Option();
				
				optionSelected.setId(Integer.parseInt(String.valueOf(entry.getKey().toString())));
				
				if (String.valueOf(entry.getValue()).equals("false")) {
					optionSelected.setCorrect(false);
				} else {
					optionSelected.setCorrect(true);
				}
				optionsSelected.put(String.valueOf(optionSelected.getId()),
						optionSelected);
			}

			if (!todasLasPreguntas.isEmpty()) {
				if (!((todasLasPreguntas.get(tecnologiaActual).size())<=preguntaActual+1)) {
					//(todasLasPreguntas.get(tecnologiaActual)).remove(preguntaActual.intValue());
					preguntaActual++;
				} else {
					preguntaActual = 0;
					Set<Technology> tecnologiasDelTest = (Set<Technology>) getSession()
							.get("tecnologiasDelTest");
			
					
					tecnologiasDelTest.remove(tecnologiaActual);
					todasLasPreguntas.remove(tecnologiaActual);
					
					Iterator<Technology> i = tecnologiasDelTest.iterator();

					if(i.hasNext()) {
						tecnologiaActual = (Technology) i.next();
					} else {
						tecnologiaActual = null;
					}
					
					getSession().put("tecnologiasDelTest", tecnologiasDelTest);
					getSession().put("tecnologiaActual", tecnologiaActual);

				}
				getSession().put("todasLasPreguntas", todasLasPreguntas);
				getSession().put("preguntaActual", preguntaActual);
				getSession().put("questionsAnswered", questionsAnswered);
				getSession().put("optionsSelected", optionsSelected);
				
				return SUCCESS;
			} else {
				// finalizo el examen!
				logger.debug("the test is finished.");
				return "redirect";
			}
		}
		logger.error("Error");
		return ERROR;
	}

	private void persistTechScore(Technology techno) {
		double qtyQuestions = 0;
		double correctQuestions = 0;
		for (QuestionAnswered questionAnswered : questionsAnswered) {
			if (questionAnswered.getQuestion().getTechnology().getId()
					.equals(techno.getId())) {
				if (questionAnswered.isCorrect())
					correctQuestions++;
				qtyQuestions++;
			}
		}
		TechScore techScore = new TechScore();
		techScore.setTechnology(techno);
		techScore.setTest(test);
		techScore.setQuestions((int) qtyQuestions);
		techScore.setQuestionsCorrect((int) correctQuestions);
		techScore.setScore(qtyQuestions == 0 ? 0 : correctQuestions / qtyQuestions);
		
		try{
			technologyScoreDao.create(techScore);
        }catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while evaluationg a test", e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("ERROR - While evaluationg a test", e);
			e.printStackTrace();
		}
	}

	private void persitChosedOptions(Map<String, Option> options,
			QuestionAnswered questionAnswered) {
		
		for (Map.Entry<String, Option> opt : options.entrySet()) {
			Option option = (Option) optionDao.read(opt.getValue().getId());
			if (!option.getQuestion().getId()
					.equals(questionAnswered.getQuestion().getId())) {
				continue;
			}
			OptionChosed optChosed = new OptionChosed(questionAnswered, option,
					opt.getValue().isCorrect());
			optionChosedDao.create(optChosed);
			questionAnswered.getOptionChoseds().add(optChosed);
		}
	}

	private QuestionAnswered processQuestionAnswered(Question question,
			QuestionAnswered questionAnswered) {
		
		for (Option correctOpstion : question.getOptions()) {
			// we check for the correct options
			if (correctOpstion.isCorrect()) {
				if (!evaluateApplicantChoice(correctOpstion, true)) {
					questionAnswered.setCorrect(false);
					return questionAnswered;
				}
			} else {
				if (!evaluateApplicantChoice(correctOpstion, false)) {
					questionAnswered.setCorrect(false);
					return questionAnswered;
				}

			}
		}
		return questionAnswered;
	}

	private boolean evaluateApplicantChoice(Option correctOpstion,
			boolean shouldBe) {
		boolean result = true;
		for (Map.Entry<String, Option> optionSelected : optionsSelected
				.entrySet()) {
			if (correctOpstion.getId()
					.equals(optionSelected.getValue().getId())) {
				if (optionSelected.getValue().isCorrect() != shouldBe) {
					result = false;
				}
			}
		}

		return result;
	}

	private void persistQuestionResults(QuestionAnswered questionAnswered) {
		getQuestionsAnswered().add(questionAnswered);
		persistQuestionAnswered(questionAnswered);
		persitChosedOptions(optionsSelected, questionAnswered);

	}

	private void persistQuestionAnswered(QuestionAnswered questionAnswered) {
		questionAnsweredDao.create(questionAnswered);
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public Map getSession() {
		return session;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public List<QuestionAnswered> getQuestionsAnswered() {
		return questionsAnswered;
	}

	public void setQuestionsAnswered(List<QuestionAnswered> questionsAnswered) {
		this.questionsAnswered = questionsAnswered;
	}

	public Map<String, Option> getOptionsSelected() {
		return optionsSelected;
	}

	public void setOptionsSelected(Map<String, Option> optionsSelected) {
		this.optionsSelected = optionsSelected;
	}

	public GenericDaoImplementation getTechnoDao() {
		return technoDao;
	}

	public void setTechnoDao(GenericDaoImplementation technoDao) {
		this.technoDao = technoDao;
	}

	public Technology getTecnologiaActual() {
		return tecnologiaActual;
	}

	public void setTecnologiaActual(Technology tecnologiaActual) {
		this.tecnologiaActual = tecnologiaActual;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	

}
