/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ar.actions;

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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;

/**
 *
 * @author Santiago.Arias
 */
public class EvaluateTestAction extends ActionSupport implements SessionAware {

	private Logger logger = Logger.getLogger(EvaluateTestAction.class);
	
    private List<QuestionAnswered> questionsAnswered = new ArrayList<QuestionAnswered>();    
    private Map<String, Option> optionsSelected = new HashMap<String, Option>();
    private GenericDaoImplementation technoDao = new GenericDaoImplementation(Technology.class);
    GenericDaoImplementation questionAnsweredDao = new GenericDaoImplementation(QuestionAnswered.class);
    GenericDaoImplementation technologyScoreDao = new GenericDaoImplementation(TechScore.class);
    GenericDaoImplementation optionDao = new GenericDaoImplementation(Option.class);
    GenericDaoImplementation optionChosedDao = new GenericDaoImplementation(OptionChosed.class);    
    private Test test;
    private double score = 0;
    private Map session;

    @Override
    public String execute() throws Exception {
        GenericDaoImplementation testDao = new GenericDaoImplementation(Test.class);
        List<Question> allquestions = new ArrayList<Question>();
        
        //
        Map<String, Object> parameters = ActionContext.getContext().getParameters();
        Integer testId = (Integer) session.get("testId");
        test = (Test) testDao.read(testId);
        if(test.getScore()==null){    
	        allquestions = (List<Question>) session.get("questions");
	
	        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
	            Option optionSelected = new Option();
	            optionSelected.setId(Integer.parseInt(String.valueOf(entry.getKey().toString())));
	            if (String.valueOf(entry.getValue()).equals("false")) {
	                optionSelected.setCorrect(false);
	            } else {
	                optionSelected.setCorrect(true);
	            }
	            optionsSelected.put(String.valueOf(optionSelected.getId()),optionSelected);
	        }
	        for (Question question : allquestions) {
	            QuestionAnswered questionAnswered = new QuestionAnswered(question, test, true);
	            processQuestionAnswered(question, questionAnswered);
	            if(questionAnswered.isCorrect()) score++;
	            persistQuestionResults(questionAnswered);
	        }
	
	        
	        score = (score/(double)allquestions.size())*100;
	        BigDecimal bd = new BigDecimal(Double.toString(score));
	        bd = bd.setScale( 2, BigDecimal.ROUND_HALF_UP );
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
	
	        logger.debug("\n\n\n final score " + score + "/" + allquestions.size() + "\n\n\n");
	        List<Technology> technologies = technoDao.getAll();
	       for(Technology techno : technologies){
	           persistTechScore(techno);
	       }
	   
	        return SUCCESS;
        }
        
        return ERROR;
    }

    private void persistTechScore(Technology techno){
        double qtyQuestions = 0;
        double correctQuestions = 0;
        for(QuestionAnswered questionAnswered: questionsAnswered){
            if(questionAnswered.getQuestion().getTechnology().getId().equals(techno.getId())){
                if(questionAnswered.isCorrect()) correctQuestions++;
                qtyQuestions++;
            }
        }
        TechScore techScore = new TechScore();
        techScore.setTechnology(techno);
        techScore.setTest(test);
        techScore.setQuestions((int)qtyQuestions);
        techScore.setQuestionsCorrect((int)correctQuestions);
        techScore.setScore(qtyQuestions==0?0:correctQuestions/qtyQuestions);
        
        try{
        	technologyScoreDao.create(techScore);
        }catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while evaluating a test", e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("ERROR - While evaluating a test", e);
			e.printStackTrace();
		}
    }


    private void persitChosedOptions(Map<String, Option> options, QuestionAnswered questionAnswered) {
        for (Map.Entry<String, Option> opt : options.entrySet()) {            
            Option option = (Option) optionDao.read(opt.getValue().getId());
            if(!option.getQuestion().getId().equals(questionAnswered.getQuestion().getId())){
                continue;
            }
            OptionChosed optChosed = new OptionChosed(questionAnswered,option,opt.getValue().isCorrect());
            
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

    private QuestionAnswered processQuestionAnswered(Question question, QuestionAnswered questionAnswered) {
        
    	for (Option correctOpstion : question.getOptions()) {
            //we check for the correct options
            if (correctOpstion.isCorrect()) {
                if(!evaluateApplicantChoice(correctOpstion, true)){
                    questionAnswered.setCorrect(false);
                            return questionAnswered;                    
                }
            } else {
                if(!evaluateApplicantChoice(correctOpstion, false)){
                    questionAnswered.setCorrect(false);
                            return questionAnswered;
                }

            }
        }
        return questionAnswered;
    }

    private boolean evaluateApplicantChoice(Option correctOpstion,boolean shouldBe){
        boolean result = true;
        for (Map.Entry<String, Option> optionSelected : optionsSelected.entrySet()) {
                    if (correctOpstion.getId().equals(optionSelected.getValue().getId())) {
                        if (optionSelected.getValue().isCorrect() != shouldBe) {
                            result = false;                          
                        }
                    }
                }

        return result;
    }

    private void persistQuestionResults(QuestionAnswered  questionAnswered) {
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
    
}
