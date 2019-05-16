package modele;

public class Jeu {

	private Joueur joueur;
	private Terrain map;
	private String cheminMap = "../Map/Map100x100.csv";
	
	public Jeu() {
		this.joueur = new Joueur(10, 5, 88, 52, "Joueur", 300, 100);
		this.map = new Terrain(this.cheminMap); 
	}
	
	public boolean gererCollision(Personnage joueur, Terrain terrain) {
		
		int yJoueur = joueur.getY().getValue()/64;
		int xJoueur = joueur.getX().getValue()/64;
		
		if(!terrain.blockParCord(xJoueur, yJoueur).getCollision()) {
			return true;
		} else {
			return false;
		}
	}
	
	public Personnage getJoueur() {
		return this.joueur;
	}
	
	public Terrain getMap() {
		return this.map;
	}
	
	
	
}
