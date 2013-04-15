package com.ar.mrm.entities;
// Generated Aug 9, 2010 2:30:11 AM by Hibernate Tools 3.2.1.GA



/**
 * OptionChosed generated by hbm2java
 */
public class OptionChosed  implements java.io.Serializable {


     private Integer id;
     private QuestionAnswered questionAnswered;
     private Option option;
     private boolean checked;

    public OptionChosed() {
    }

    public OptionChosed(QuestionAnswered questionAnswered, Option option, boolean checked) {
       this.questionAnswered = questionAnswered;
       this.option = option;
       this.checked = checked;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public QuestionAnswered getQuestionAnswered() {
        return this.questionAnswered;
    }
    
    public void setQuestionAnswered(QuestionAnswered questionAnswered) {
        this.questionAnswered = questionAnswered;
    }
    public Option getOption() {
        return this.option;
    }
    
    public void setOption(Option option) {
        this.option = option;
    }
    public boolean isChecked() {
        return this.checked;
    }
    
    public void setChecked(boolean checked) {
        this.checked = checked;
    }




}


