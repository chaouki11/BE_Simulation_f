package enstabretagne.helloworld.scenario;

import java.util.List;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.engine.EntiteSimulee;
import enstabretagne.engine.Scenario;
import enstabretagne.engine.SimEvent;
import enstabretagne.engine.SimuEngine;
import enstabretagne.helloworld.Etudiant.EtudiantSimple;
import enstabretagne.helloworld.Etudiant.Film;
import enstabretagne.helloworld.Etudiant.Genre;
import enstabretagne.helloworld.Etudiant.InitEtudiant;

//spécialisation du concept de scénario
public class ScenarioENSTA extends Scenario{

	public ScenarioENSTA(SimuEngine engine, ScenarioEnstaInitData init) {
		super(engine, init);
		nbEtudiants = init.getEtudiants();
		Logger.Information(this, "constructeur", "nbetudiants=" + getEtudiants());
	}

	int totalEntities;
	@Override
	public void creerEntitesSimulees() {
		Logger.Information(this, "creerentite", "nbetudiants=" + getEtudiants());
		for(int i=0;i<getEtudiants();i++) {
			creerRandomEtudiantSimple(this,i);
			totalEntities++;
		}
		
	}
	
	//exemple de création d'un étudiant aléatoire
	protected static EtudiantSimple creerRandomEtudiantSimple(Scenario s, int i) {
		//génération du genre de manière aléatoire
		Genre randomGenre;
		double d = s.getRandomGenerator().nextDouble();
		//on sait qu'il n'y a pas plus de 3 genres
		//on a supposé qu'il était équiprobable d'être d'un genre ou un autre
		if(d<1/3) {
			randomGenre = Genre.Homme;
		}else if(d>1/3 && d<2/3){
			randomGenre = Genre.Femme;				
		}
		else {
			randomGenre = Genre.Autre;
		}
		
		//génération du film préféré
		//cette fois on part du principe que la liste des films est extensible
		//on génére donc un nombre entier aléatoire entre 0 et le nombre de films listés
		int randomFilmIndex = s.getRandomGenerator().nextInt(0, Film.values().length-1);
		//je choisi le film à partir de l'index généré aléatoirement
		Film randomFilm = Film.values()[randomFilmIndex];

		return new EtudiantSimple(s.getEngine(), new InitEtudiant("Et" + i,randomGenre,randomFilm));
		
	}
	
	int nbEtudiants;
	public int getEtudiants() {
		return nbEtudiants;
	}

	@Override
	public void init() {
		super.init();
		//on initialise les entités
		//on peut avoir une stratégie d'initialisation initiale complexe
		//ici elle est simple on initialise tous les étudiants sans distinction
		//mais on pourrait initialiser selon le genre d'abord les hommes, puis les femmes puis les autres
		
		//on fait une requête au moteur
		List<EntiteSimulee> l = recherche(e->{return e instanceof EtudiantSimple ;});

		//on demande l'initialisation de ces entités
		for(EntiteSimulee e: l) {
			e.requestInit();
		}
		
		//on décide de faire une création différée des étudiants
		//ceci est à but pédagogique: on n'est pas obligé de créer desuite les entités
		Post(new CreerEtudiant(getEngine().SimulationDate().add(LogicalDuration.ofMinutes(8)), "Olivier"));
	}
	
	public class CreerEtudiant extends SimEvent {
		
		String nom;

		public CreerEtudiant(LogicalDateTime d,String nom) {
			super(d);
			this.nom = nom;
		}

		//Exemple typique: 
		// en tant qu'entité, le scénario crée des entités filles
		//c'est au scénario de faire le requestInit()
		@Override
		public void process() {
			EtudiantSimple es = creerRandomEtudiantSimple(ScenarioENSTA.this, ++totalEntities);
			es.requestInit();
		}
		
	}

	@Override
	public void terminate() {
		super.terminate();
		totalEntities=0;
		nbEtudiants=0;
	}

}
