package enstabretagne.cureTatooine.client;

import enstabretagne.engine.InitData;

public class ClientInitData extends InitData{
	
	Cure cure;

	public ClientInitData(String name,Cure cure) {
		super(name);
		this.cure=cure;
		
	}

	public Cure getCure() {
		return cure;
	}

	public void setCure(Cure cure) {
		this.cure = cure;
	}
	
	

}
