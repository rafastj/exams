package com.ar.mrm.entities;
// Generated Aug 9, 2010 2:30:11 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Test generated by hbm2java
 */
public class Test  implements java.io.Serializable {


     private Integer id;
     private Applicant applicant;
     private TestModel testModel;
     private Double score;
     private Set questions = new HashSet(0);
     private Set questionAnswereds = new HashSet(0);
     private Set<TechScore> techScores = new HashSet<TechScore>(0);

    public Test() {
    }

	
    public Test(Applicant applicant, TestModel testModel) {
        this.applicant = applicant;
        this.testModel = testModel;
    }
    public Test(Applicant applicant, TestModel testModel, Double score, Set questions, Set questionAnswereds, Set<TechScore> techScores) {
       this.applicant = applicant;
       this.testModel = testModel;
       this.score = score;
       this.questions = questions;
       this.questionAnswereds = questionAnswereds;
       this.techScores = techScores;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Applicant getApplicant() {
        return this.applicant;
    }
    
    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }
    public TestModel getTestModel() {
        return this.testModel;
    }
    
    public void setTestModel(TestModel testModel) {
        this.testModel = testModel;
    }
    public Double getScore() {
        return this.score;
    }
    
    public void setScore(Double score) {
        this.score = score;
    }
    public Set<Question> getQuestions() {
        return this.questions;
    }
    
    public void setQuestions(Set questions) {
        this.questions = questions;
    }
    public Set<QuestionAnswered> getQuestionAnswereds() {
        return this.questionAnswereds;
    }
    
    public void setQuestionAnswereds(Set questionAnswereds) {
        this.questionAnswereds = questionAnswereds;
    }
    public Set<TechScore> getTechScores() {
        return this.techScores;
    }
    
    public void setTechScores(Set<TechScore> techScores) {
        this.techScores = techScores;
    }




}


