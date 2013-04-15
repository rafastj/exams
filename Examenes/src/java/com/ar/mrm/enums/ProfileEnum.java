/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ar.mrm.enums;

/**
 *
 * @author Santiago.Arias
 */
public enum ProfileEnum {

    JAVA(1), QA(2), DESIGNER(3);
    private Integer id;

    private ProfileEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
