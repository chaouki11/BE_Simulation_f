package enstabretagne.engine;

import enstabretagne.base.logger.Logger;

/**
 * exemple d'un moniteur ultra simple
 * 
 */
//c'est lui qui porte le cadencement des scénarios via le plan d'expérience
public class SimplePlanMonitor {
	Plan plan;
	SimuEngine engine;

	public SimplePlanMonitor(Plan p) {
		//il porte la création du moteur
		this.engine = new SimuEngine();
		
		plan = p;
		plan.engine = engine;
	}

	//méthode principale simple
	public void run() {
		//1. on crée les scénarii
		plan.initScenarii();
		Logger.Information(this, "main", "Début du plan d'expérience");

		//on boucle sur les scénarios
		while (plan.hasNextScenario()) {
			//à chaque scénario on demande la création des entités de simulation correspondantes
			plan.nextScenario().creerEntitesSimulees();
			
			//la création des entités a été portée à la connaissance du moteur
			//noter que de nouvelles entités pourront être créées au cours de la simulation
			engine.initSimulation();
			//déclenchement de la boucle de simulation
			engine.simulate();
			//fin du run, nettoyage du moteur en vue de la possible prochaine exécution de scénario
			engine.terminate(false);//ce n'est pas la dernière exécution
		}
		engine.terminate(true);
		Logger.Information(null, "main", "Fin du plan d'expérience");
		Logger.Terminate();

	}

}
