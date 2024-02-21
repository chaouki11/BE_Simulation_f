package enstabretagne.cureTatooine.scenarios;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import annexeTatooine.Zone;
import annexeTatooine.ZoneInitData;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.logger.ToRecord;
import enstabretagne.base.math.MoreRandom;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.cureTatooine.client.Client;
import enstabretagne.cureTatooine.client.ClientInitData;
import enstabretagne.cureTatooine.client.Cure;
import enstabretagne.cureTatooine.scenarios.ScenarioTatooineInit.AffluenceParMois;
import enstabretagne.engine.EntiteSimulee;
import enstabretagne.engine.Scenario;
import enstabretagne.engine.ScenarioInitData;
import enstabretagne.engine.SimEvent;
import enstabretagne.engine.SimuEngine;
import enstabretagne.helloworld.Etudiant.EtudiantSimple;

@ToRecord(name = "Scenario")
public class ScenarioTatooineSimple extends Scenario {
	
	public static ArrayList<Zone> zones;
	
	List<Integer> nbAtelier;
	
	List<Double> proportion;
	
	public static Zone getZoneByName(String name) {
		for(Zone z: zones) {
			
			if(z.getZoneInitData().getNom()=="name") {
				return z;
			}
			
		}
		return null;
	}
	

	public ScenarioTatooineSimple(SimuEngine engine, ScenarioInitData init) {
		super(engine, init);
		
		
		
		//gestion de proportion
		this.nbAtelier= new ArrayList();
		this.proportion= new ArrayList();
		
		nbAtelier.add(3);
		nbAtelier.add(4);
		nbAtelier.add(5);
		nbAtelier.add(6);
		
		proportion.add(0.2);
		proportion.add(0.35);
		proportion.add(0.3);
		proportion.add(0.15);
		//
		
		
		//créer les zones
		Zone zone1=new Zone(new ZoneInitData("Zone des terres chaudes", "terres","planifié", new SimpleDateFormat("07:15"), new SimpleDateFormat("14:00"), 6, LogicalDuration.ofMinutes(20), 20, LogicalDuration.ofDay(61), 10, LogicalDuration.ofDay(3), "organisé", 10));
		Zone zone2=new Zone(new ZoneInitData("Zone des jets filiformes", "filiforme","libre", new SimpleDateFormat("10:00"), new SimpleDateFormat("13:00"), 4, LogicalDuration.ofMinutes(5), 30, LogicalDuration.ofDay(28), 4, LogicalDuration.ofDay(2), "non organisé", 10));
		Zone zone3=new Zone(new ZoneInitData("Zone des étuves", "étuve","planifié", new SimpleDateFormat("07:15"), new SimpleDateFormat("14:00"), 6, LogicalDuration.ofMinutes(15), 15, LogicalDuration.ofDay(21), 5, LogicalDuration.ofDay(3), "organisé", 6));
		Zone zone4=new Zone(new ZoneInitData("Zone Bains à jets", "bain","libre", new SimpleDateFormat("07:15"), new SimpleDateFormat("14:00"), 9, LogicalDuration.ofMinutes(20), 10, LogicalDuration.ofDay(35), 4, LogicalDuration.ofDay(2), "organisé", 15));
		Zone zone5=new Zone(new ZoneInitData("Zone des douches", "douche","libre", new SimpleDateFormat("07:15"), new SimpleDateFormat("14:00"), 8, LogicalDuration.ofMinutes(10), 10, LogicalDuration.ofDay(49), 2, LogicalDuration.ofDay(2), "non organisé", 8));
		Zone zone6=new Zone(new ZoneInitData("Zone des soins du visage", "visage","libre", new SimpleDateFormat("07:15"), new SimpleDateFormat("14:00"), 8, LogicalDuration.ofMinutes(10), 5, LogicalDuration.ofDay(365), 40, LogicalDuration.ofDay(1), "non organisé", 5));
		
		zones=new ArrayList<Zone>();
		zones.add(zone1);
		zones.add(zone2);
		zones.add(zone3);
		zones.add(zone4);
		zones.add(zone5);
		zones.add(zone6);
		//
		
	}

	@Override
	public void creerEntitesSimulees() {
		ScenarioTatooineInit scenario = (ScenarioTatooineInit) getInit();
		nbClientMax = scenario.capaciteMaxClientInstitut;

		//On prépare les changements d'affluence chaque mois
		for(AffluenceParMois a : scenario.affluenceParMois) {
			Post(new ChangementTauxCreationMois(a.d,a.tauxMax));
			for(int i=0;i<(a.tauxMax*nbClientMax);i++) {
				
				//faut ajouter logique repartition nombre de cure
				
				
				for(int x=0;x<this.nbAtelier.size(); x++) {
					
					
					
					double tot=this.proportion.get(x);
					tot=(int)tot*a.tauxMax*nbClientMax;
					MoreRandom mr= new MoreRandom();
					for(int e=0; e<tot;e++) {
						
						
						
						//generate  this.nbAtelier[x] atelier
						ArrayList<Zone> zonesAfaire=new ArrayList();
						
						ArrayList<Zone> inteAzone=new ArrayList();
						for(Zone p:this.zones) {
							inteAzone.add(p);
							
						}
						
						
						for(int h=0;h<this.nbAtelier.get(e);h++) {
							int randomIndex = mr.nextInt(inteAzone.size());
							zonesAfaire.add(inteAzone.get(randomIndex));
							inteAzone.remove(randomIndex);
							
						}
				        
						Post(new CreerClient(a.d,zonesAfaire));	//add zonesAfaire to arg
					}
					
				}
				
			}
		}
	}
	
	int nbClientMax;
	int nbClientMaxMois;
	
	@ToRecord(name = "NbClientMaxMois")
	public int getNbClientMaxMois() {
		return nbClientMaxMois;
	}
	
	int nbClientEnCours;
	
	@ToRecord(name = "NbClientEncours")
	public int getNbClientEnCours() {
		return nbClientEnCours;
	}
	
	
	public void setNbClientEnCours(int nbClientEnCours) {
		this.nbClientEnCours = nbClientEnCours;
	}
	
	
	//normalement ici il faut créer un process assez élaboré avec le parcours de soin
	protected void creerClientAleatoire(ArrayList<Zone> zones) {
		
		
			List<String> ateliers=new ArrayList();
			
			for (Zone zone : zones) {
	            // Add the name of each zone to the ateliers list
	            ateliers.add(zone.getZoneInitData().getNom());
	        }
			
			
			
			ClientInitData iniClient = new ClientInitData("C"+nbClientEnCours++,new Cure(ateliers)) ; 
			Client c = new Client(getEngine(), iniClient);
			c.requestInit();
			
			System.out.println("TESTESTESTEST");
			System.out.println(c.getName()+c.getCid().getCure());
			
			
			//On sait que la cure ne dure que 3 semaines donc départ du client dans 3 semaines
			
			//afficher client crée
			
			//Logger.Data("nom client : "+c.getName()+"cure: "+c.getCid().getCure());
			//System.out.println("nom client : "+c.getName()+"cure: "+c.getCid().getCure());
			
			
			Post(new DepartClient(Now().add(LogicalDuration.ofDay(21)),c));
	}

	@Override
	protected void init() {
		super.init();
		
				
		Logger.Information(this, "scenario.init", getName() + " s'initialise.");
		List<EntiteSimulee> l = recherche(e->{return e instanceof Client ;});
		
		for(EntiteSimulee e: l) {
			e.requestInit();
		}
		

		//création et démarrage du processus d'enregistrement des données
		Post(new RecordProcess(Now()));

	}

	//
	public class CreerClient extends SimEvent {
		
		public ArrayList<Zone> zones;

		public CreerClient(LogicalDateTime d,ArrayList<Zone> zones) {
			super(d);
			for(Zone g:zones) {
				this.zones.add(g);
			}
		}
		
		@Override
		public void process() {
			
			creerClientAleatoire(this.zones);
			
		}
		
	}
	
	public class DepartClient extends SimEvent {
		public DepartClient(LogicalDateTime d,Client c) {
			super(d);
		}

		@Override
		public void process() {
			setNbClientEnCours(nbClientEnCours-1);
		}
	}
	
	public class ChangementTauxCreationMois extends SimEvent {

		double tauxPrevu;
		public ChangementTauxCreationMois(LogicalDateTime t,double tauxPrevu) {
			super(t);
			this.tauxPrevu = tauxPrevu;
		}
		@Override
		public void process() {
			nbClientMaxMois = (int) (tauxPrevu*nbClientMax);
			Logger.Information(entitePorteuseEvenement, "Changement Taux", "nouveau taux = "+tauxPrevu+" nbClientMaxMois="+nbClientMaxMois);
			
		}
	}
	
	//post unique de fin des entrées
	public class FinDesEntrees3SemainesAvant extends SimEvent {
		public FinDesEntrees3SemainesAvant(LogicalDateTime d) {
			super(d);
		}

		@Override
		public void process() {
			nbClientMaxMois = 0;
		}
	}
	
	//repost régulier du process d'enregistrement
	public class RecordProcess extends SimEvent {
		
		public RecordProcess(LogicalDateTime d) {
			super(d);
		}

		@Override
		public void process() {
			Logger.Data(entitePorteuseEvenement);
			rescheduleAt(Now().add(LogicalDuration.ofDay(5)));
			entitePorteuseEvenement.Post(this);
		}
	}

}
