package com.ar.mrm.entities;
// Generated Aug 9, 2010 2:30:11 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Option generated by hbm2java
 */
public class Option  implements java.io.Serializable {

     private Integer id;
     private Question question;
     private String description;
     private boolean correct;
     private Set optionChoseds = new HashSet(0);
     
    public Option() {
    }

	
    public Option(Question question, String description, boolean correct) {
        this.question = question;
        this.description = description;
        this.correct = correct;
    }
    public Option(Question question, String description, boolean correct, Set optionChoseds) {
       this.question = question;
       this.description = description;
       this.correct = correct;
       this.optionChoseds = optionChoseds;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Question getQuestion() {
        return this.question;
    }
    
    public void setQuestion(Question question) {
        this.question = question;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isCorrect() {
        return this.correct;
    }
    
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
    public Set getOptionChoseds() {
        return this.optionChoseds;
    }
    
    public void setOptionChoseds(Set optionChoseds) {
        this.optionChoseds = optionChoseds;
    }


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Option other = (Option) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

