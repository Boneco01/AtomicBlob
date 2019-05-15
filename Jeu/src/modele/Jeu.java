package modele;

public class Jeu {

	private Joueur joueur;
	private Terrain map;
	private String cheminMap = "../Map/MapTestModele.csv";
	
	public Jeu() {
		this.joueur = new Joueur(10, 5, 88, 52, "Joueur", 300, 100);
		this.map = new Terrain(this.cheminMap);
	}
	
	public void GameLoop() {
		
		//while(this.joueur.getVie() != 0) {
			
			while (gererCollision(this.joueur, this.map)) {
				this.joueur.tombe();
			}
			
		//}
		
	}
	
	private boolean gererCollision(Personnage joueur, Terrain terrain) {
		
		int yJoueur = joueur.getY().getValue();
		int xJoueur = joueur.getX().getValue();
		
		if(!terrain.getMap().get(yJoueur/64).getCollision()) {
			return true;
		} else {
			this.joueur.setY(yJoueur-this.joueur.getHauteur()/2);
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
