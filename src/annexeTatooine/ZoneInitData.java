package annexeTatooine;

import java.text.SimpleDateFormat;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

public class ZoneInitData {
	
	String nom;
	String typeSoin;
	SimpleDateFormat ouverture;
	SimpleDateFormat fermeture;
	int NombreAtelier;
	LogicalDuration dureeAtelier;
	int efficacite;
	LogicalDuration freqDefaillance;
	double stdDefaillance;
	LogicalDuration tempsRemiseMarche;
	String typeAttente;
	int tailleFile;
	int NombreAtelierFonctionnel;
	String typeFreq;
	
	
	public ZoneInitData(String nom,String typeSoin,String typeFreq,SimpleDateFormat ouverture,SimpleDateFormat fermeture,int NombreAtelier,LogicalDuration dureeAtelier,int efficacite,LogicalDuration freqDefaillance,double stdDefaillance,LogicalDuration tempsRemiseMarche,String typeAttente,int tailleFile ) {
		this.nom = nom;
	    this.typeSoin = typeSoin;
	    this.typeFreq=typeFreq;
	    this.ouverture = ouverture;
	    this.fermeture = fermeture;
	    this.NombreAtelier = NombreAtelier;
	    this.dureeAtelier = dureeAtelier;
	    this.efficacite = efficacite;
	    this.freqDefaillance = freqDefaillance;
	    this.stdDefaillance = stdDefaillance;
	    this.tempsRemiseMarche = tempsRemiseMarche;
	    this.typeAttente = typeAttente;
	    this.tailleFile = tailleFile;
	    this.NombreAtelierFonctionnel=NombreAtelier;
	     
	}
	
	public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTypeSoin() {
        return this.typeSoin;
    }

    public void setTypeSoin(String typeSoin) {
        this.typeSoin = typeSoin;
    }

    public SimpleDateFormat getOuverture() {
        return this.ouverture;
    }

    public void setOuverture(SimpleDateFormat ouverture) {
        this.ouverture = ouverture;
    }

    public SimpleDateFormat getFermeture() {
        return this.fermeture;
    }

    public void setFermeture(SimpleDateFormat fermeture) {
        this.fermeture = fermeture;
    }

    public int getNombreAtelier() {
        return this.NombreAtelier;
    }

    public void setNombreAtelier(int NombreAtelier) {
        this.NombreAtelier = NombreAtelier;
    }

    public LogicalDuration getDureeAtelier() {
        return this.dureeAtelier;
    }

    public void setDureeAtelier(LogicalDuration dureeAtelier) {
        this.dureeAtelier = dureeAtelier;
    }

    public int getEfficacite() {
        return this.efficacite;
    }

    public void setEfficacite(int efficacite) {
        this.efficacite = efficacite;
    }

    public LogicalDuration getFreqDefaillance() {
        return this.freqDefaillance;
    }

    public void setFreqDefaillance(LogicalDuration freqDefaillance) {
        this.freqDefaillance = freqDefaillance;
    }

    public double getStdDefaillance() {
        return this.stdDefaillance;
    }

    public void setStdDefaillance(double stdDefaillance) {
        this.stdDefaillance = stdDefaillance;
    }

    public LogicalDuration getTempsRemiseMarche() {
        return this.tempsRemiseMarche;
    }

    public void setTempsRemiseMarche(LogicalDuration tempsRemiseMarche) {
        this.tempsRemiseMarche = tempsRemiseMarche;
    }

    public String getTypeAttente() {
        return this.typeAttente;
    }

    public void setTypeAttente(String typeAttente) {
        this.typeAttente = typeAttente;
    }

    public int getTailleFile() {
        return this.tailleFile;
    }

    public void setTailleFile(int tailleFile) {
        this.tailleFile = tailleFile;
    }

    public int getNombreAtelierFonctionnel() {
        return this.NombreAtelierFonctionnel;
    }

    public void setNombreAtelierFonctionnel(int NombreAtelierFonctionnel) {
        this.NombreAtelierFonctionnel = NombreAtelierFonctionnel;
    }

}
