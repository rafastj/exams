/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ar.mrm;


import com.ar.mrm.entities.Applicant;
import com.ar.mrm.entities.Profile;
import com.ar.mrm.entities.Seniority;

/**
 *
 * @author santiago.arias
 */
public class TestDao {

    private Profile profile;
    private Seniority seniority;
    private Applicant applicant;

    public TestDao(Profile profile, Seniority seniority,Applicant applicant) {
        this.profile = profile;
        this.seniority = seniority;
        this.applicant = applicant;
    }

    public void createTest() {
      
    }

    private void createDesignerEvaluation(){

    }

    private void createQAEvaluation(){

    }


    private void createJAVAEvaluation(){

    }
    

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Seniority getSeniority() {
        return seniority;
    }

    public void setSeniority(Seniority seniority) {
        this.seniority = seniority;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }
    


}
