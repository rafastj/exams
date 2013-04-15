package com.ar.actions;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;

import com.ar.mrm.dao.GenericDaoImplementation;
import com.ar.mrm.entities.Option;
import com.ar.mrm.entities.Question;
import com.ar.mrm.entities.Seniority;
import com.ar.mrm.entities.Technology;
import com.opensymphony.xwork2.ActionSupport;

public class ShowModificarQuestionAction extends ActionSupport implements
		SessionAware, RequestAware {

	private Logger logger = Logger.getLogger(ShowModificarQuestionAction.class);
	
	private Map request;
	private String id_q;
	private String questionDescription;
	private List<Seniority> seniorities;
	private List<Technology> technologies;

	private Integer idSeniorityAnterior;
	private Integer idTechnologyAnterior;

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

	private String firstOption;
	private String secondOption;
	private String thirdOption;
	private String fourthOption;
	private String fifthOption;
	private String sixthOption;
	private String seventhOption;
	private String eightOption;
	private String ninethOption;
	private String tenOption;

	private boolean firstOptionValue, secondOptionValue, thirdOptionValue,
			fourthOptionValue, fifthOptionValue, sixthOptionValue,
			seventhOptionValue, eightOptionValue, ninethOptionValue,
			tenOptionValue;

	private Map session;

	private GenericDaoImplementation questionDao = new GenericDaoImplementation(Question.class);
	private GenericDaoImplementation seniorityDao = new GenericDaoImplementation(Seniority.class);
	private GenericDaoImplementation technologyDao = new GenericDaoImplementation(Technology.class);

	@Override
	public String execute() throws Exception {

    	try{
    		seniorities = seniorityDao.getAll();
    	}catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while reading seniorities", e);
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("ERROR - While reading a seniorities", e);
			e.printStackTrace();
		}
    	try {
    		technologies = technologyDao.getAll();
		} catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while reading technologies", e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("ERROR - While reading a technologies", e);
			e.printStackTrace();
		}

		Question question = null;
		try {
			Integer questionId = Integer.parseInt(id_q);
			question = (Question) questionDao.read(questionId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (HibernateException e) {
			logger.error("ERROR - Hibernate Exception while reading a question", e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("ERROR - While reading a question", e);
			e.printStackTrace();
		}

		questionDescription = question.getDescription();
		idSeniorityAnterior = question.getSeniority().getId();
		idTechnologyAnterior = question.getTechnology().getId();

		Set<Option> options = question.getOptions();
		int count = 1;

		for (Option option : options) {
			switch (count) {
			case 1:
				firstOptionId = option.getId();
				firstOption = option.getDescription();
				firstOptionValue = option.isCorrect();
				break;

			case 2:
				secondOptionId = option.getId();
				secondOption = option.getDescription();
				secondOptionValue = option.isCorrect();
				break;
				
			case 3:
				thirdOptionId = option.getId();
				thirdOption = option.getDescription();
				thirdOptionValue = option.isCorrect();
				break;
				
			case 4:
				fourthOptionId = option.getId();
				fourthOption = option.getDescription();
				fourthOptionValue = option.isCorrect();
				break;
				
			case 5:
				fifthOptionId = option.getId();
				fifthOption = option.getDescription();
				fifthOptionValue = option.isCorrect();
				break;
				
			case 6:
				sixthOptionId = option.getId();
				sixthOption = option.getDescription();
				sixthOptionValue = option.isCorrect();
				break;
				
			case 7:
				seventhOptionId = option.getId();
				seventhOption = option.getDescription();
				seventhOptionValue = option.isCorrect();
				break;
				
			case 8:
				eightOptionId = option.getId();
				eightOption = option.getDescription();
				eightOptionValue = option.isCorrect();
				break;
				
			case 9:
				ninethOptionId = option.getId();
				ninethOption = option.getDescription();
				ninethOptionValue = option.isCorrect();
				break;
				
			case 10:
				tenthOptionId = option.getId();
				tenOption = option.getDescription();
				tenOptionValue = option.isCorrect();
				break;
			}
			count++;
		}

		if (question == null) {
			logger.error("Question not found!");
			session.put("sucess", "Question not found!");
			return SUCCESS;
		}
		logger.debug(getId_q());
		return SUCCESS;
	}

	public Map getSession() {
		return session;
	}

	public String getId_q() {
		return id_q;
	}

	public void setId_q(String idQ) {
		id_q = idQ;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public GenericDaoImplementation getQuestionDao() {
		return questionDao;
	}

	public void setQuestionDao(GenericDaoImplementation questionDao) {
		this.questionDao = questionDao;
	}

	public String getQuestionDescription() {
		return questionDescription;
	}

	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
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

	public String getNinethOption() {
		return ninethOption;
	}

	public void setNinethOption(String ninethOption) {
		this.ninethOption = ninethOption;
	}

	public Integer getTenthOptionId() {
		return tenthOptionId;
	}

	public void setTenthOptionId(Integer tenthOptionId) {
		this.tenthOptionId = tenthOptionId;
	}

	public String getTenOption() {
		return tenOption;
	}

	public void setTenOption(String tenOption) {
		this.tenOption = tenOption;
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

	public void setRequest(Map req) {
		request = req;
	}

	public List<Seniority> getSeniorities() {
		return seniorities;
	}

	public void setSeniorities(List<Seniority> seniorities) {
		this.seniorities = seniorities;
	}

	public List<Technology> getTechnologies() {
		return technologies;
	}

	public void setTechnologies(List<Technology> technologies) {
		this.technologies = technologies;
	}

	public Integer getIdSeniorityAnterior() {
		return idSeniorityAnterior;
	}

	public void setIdSeniorityAnterior(Integer idSeniorityAnterior) {
		this.idSeniorityAnterior = idSeniorityAnterior;
	}

	public Integer getIdTechnologyAnterior() {
		return idTechnologyAnterior;
	}

	public void setIdTechnologyAnterior(Integer idTechnologyAnterior) {
		this.idTechnologyAnterior = idTechnologyAnterior;
	}

}