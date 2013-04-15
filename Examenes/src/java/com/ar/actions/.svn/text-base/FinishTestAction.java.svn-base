package com.ar.actions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;

import com.ar.mrm.dao.GenericDaoImplementation;
import com.ar.mrm.dao.TechScoreDAO;
import com.ar.mrm.dao.TechnologyDAO;
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

public class FinishTestAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 5048453932079755524L;

	private Logger logger = Logger.getLogger(FinishTestAction.class);
	
	private List<QuestionAnswered> questionsAnswered; // = new ArrayList<QuestionAnswered>();
	private Map<String, Option> optionsSelected; // = new HashMap<String, Option>();
	//GenericDaoImplementation technoDao = new GenericDaoImplementation(Technology.class);
	private TechnologyDAO technologyDAO;
	private TechScoreDAO techScoreDAO;
	GenericDaoImplementation testDao = new GenericDaoImplementation(Test.class);
	GenericDaoImplementation optionDao = new GenericDaoImplementation(Option.class);
	GenericDaoImplementation optionChosedDao = new GenericDaoImplementation(OptionChosed.class);
	GenericDaoImplementation questionAnsweredDao = new GenericDaoImplementation(QuestionAnswered.class);
	
	private Test test;
	private double score = 0;
	private Map session;
	
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
		
		logger.debug("FinishTestAction");
		
		List<Question> allquestions = new ArrayList<Question>();

		applicant = (Applicant) getSession().get("applicant");
		
		todasLasPreguntas = (HashMap<Technology, ArrayList<Question>>) getSession().get("todasLasPreguntas");
		tecnologiaActual = (Technology) getSession().get("tecnologiaActual");

		Map<String, Object> parameters = ActionContext.getContext().getParameters();
		Integer testId = (Integer) getSession().get("testId");
		test = (Test) testDao.read(testId);

		
		questionsAnswered = (List<QuestionAnswered>) getSession().get("questionsAnswered");
		optionsSelected = (Map<String, Option>) getSession().get("optionsSelected");
		preguntaActual = (Integer) getSession().get("preguntaActual");

		

		if (test.getScore() == null) {
			
				allquestions = (List<Question>) session.get("questions");
				
				// finalizo el examen!
		
				for (Question question : allquestions) {
					QuestionAnswered questionAnswered = new QuestionAnswered(
							question, test, true);
					processQuestionAnswered(question, questionAnswered);
					if (questionAnswered.isCorrect())
						score++;
					persistQuestionResults(questionAnswered);
				}
		
				if((allquestions!=null)&&(allquestions.size()!=0)){
					score = (score / (double) allquestions.size()) * 100;
					
				}else{
					score = 0D;
				}
					
				
				BigDecimal bd = new BigDecimal(Double.toString(score));
				bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
				test.setScore(bd.doubleValue());
				test.getApplicant().setTestTaked(true);
				try{
					testDao.update(test);
		        }catch (HibernateException e) {
					logger.error("ERROR - Hibernate Exception while updating a test", e);
					e.printStackTrace();
				}catch (Exception e) {
					logger.error("ERROR - While updating a test", e);
					e.printStackTrace();
				}
		
				logger.debug("\n\n\n final score " + score + "/"
						+ allquestions.size() + "\n\n\n");
				List<Technology> technologies = technologyDAO.getAll();
				for (Technology techno : technologies) {
					persistTechScore(techno);
				}
		
				return "SUCCESS";
		}else{
			return "error";
		}

	}

	private void persistTechScore(Technology techno) {
		//GenericDaoImplementation technologyScoreDao = new GenericDaoImplementation(TechScore.class);
		
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
			//technologyScoreDao.create(techScore);
			techScoreDAO.create(techScore);
        }catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while evaluating a test", e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("ERROR - While evaluating a test", e);
			e.printStackTrace();
		}
	}

	private void persitChosedOptions(Map<String, Option> options,QuestionAnswered questionAnswered) {
		
		for (Map.Entry<String, Option> opt : options.entrySet()) {
			Option option = (Option) optionDao.read(opt.getValue().getId());
			if (!option.getQuestion().getId().equals(questionAnswered.getQuestion().getId())) {
				continue;
			}
			OptionChosed optChosed = new OptionChosed(questionAnswered, option,opt.getValue().isCorrect());
			try{
				optionChosedDao.create(optChosed);
	        }catch (HibernateException e) {
				logger.error("ERROR - Hibernate Exception while persisting options", e);
				e.printStackTrace();
			}catch (Exception e) {
				logger.error("ERROR - While persisting options", e);
				e.printStackTrace();
			}
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
		try{
			questionAnsweredDao.create(questionAnswered);
		}catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while persisting answered questions", e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("ERROR - While persisting answered questions", e);
			e.printStackTrace();
		}
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


	public TechnologyDAO getTechnologyDAO() {
		return technologyDAO;
	}

	public void setTechnologyDAO(TechnologyDAO technologyDAO) {
		this.technologyDAO = technologyDAO;
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

	public TechScoreDAO getTechScoreDAO() {
		return techScoreDAO;
	}

	public void setTechScoreDAO(TechScoreDAO techScoreDAO) {
		this.techScoreDAO = techScoreDAO;
	}
	
}
