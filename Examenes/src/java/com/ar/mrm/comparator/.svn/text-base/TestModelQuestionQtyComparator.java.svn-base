package com.ar.mrm.comparator;

import java.util.Comparator;

import com.ar.mrm.entities.TestModelQuestionqty;

public class TestModelQuestionQtyComparator implements Comparator<TestModelQuestionqty>{

	public int compare(TestModelQuestionqty o1, TestModelQuestionqty o2) {
		Integer nameOrder = null;
		
		if((o1.getTechnology()!=null)&&(o1.getTechnology().getDescription()!=null)){
			
			if((o2.getTechnology()!=null)&&(o2.getTechnology().getDescription()!=null)){
				
				nameOrder = o1.getTechnology().getDescription().compareTo(o2.getTechnology().getDescription());
			}
			
		}
		
		if(nameOrder == 0){
			return 1;
		}
		
		return nameOrder;
	}

}
