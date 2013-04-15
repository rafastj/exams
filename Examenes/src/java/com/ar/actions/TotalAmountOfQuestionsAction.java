package com.ar.actions;

import java.util.ArrayList;
import java.util.List;

import com.ar.mrm.dao.GenericDaoImplementation;
import com.ar.mrm.entities.Question;
import com.opensymphony.xwork2.ActionSupport;

public class TotalAmountOfQuestionsAction extends ActionSupport  {
	private static final long serialVersionUID = -8708000055518424042L;
	private String technologyId;
	private String seniorityId;
    private GenericDaoImplementation questionsDao = new GenericDaoImplementation(Question.class);
	private String totalAmountOfQuestionsForTechnologySeniority;
    
    @Override
    public String execute() throws Exception {
    	List<Question> totalQuestions = retreiveQuestionsFromDb(technologyId, seniorityId);
    	if(totalQuestions!=null){
    		Integer sizeString = totalQuestions.size();
    		totalAmountOfQuestionsForTechnologySeniority = sizeString.toString(); 
    	}else{
    		totalAmountOfQuestionsForTechnologySeniority = "0";
    	}
    	
    	return SUCCESS;
    }


    private List<Question> retreiveQuestionsFromDb(String questionTechId, String questionSeniorityId) {
        List<Question> result = new ArrayList<Question>();
        result = questionsDao.executeQuery("FROM Question where technology.id = " + questionTechId + " AND seniority.id = " + questionSeniorityId + "");
        return result;

    }

    
	public String getTechnologyId() {
		return technologyId;
	}


	public void setTechnologyId(String technologyId) {
		this.technologyId = technologyId;
	}


	public String getSeniorityId() {
		return seniorityId;
	}


	public void setSeniorityId(String seniorityId) {
		this.seniorityId = seniorityId;
	}


	public GenericDaoImplementation getQuestionsDao() {
		return questionsDao;
	}


	public void setQuestionsDao(GenericDaoImplementation questionsDao) {
		this.questionsDao = questionsDao;
	}


	public String getTotalAmountOfQuestionsForTechnologySeniority() {
		return totalAmountOfQuestionsForTechnologySeniority;
	}


	public void setTotalAmountOfQuestionsForTechnologySeniority(
			String totalAmountOfQuestionsForTechnologySeniority) {
		this.totalAmountOfQuestionsForTechnologySeniority = totalAmountOfQuestionsForTechnologySeniority;
	}
}
