package com.ar.mrm.dto;

public class TestModelQuestionQtyDTO {
	private Integer id;
	private Integer seniorityId;
	private String seniorityDescription;
	private Integer technologyId;
	private String technologyDescription;
	//private Integer qtyQuestions;
	private String qtyQuestions;
	private Integer totalAmountOfQuestions;
	private Boolean persist = false;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSeniorityId() {
		return seniorityId;
	}
	public void setSeniorityId(Integer seniorityId) {
		this.seniorityId = seniorityId;
	}
	public Integer getTechnologyId() {
		return technologyId;
	}
	public void setTechnologyId(Integer technologyId) {
		this.technologyId = technologyId;
	}
	public String getSeniorityDescription() {
		return seniorityDescription;
	}
	public void setSeniorityDescription(String seniorityDescription) {
		this.seniorityDescription = seniorityDescription;
	}
	public String getTechnologyDescription() {
		return technologyDescription;
	}
	public void setTechnologyDescription(String technologyDescription) {
		this.technologyDescription = technologyDescription;
	}
	/*
	public Integer getQtyQuestions() {
		return qtyQuestions;
	}
	public void setQtyQuestions(Integer qtyQuestions) {
		this.qtyQuestions = qtyQuestions;
	}
	*/
	
	
	public Boolean getPersist() {
		return persist;
	}
	public String getQtyQuestions() {
		return qtyQuestions;
	}
	public void setQtyQuestions(String qtyQuestions) {
		this.qtyQuestions = qtyQuestions;
	}
	public void setPersist(Boolean persist) {
		this.persist = persist;
	}
	
	public Integer getTotalAmountOfQuestions() {
		return totalAmountOfQuestions;
	}
	public void setTotalAmountOfQuestions(Integer totalAmountOfQuestions) {
		this.totalAmountOfQuestions = totalAmountOfQuestions;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((seniorityId == null) ? 0 : seniorityId.hashCode());
		result = prime * result
				+ ((technologyId == null) ? 0 : technologyId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestModelQuestionQtyDTO other = (TestModelQuestionQtyDTO) obj;
		if (seniorityId == null) {
			if (other.seniorityId != null)
				return false;
		} else if (!seniorityId.equals(other.seniorityId))
			return false;
		if (technologyId == null) {
			if (other.technologyId != null)
				return false;
		} else if (!technologyId.equals(other.technologyId))
			return false;
		return true;
	}
	
	
}
