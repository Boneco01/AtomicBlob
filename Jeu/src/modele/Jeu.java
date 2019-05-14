package modele;

public class Jeu {

	private Personnage joueur;
	private Terrain map;
	//Ubuntu
	private String cheminMap = "../Map/MapTestModele.csv";		
	//Windows
	//private String cheminMap = "C:\\Users\\Paul\\Documents\\DUT\\S2\\Projet\\AtomicBlob\\Map\\MapTestModele.csv";
	
	public Jeu() {
		this.joueur = new Joueur(10, 1, 32, "Joueur", 0, 0);
		this.map = new Terrain(this.cheminMap);
	}
	
	public void GameLoop() {
		
		while(this.joueur.getVie() != 0) {
			
			while(gererCollision(this.joueur, this.map, 0, 1)) {
				this.joueur.tombe();
			}
			
		}
		
	}
	
	private boolean gererCollision(Personnage joueur, Terrain terrain, int x, int y) {
		
		if(terrain.getMap().get(joueur.getY()+y).get(joueur.getX()+x).getCollision()) {
			return false;
		} else {
			return true;
		}
		
	}
	
	
	
}
