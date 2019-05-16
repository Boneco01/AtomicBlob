package modele;

public class Jeu {

	private Joueur joueur;
	private Terrain map;
	private String cheminMap = "../Map/Map100x100.csv";
	
	public Jeu() {
		this.joueur = new Joueur(10, 5, 88, 52, "Joueur", 300, 100);
		this.map = new Terrain(this.cheminMap);
	}
	
public boolean gererCollision(Personnage perso, Terrain terrain, double boxLargeur, double boxHauteur) { //TODO box relative au sprite
        
        int xJoueur = (int)(joueur.getX().getValue()+boxLargeur)/64;
        int yJoueur = (int)(joueur.getY().getValue()+boxHauteur)/64;
        
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
