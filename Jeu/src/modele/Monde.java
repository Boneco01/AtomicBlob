package modele;

public class Monde {

	private Joueur joueur;
	private Terrain map;
	private String cheminMap = "../Map/MapTestModele.csv";
	
	public Monde() {
		this.map = new Terrain(this.cheminMap);
		this.joueur = new Joueur(10, 3, 88, 52, "Joueur", 300, 100, this);
	}
	
public boolean gererCollision(Personnage perso, Terrain terrain, int boxLargeur, int boxHauteur) {
        
        int xJoueur = (joueur.getXProperty().getValue()+boxLargeur)/64;
        int yJoueur = (joueur.getYProperty().getValue()+boxHauteur)/64;
        
        if(!terrain.blockParCord(xJoueur, yJoueur).getCollision()) {
            return true;
        } else {
            return false;
        }
    }
	
	public Joueur getJoueur() {
		return this.joueur;
	}
	
	public Terrain getMap() {
		return this.map;
	}
	
	
	
}