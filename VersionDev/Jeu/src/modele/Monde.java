package modele;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Monde {

	private Joueur joueur;
	private ObservableList<Ennemi> ennemis;
	private Terrain map;
	private String cheminMap = "../Map/TileMap/MapFinaleAllegee.csv";
	private int timerSpawn;
	
	public Monde() {
		this.map = new Terrain(this.cheminMap);
		this.joueur = new Joueur(10, 3, "Joueur", 3200, 100, this);
		this.ennemis = FXCollections.observableList(new ArrayList<Ennemi>());
		//this.ennemis.add(new Sentinelle(1, 60, 90, "Sentinelle1", 2800, 200, this)); //Si vous voulez placer un ennemi par vous même
		//this.ennemis.add(new Drone("drone1",2800,1000,this));
		this.timerSpawn = 2000;
	}
	
	public boolean gererCollision(Terrain terrain, int boxLargeur, int boxHauteur, Personnage personnage) {
        
		//64 = nb de pixels d'un bloc. Une constante aurait été plus MVC
        int xPersonnage = (personnage.getXProperty().getValue()+boxLargeur)/64;
        int yPersonnage = (personnage.getYProperty().getValue()+boxHauteur)/64;
        
        if(!terrain.blockParCord(xPersonnage, yPersonnage).getCollision()) {
            return true;
        } 
        return false;
        
    }
	
	public Joueur getJoueur() {
		return this.joueur;
	}
	
	public ObservableList<Ennemi> getEnnemis() {
		return this.ennemis;	
	}
	
	public void checkEnnemis() {
		for(int i=0;i<this.ennemis.size();i++) {
			if(this.ennemis.get(i).getVie()<=0) {
				this.ennemis.remove(this.ennemis.get(i));
			}
		}
	}
	
	public void spawnEnnemi() {
		if(this.timerSpawn==0) {
			Ennemi e;
			int aleaEnnemi = (int) (Math.random()*10);
			int aleaSpawnX = (int) (Math.random()*5600)+400;
			
			if(aleaEnnemi<7) {
				e = new Sentinelle(1, 60, 90, "Sentinelle1", aleaSpawnX, 200, this); //y=100 d�cale la hitbox de l'ennemi d'un bloc en dessous ??
			} else {
				e = new Drone("drone1",aleaSpawnX,1000,this); // A remplacer par un drone
			}
			
			this.ennemis.add(e);
			this.timerSpawn=2000;
		}
		this.timerSpawn--;
	}
	
	public Terrain getMap() {
		return this.map;
	}
	
	
	
}