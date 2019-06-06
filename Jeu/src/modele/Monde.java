package modele;


public class Monde {

	private Joueur joueur;
	private Sentinelle sentinelle;
	private Terrain map;
	private String cheminMap = "../Map/MapTestModeleV4.csv";
	
	public Monde() {
		this.joueur = new Joueur(10, 3, 44, 26, "Joueur", 300, 100, this);
		this.sentinelle=new Sentinelle(10, 1, 60, 90, "Sentinelle1", 300, 100, this);
		this.map = new Terrain(this.cheminMap);
	}
	
	public boolean gererCollision(Terrain terrain, int boxLargeur, int boxHauteur, Personnage personnage) {
        
        int xPersonnage = (personnage.getXProperty().getValue()+boxLargeur)/64;
        int yPersonnage = (personnage.getYProperty().getValue()+boxHauteur)/64;
        
        if(!terrain.blockParCord(xPersonnage, yPersonnage).getCollision()) {
            return true;
        } else {
            return false;
        }
    }
	
	public Joueur getJoueur() {
		return this.joueur;
	}
	
	public Sentinelle getSentinelle() { //TODO changer par un ennemi
		return this.sentinelle;	
	}
	
	public Terrain getMap() {
		return this.map;
	}
	
	
	
}