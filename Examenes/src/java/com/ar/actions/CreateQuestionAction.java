/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ar.actions;


import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.ar.mrm.dao.GenericDaoImplementation;
import com.ar.mrm.entities.Option;
import com.ar.mrm.entities.Question;
import com.ar.mrm.entities.Seniority;
import com.ar.mrm.entities.Technology;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Santiago.Arias
 */
public class CreateQuestionAction extends ActionSupport implements SessionAware {
	private Logger logger = Logger.getLogger(CreateQuestionAction.class); 
	
    private String firstOption,secondOption,thirdOption,fourthOption,fifthOption,
    sixthOption,seventhOption,eightOption,ninethOption, tenOption, profile,
    seniority, technology;
    private boolean firstOptionValue, secondOptionValue,thirdOptionValue,fourthOptionValue, fifthOptionValue,
    sixthOptionValue , seventhOptionValue, eightOptionValue,ninethOptionValue, tenOptionValue;
    private String questionDescription;
    private Map session ;
    GenericDaoImplementation optionDao = new GenericDaoImplementation(Option.class);
	GenericDaoImplementation questionDao = new GenericDaoImplementation(Question.class);
    GenericDaoImplementation technoDao = new GenericDaoImplementation(Technology.class);
    GenericDaoImplementation seniorityDao = new GenericDaoImplementation(Seniority.class);
    

    @Override
       public String execute() throws Exception {
        if(!validateCheckBoxes() || !checkSeniority()) return ERROR;
        createOptions(createQuestion());
        session.put("sucess", "Question Created Ok!");
        logger.debug("Question Created Ok!");
        return SUCCESS;
    }  

    
    
    private Question createQuestion() {
        Question question = new Question();
        logger.debug("question description " + questionDescription.replace("\n", "<br>"));
        question.setDescription(questionDescription);
        Technology tech = (Technology) technoDao.read(Integer.parseInt(technology));
        logger.debug("technology retreived " + tech.getDescription());
        Seniority st = (Seniority) seniorityDao.read(Integer.parseInt(seniority));
        question.setTechnology(tech);
        question.setSeniority(st);
        questionDao.create(question);
        return question;
    }

    private boolean checkSeniority() {
        boolean result = true;
        if (getSeniority().equalsIgnoreCase("0") || getTechnology().equalsIgnoreCase("0")) {
            getSession().put("error", "You need to select a valid Seniority and Technology to create The Question option");
            logger.debug("ERROR - You need to select a valid Seniority and Technology to create The Question option");
            result = false;
        }
        logger.debug("getSeniority  -->" + getSeniority());
        
        return result;
    }

    private boolean validateCheckBoxes(){
        boolean result = true;
        if(!firstOptionValue&&!secondOptionValue&&!thirdOptionValue&&
           !fourthOptionValue && !fifthOptionValue && !sixthOptionValue && !seventhOptionValue && !eightOptionValue
           && !ninethOptionValue && !tenOptionValue){
            getSession().put("error", "You need to select one valid option");
            logger.debug("ERROR - You need to select one valid option");
            result = false;
        }

        return result;
    }


   private void createOptions(Question question) {
       persistOption(question,firstOption,firstOptionValue);
       persistOption(question,secondOption,secondOptionValue);
       persistOption(question,thirdOption,thirdOptionValue);
       persistOption(question,fourthOption,fourthOptionValue);
       persistOption(question,fifthOption,fifthOptionValue);
       persistOption(question,sixthOption,sixthOptionValue);
       persistOption(question,seventhOption,seventhOptionValue);
       persistOption(question,eightOption,eightOptionValue);
       persistOption(question,ninethOption,ninethOptionValue);
       persistOption(question,tenOption,tenOptionValue);
    }

   private void persistOption(Question question,String description, boolean correct){
       if(question==null || description ==null || description.equals(""))return;
        Option opt = new Option();
        opt.setQuestion(question);
        opt.setDescription(description);
        opt.setCorrect(correct);
        optionDao.create(opt);
   }


    public String getFirstOption() {
        return firstOption;
    }

    public void setFirstOption(String firstOption) {
        this.firstOption = firstOption;
    }

    public boolean isFirstOptionValue() {
        return firstOptionValue;
    }

    public void setFirstOptionValue(boolean firstOptionValue) {
        this.firstOptionValue = firstOptionValue;
    }

    public GenericDaoImplementation getOptionDao() {
        return optionDao;
    }

    public void setOptionDao(GenericDaoImplementation optionDao) {
        this.optionDao = optionDao;
    }

    public String getSecondOption() {
        return secondOption;
    }

    public void setSecondOption(String secondOption) {
        this.secondOption = secondOption;
    }

    public boolean isSecondOptionValue() {
        return secondOptionValue;
    }

    public void setSecondOptionValue(boolean secondOptionValue) {
        this.secondOptionValue = secondOptionValue;
    }

    public String getThirdOption() {
        return thirdOption;
    }

    public void setThirdOption(String thirdOption) {
        this.thirdOption = thirdOption;
    }

    public boolean isThirdOptionValue() {
        return thirdOptionValue;
    }

    public void setThirdOptionValue(boolean thirdOptionValue) {
        this.thirdOptionValue = thirdOptionValue;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

   
    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public void setSession(Map session) {
        this.session = session;
    }
    
    public Map getSession() {
        return  this.session;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public String getEightOption() {
        return eightOption;
    }

    public void setEightOption(String eightOption) {
        this.eightOption = eightOption;
    }


    public String getFifthOption() {
        return fifthOption;
    }

    public void setFifthOption(String fifthOption) {
        this.fifthOption = fifthOption;
    }

    public boolean isFifthOptionValue() {
        return fifthOptionValue;
    }

    public void setFifthOptionValue(boolean fifthOptionValue) {
        this.fifthOptionValue = fifthOptionValue;
    }

    public String getFourthOption() {
        return fourthOption;
    }

    public void setFourthOption(String fourthOption) {
        this.fourthOption = fourthOption;
    }

    public boolean isFourthOptionValue() {
        return fourthOptionValue;
    }

    public void setFourthOptionValue(boolean fourthOptionValue) {
        this.fourthOptionValue = fourthOptionValue;
    }

    public String getNinethOption() {
        return ninethOption;
    }

    public void setNinethOption(String ninethOption) {
        this.ninethOption = ninethOption;
    }

   

    public String getSeventhOption() {
        return seventhOption;
    }

    public void setSeventhOption(String seventhOption) {
        this.seventhOption = seventhOption;
    }

  

    public String getSixthOption() {
        return sixthOption;
    }

    public void setSixthOption(String sixthOption) {
        this.sixthOption = sixthOption;
    }

 

    public String getTenOption() {
        return tenOption;
    }

    public void setTenOption(String tenOption) {
        this.tenOption = tenOption;
    }

    public boolean isEightOptionValue() {
        return eightOptionValue;
    }

    public void setEightOptionValue(boolean eightOptionValue) {
        this.eightOptionValue = eightOptionValue;
    }

    public boolean isNinethOptionValue() {
        return ninethOptionValue;
    }

    public void setNinethOptionValue(boolean ninethOptionValue) {
        this.ninethOptionValue = ninethOptionValue;
    }

    public boolean isSeventhOptionValue() {
        return seventhOptionValue;
    }

    public void setSeventhOptionValue(boolean seventhOptionValue) {
        this.seventhOptionValue = seventhOptionValue;
    }

    public boolean isSixthOptionValue() {
        return sixthOptionValue;
    }

    public void setSixthOptionValue(boolean sixthOptionValue) {
        this.sixthOptionValue = sixthOptionValue;
    }

    public boolean isTenOptionValue() {
        return tenOptionValue;
    }

    public void setTenOptionValue(boolean tenOptionValue) {
        this.tenOptionValue = tenOptionValue;
    }
    

}
