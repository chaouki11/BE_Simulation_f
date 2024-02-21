package enstabretagne.cureTatooine;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import annexeTatooine.Zone;
import annexeTatooine.ZoneInitData;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.engine.SimplePlanMonitor;

public class CureTatooine {

	public static void main(String[] args) {
		Logger.load();
		
		
		
		LogicalDateTime start = new LogicalDateTime("01/01/2024 00:00");
		LogicalDateTime end = new LogicalDateTime("31/09/2024 00:00");

		PlanTatooine plan = new PlanTatooine(1, start, end);

		SimplePlanMonitor spm = new SimplePlanMonitor(plan);
		spm.run();
		
	}

}
