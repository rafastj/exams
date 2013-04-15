/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ar.actions;

import com.ar.mrm.dao.GenericDaoImplementation;
import com.ar.mrm.entities.Applicant;
import com.ar.mrm.entities.Question;
import com.ar.mrm.entities.Test;
import com.ar.mrm.entities.TestModel;
import com.ar.mrm.entities.TestModelQuestionqty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Santi
 */
public class CreateTestAction implements SessionAware {

	private Logger logger = Logger.getLogger(CreateTestAction.class);
    private List<Question> allQuestions = new ArrayList<Question>();
    private TestModel testModelAssigned;
    private Applicant applicantAssigned;
    private Test test = new Test();
    private Map session;
    GenericDaoImplementation questionDao = new GenericDaoImplementation(Question.class);
    GenericDaoImplementation applicantDao = new GenericDaoImplementation(Applicant.class);

    public CreateTestAction() {
    }

    public CreateTestAction(TestModel testModelAssigned, Applicant applicantAsigned) {
        this.applicantAssigned = applicantAsigned;
        this.testModelAssigned = testModelAssigned;
    }

    public boolean execute() throws Exception {
    	logger.debug("CreateTestAction");
        if (!createTest() || !loadQuestions()) {
            return false;
        }
        return true;
    }

    private boolean createTest() {
        boolean result = false;
        try {
            test.setTestModel(testModelAssigned);
            test.setApplicant(applicantAssigned);
            applicantDao.create(test);
            if (test.getId() != null) {
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error en createTest", e);
        } finally {
            return result;
        }
    }

    public boolean loadQuestions() {

        boolean result = true;
        try {
            List<Question> allQuestionsInDatabase = new ArrayList<Question>();
            allQuestionsInDatabase = questionDao.getAll();
            Collections.shuffle(allQuestionsInDatabase);
            for (TestModelQuestionqty testModelQuestionqty : test.getTestModel().getTestModelQuestionqties()) {
                for (int i = 0; i < testModelQuestionqty.getQtyQuestions(); i++) {
                    //for(Question question : allQuestionsInDatabase){
                    for (Iterator<Question> iter = allQuestionsInDatabase.iterator(); iter.hasNext();) {
                        Question question = iter.next();
                        if (question.getTechnology().getId().equals(testModelQuestionqty.getTechnology().getId())
                                && question.getSeniority().getId().equals(testModelQuestionqty.getSeniority().getId())) {
                            test.getQuestions().add(question);
                            iter.remove();
                            break;
                        }
                    }


                }
            }
            /*
            for (Question question : test.getQuestions()) {
                logger.debug(" question loadded for test -->" + question.getDescription());
            }
            */
            applicantDao.update(test);
        } catch (Exception e) {
            e.printStackTrace();
            getSession().put("error", "invalid parameters");
            logger.error("invalid parameters", e);
            result = false;
        }

        return result;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public Map getSession() {
        return session;
    }

    public List<Question> getAllQuestions() {
        return allQuestions;
    }

    public void setAllQuestions(List<Question> allQuestions) {
        this.allQuestions = allQuestions;
    }

    public TestModel getTestModelAssigned() {
        return testModelAssigned;
    }

    public void setTestModelAssigned(TestModel testModelAssigned) {
        this.testModelAssigned = testModelAssigned;
    }

    public Applicant getApplicantAssigned() {
        return applicantAssigned;
    }

    public void setApplicantAssigned(Applicant applicantAssigned) {
        this.applicantAssigned = applicantAssigned;
    }
}
