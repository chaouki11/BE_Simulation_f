package enstabretagne.cureTatooine.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import annexeTatooine.Zone;
import enstabretagne.cureTatooine.scenarios.ScenarioTatooineSimple;

public class Cure {
	
	public Map<String,Integer> ateliers;//zone,point
	
	public Cure(List<String> ateliersName) {
		
		//initialiser la progrssison des ateliers(points) à zéro
		this.ateliers=new HashMap();
		for(String x:ateliersName) {
			this.ateliers.put(x, 0);
	
			
		}
		
	}

}
