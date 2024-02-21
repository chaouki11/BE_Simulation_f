package enstabretagne.helloworld.Etudiant;

import enstabretagne.engine.InitData;

//extension du modèle d'initiation d'une entité pour en faire un modèle d'initialisation d'un étudiant
public class InitEtudiant extends InitData {
	public final Genre genre;
	public final Film filmPrefere;
	public InitEtudiant(String name,Genre genre, Film filmPrefere) {
		super(name);
		this.genre = genre;
		this.filmPrefere = filmPrefere;
	} 

	
}
