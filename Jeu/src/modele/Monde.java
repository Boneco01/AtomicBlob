package modele;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Monde {

	private Joueur joueur;
	private ObservableList<Ennemi> ennemis;
	private Terrain map;
	private String cheminMap = "../Map/MapTestModeleV4.csv";
	
	public Monde() {
		this.map = new Terrain(this.cheminMap);
		this.joueur = new Joueur(10, 3, "Joueur", 500, 200, this);
		this.ennemis = FXCollections.observableList(new ArrayList<Ennemi>());
		this.ennemis.add(new Sentinelle(1, 60, 90, "Sentinelle1", 600, 100, this));
		this.ennemis.add(new Drone("drone1",500,200,this));
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
	
	public Terrain getMap() {
		return this.map;
	}
	
	
	
}