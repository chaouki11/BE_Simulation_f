package enstabretagne.cureTatooine.client;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.logger.ToRecord;
import enstabretagne.engine.EntiteSimulee;
import enstabretagne.engine.InitData;
import enstabretagne.engine.SimuEngine;

@ToRecord(name="Client")
public class Client extends EntiteSimulee{
	
	ClientInitData cid;

	public Client(SimuEngine engine, InitData ini) {
		super(engine, ini);
		this.cid=(ClientInitData) ini;
		
	}
	
	@ToRecord(name="Points de soins")
	double getPointsTotalEnCours() {
		return 0;
	}
	
	@Override
	public String toString() {
		return "curiste name: "+getName()+"/ liste atelier"+this.cid.getCure();
	}

	@Override
	protected void init() {
		super.init();
		Logger.Information(this, "init", "Initialisation en cours");
	}

	public ClientInitData getCid() {
		return cid;
	}

	public void setCid(ClientInitData cid) {
		this.cid = cid;
	}
	
	
}
