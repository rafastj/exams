package com.ar.mrm.comparator;

import java.util.Comparator;

import com.ar.mrm.entities.Seniority;

public class SenioritiesComparator implements Comparator<Seniority> {

	public int compare(Seniority o1, Seniority o2) {
		Integer nameOrder = null;
		
		if((o1!=null)&&(o1.getDescription()!=null)){
			
			if((o2!=null)&&(o2.getDescription()!=null)){
				
				nameOrder = o1.getDescription().compareTo(o2.getDescription());
			}
			
		}
		
		if(nameOrder == 0){
			return 1;
		}
		
		return nameOrder;
	}

}
