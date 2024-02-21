package enstabretagne.helloworld.Etudiant;

import java.util.List;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.engine.EntiteSimulee;
import enstabretagne.engine.SimEvent;

//cr�ation d'un �v�nement Bonjour
public class Bonjour extends SimEvent {

	private String nom;

	public Bonjour(LogicalDateTime d, String nom) {
		super(d);
		this.nom = nom;
	}

	//m�thode utilitaire qui permet d'allerger l'impl�mentation de la m�thode process
	//et contribue � sa lisibilit�
	public String amiString(List<EntiteSimulee> amis, int n) {
		if (amis.size() > 0) {
			if (amis.size() == 1) {
				var ie = (InitEtudiant) amis.get(0).getInit();
				String amiString = "";
				switch (ie.genre) {
				case Homme: {
					amiString = "ami";
					break;
				}
				case Femme: {
					amiString = "amie";
					break;
				}
				case Autre: {
					amiString = "ami.e";
					break;
				}
				}

				return "mon " + amiString + " est " + ((EtudiantSimple) amis.get(0)).getName();
			} else {
				String amisString = "";
				for (EntiteSimulee e : amis) {
					amisString = amisString + ((EtudiantSimple) e).getName() + " ";
				}
				return "mes amis sont : " + amisString;

			}
		} else {
			return "je n'ai pas d'amis parmi les " + n + " �tudiants";
		}

	}

	public void process() {
		Logger.Detail(entitePorteuseEvenement, "bonjour.Process",
				"Bonjour de la part de " + nom + " � " + getDateOccurence());
		Logger.Detail(entitePorteuseEvenement, "bonjour.Process",
				"mon film pr�f�r� est " + ((InitEtudiant) entitePorteuseEvenement.getInit()).filmPrefere);
		List<EntiteSimulee> allStudents = entitePorteuseEvenement.recherche(e -> ((e instanceof EtudiantSimple)));

		List<EntiteSimulee> amis = entitePorteuseEvenement.recherche(e -> ((e instanceof EtudiantSimple)
				&& (e != entitePorteuseEvenement) 
				&& ((InitEtudiant) e.getInit()).filmPrefere.equals(((InitEtudiant) entitePorteuseEvenement.getInit()).filmPrefere)));

		for(EntiteSimulee e : amis) {
			double montantADemander = entitePorteuseEvenement.getRandomGenerator().nextDouble() * 100;
			
			Logger.Detail(entitePorteuseEvenement, "bonjour.Process", "J'ai besoin d'argent. Prête moi "+montantADemander+"€ s'il te plait.", null);
			EtudiantSimple es = (EtudiantSimple) e;
			double montantRecu = es.PreteMoiArgent(montantADemander);
			if(montantRecu == 0 )
				Logger.Detail(entitePorteuseEvenement, "bonjour.Process", "Tu es radin !", null);
			else
				Logger.Detail(entitePorteuseEvenement, "bonjour.Process", "Tu es vraiment un ami car tu m'as donné "+montantRecu+"!", null);
		}
		Logger.Detail(entitePorteuseEvenement, "bonjour.Process", amiString(amis, allStudents.size()));

		//exemple d'introduction d'un al�a temporel
		double alea = entitePorteuseEvenement.getRandomGenerator().nextDouble() * 3 * 60;

		//la prochaine occurence de l'�v�nement est donc al�atoire
		this.rescheduleAt(getDateOccurence().add(LogicalDuration.ofMinutes(5).add(LogicalDuration.ofSeconds(alea))));
		entitePorteuseEvenement.Post(this);
	}

}
