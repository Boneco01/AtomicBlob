package modele;

import modele.Items.ItemVide;

public class Monde {

	private Joueur joueur;
	private Terrain map;
	private String cheminMap = "../Map/MapTestModeleV4.csv";
	
	public Monde() {
		this.map = new Terrain(this.cheminMap);
		this.joueur = new Joueur(10, 3, 44, 26, "Joueur", 300, 100, this, new ItemVide());
		
	}
	
	public boolean gererCollision(Terrain terrain, int boxLargeur, int boxHauteur) {
        
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