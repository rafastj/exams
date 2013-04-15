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

public class UpdateQuestionAction extends ActionSupport implements SessionAware {
	
	private Logger logger = Logger.getLogger(UpdateQuestionAction.class);
	
	private String firstOption,secondOption,thirdOption,fourthOption,fifthOption,
    sixthOption,seventhOption,eightOption,ninethOption, tenOption, profile,
    seniority, technology;
    private boolean firstOptionValue, secondOptionValue,thirdOptionValue,fourthOptionValue, fifthOptionValue,
    sixthOptionValue , seventhOptionValue, eightOptionValue,ninethOptionValue, tenOptionValue;
    private String questionDescription;
    private Map session ;
    private GenericDaoImplementation optionDao = new GenericDaoImplementation(Option.class);
    private GenericDaoImplementation questionDao = new GenericDaoImplementation(Question.class);
    GenericDaoImplementation technoDao = new GenericDaoImplementation(Technology.class);
    GenericDaoImplementation seniorityDao = new GenericDaoImplementation(Seniority.class);
    
    private String id_q;
       
    private Integer firstOptionId;
    private Integer secondOptionId;
    private Integer thirdOptionId;
    private Integer fourthOptionId;
    private Integer fifthOptionId;
    private Integer sixthOptionId;
    private Integer seventhOptionId;
    private Integer eightOptionId;
    private Integer ninethOptionId;
    private Integer tenthOptionId;
    
    
    @Override
    public String execute() throws Exception {
     if(!validateCheckBoxes() || !checkSeniority()) return ERROR;
     createOptions(createQuestion());
     session.put("sucess", "Question Created Ok!");
     
     //return SUCCESS;
     return "redirect";
    }  


    private Question createQuestion() {
        
        Question question = null;
        Integer id = null;
        try{
        	id = Integer.parseInt(id_q);
        	
        }catch (NumberFormatException e) {
			e.printStackTrace();
		}
        question = (Question) questionDao.read(id);
        
        logger.debug("question description " + questionDescription.replace("\n", "<br>"));
        question.setDescription(questionDescription);
        Technology tech = (Technology) technoDao.read(Integer.parseInt(technology));
        logger.debug("technology retreived " + tech.getDescription());
        Seniority st = (Seniority) seniorityDao.read(Integer.parseInt(seniority));
        question.setTechnology(tech);
        question.setSeniority(st);
        //questionDao.create(question);
        questionDao.update(question);
        return question;
    }
    
    private void createOptions(Question question) {
        updateOption(question,firstOption,firstOptionValue,firstOptionId);
        updateOption(question,secondOption,secondOptionValue,secondOptionId);
        updateOption(question,thirdOption,thirdOptionValue,thirdOptionId);
        updateOption(question,fourthOption,fourthOptionValue,fourthOptionId);
        updateOption(question,fifthOption,fifthOptionValue,fifthOptionId);
        updateOption(question,sixthOption,sixthOptionValue,sixthOptionId);
        updateOption(question,seventhOption,seventhOptionValue,seventhOptionId);
        updateOption(question,eightOption,eightOptionValue,eightOptionId);
        updateOption(question,ninethOption,ninethOptionValue,ninethOptionId);
        updateOption(question,tenOption,tenOptionValue,tenthOptionId);
     }

    private void updateOption(Question question,String description, boolean correct, Integer optionId){
    	
    	// aca hay que analizar si borrar todas las opciones y generarlas denuevo o 
    	// hacerles update con el id de opcion
    	
        Option opt = null;
        if(optionId==null){
        	
        	if(question==null || description ==null || description.equals(""))return;
        	
        	opt = new Option();
        	opt.setQuestion(question);
        	opt.setDescription(description);
        	opt.setCorrect(correct);
        	optionDao.create(opt);
 	
        }else{
        	opt = (Option) optionDao.read(optionId);
        	
        	//si la descripcion esta vacia , hay que borrar la opcion
        	if((description == null)||(description.trim().equals(""))){
        		question.getOptions().remove(opt);
        		opt.setQuestion(null);
        		optionDao.delete(opt);
        		questionDao.update(question);
        		
        	}else{
        		opt.setQuestion(question);
        		opt.setDescription(description);
        		opt.setCorrect(correct);
        		optionDao.update(opt);
        	} 	
        }
    }
    
    
    private boolean validateCheckBoxes(){
        boolean result = true;
        if(!firstOptionValue&&!secondOptionValue&&!thirdOptionValue&&
           !fourthOptionValue && !fifthOptionValue && !sixthOptionValue && !seventhOptionValue && !eightOptionValue
           && !ninethOptionValue && !tenOptionValue){
            getSession().put("error", "You need to select one valid option");
            result = false;
        }

        return result;
    }
    
    private boolean checkSeniority() {
        boolean result = true;
        if (getSeniority().equalsIgnoreCase("0") || getTechnology().equalsIgnoreCase("0")) {
            getSession().put("error", "You need to select a valid Seniority and Technology to create The Question option");
            result = false;
        }
        logger.debug("getSeniority  -->" + getSeniority());
        return result;
    }


	public String getFirstOption() {
		return firstOption;
	}


	public void setFirstOption(String firstOption) {
		this.firstOption = firstOption;
	}


	public String getSecondOption() {
		return secondOption;
	}


	public void setSecondOption(String secondOption) {
		this.secondOption = secondOption;
	}


	public String getThirdOption() {
		return thirdOption;
	}


	public void setThirdOption(String thirdOption) {
		this.thirdOption = thirdOption;
	}


	public String getFourthOption() {
		return fourthOption;
	}


	public void setFourthOption(String fourthOption) {
		this.fourthOption = fourthOption;
	}


	public String getFifthOption() {
		return fifthOption;
	}


	public void setFifthOption(String fifthOption) {
		this.fifthOption = fifthOption;
	}


	public String getSixthOption() {
		return sixthOption;
	}


	public void setSixthOption(String sixthOption) {
		this.sixthOption = sixthOption;
	}


	public String getSeventhOption() {
		return seventhOption;
	}


	public void setSeventhOption(String seventhOption) {
		this.seventhOption = seventhOption;
	}


	public String getEightOption() {
		return eightOption;
	}


	public void setEightOption(String eightOption) {
		this.eightOption = eightOption;
	}


	public String getNinethOption() {
		return ninethOption;
	}


	public void setNinethOption(String ninethOption) {
		this.ninethOption = ninethOption;
	}


	public String getTenOption() {
		return tenOption;
	}


	public void setTenOption(String tenOption) {
		this.tenOption = tenOption;
	}


	public String getProfile() {
		return profile;
	}


	public void setProfile(String profile) {
		this.profile = profile;
	}


	public String getSeniority() {
		return seniority;
	}


	public void setSeniority(String seniority) {
		this.seniority = seniority;
	}


	public String getTechnology() {
		return technology;
	}


	public void setTechnology(String technology) {
		this.technology = technology;
	}


	public boolean isFirstOptionValue() {
		return firstOptionValue;
	}


	public void setFirstOptionValue(boolean firstOptionValue) {
		this.firstOptionValue = firstOptionValue;
	}


	public boolean isSecondOptionValue() {
		return secondOptionValue;
	}


	public void setSecondOptionValue(boolean secondOptionValue) {
		this.secondOptionValue = secondOptionValue;
	}


	public boolean isThirdOptionValue() {
		return thirdOptionValue;
	}


	public void setThirdOptionValue(boolean thirdOptionValue) {
		this.thirdOptionValue = thirdOptionValue;
	}


	public boolean isFourthOptionValue() {
		return fourthOptionValue;
	}


	public void setFourthOptionValue(boolean fourthOptionValue) {
		this.fourthOptionValue = fourthOptionValue;
	}


	public boolean isFifthOptionValue() {
		return fifthOptionValue;
	}


	public void setFifthOptionValue(boolean fifthOptionValue) {
		this.fifthOptionValue = fifthOptionValue;
	}


	public boolean isSixthOptionValue() {
		return sixthOptionValue;
	}


	public void setSixthOptionValue(boolean sixthOptionValue) {
		this.sixthOptionValue = sixthOptionValue;
	}


	public boolean isSeventhOptionValue() {
		return seventhOptionValue;
	}


	public void setSeventhOptionValue(boolean seventhOptionValue) {
		this.seventhOptionValue = seventhOptionValue;
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


	public boolean isTenOptionValue() {
		return tenOptionValue;
	}


	public void setTenOptionValue(boolean tenOptionValue) {
		this.tenOptionValue = tenOptionValue;
	}


	public String getQuestionDescription() {
		return questionDescription;
	}


	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}


	public Map getSession() {
		return session;
	}


	public void setSession(Map session) {
		this.session = session;
	}


	public GenericDaoImplementation getOptionDao() {
		return optionDao;
	}


	public void setOptionDao(GenericDaoImplementation optionDao) {
		this.optionDao = optionDao;
	}


	public String getId_q() {
		return id_q;
	}


	public void setId_q(String idQ) {
		id_q = idQ;
	}


	public Integer getFirstOptionId() {
		return firstOptionId;
	}


	public void setFirstOptionId(Integer firstOptionId) {
		this.firstOptionId = firstOptionId;
	}


	public Integer getSecondOptionId() {
		return secondOptionId;
	}


	public void setSecondOptionId(Integer secondOptionId) {
		this.secondOptionId = secondOptionId;
	}


	public Integer getThirdOptionId() {
		return thirdOptionId;
	}


	public void setThirdOptionId(Integer thirdOptionId) {
		this.thirdOptionId = thirdOptionId;
	}


	public Integer getFourthOptionId() {
		return fourthOptionId;
	}


	public void setFourthOptionId(Integer fourthOptionId) {
		this.fourthOptionId = fourthOptionId;
	}


	public Integer getFifthOptionId() {
		return fifthOptionId;
	}


	public void setFifthOptionId(Integer fifthOptionId) {
		this.fifthOptionId = fifthOptionId;
	}


	public Integer getSixthOptionId() {
		return sixthOptionId;
	}


	public void setSixthOptionId(Integer sixthOptionId) {
		this.sixthOptionId = sixthOptionId;
	}


	public Integer getSeventhOptionId() {
		return seventhOptionId;
	}


	public void setSeventhOptionId(Integer seventhOptionId) {
		this.seventhOptionId = seventhOptionId;
	}


	public Integer getEightOptionId() {
		return eightOptionId;
	}


	public void setEightOptionId(Integer eightOptionId) {
		this.eightOptionId = eightOptionId;
	}


	public Integer getNinethOptionId() {
		return ninethOptionId;
	}


	public void setNinethOptionId(Integer ninethOptionId) {
		this.ninethOptionId = ninethOptionId;
	}


	public Integer getTenthOptionId() {
		return tenthOptionId;
	}


	public void setTenthOptionId(Integer tenthOptionId) {
		this.tenthOptionId = tenthOptionId;
	}
    
	

	
}
