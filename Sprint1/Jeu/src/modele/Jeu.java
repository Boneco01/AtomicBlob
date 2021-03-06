package modele;

public class Jeu {

	private Joueur joueur;
	private Terrain map;
	private String cheminMap = "../Map/MapTestModele.csv";
	
	public Jeu() {
		this.joueur = new Joueur(10, 3, 88, 52, "Joueur", 300, 100);
		this.map = new Terrain(this.cheminMap);
	}
	
public boolean gererCollision(Personnage perso, Terrain terrain, int boxLargeur, int boxHauteur) {
        
        int xJoueur = (joueur.getX().getValue()+boxLargeur)/64;
        int yJoueur = (joueur.getY().getValue()+boxHauteur)/64;
        
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
