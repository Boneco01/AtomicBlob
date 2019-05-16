package controleur;

import javafx.scene.shape.Circle;
import modele.Personnage;

public class Sprite {
	private Personnage personnage;
	//private ImageView spriteDuPerso;
	private Circle cercle;  //a remplacer plus tard par un sprite
	
	public Sprite(Personnage personnage) {
		this.personnage=personnage;
		//this.spriteDuPerso = new ImageView(new Image("file:../Sprites/Joueur/JoueurBase/JoueurBase.gif"));
		this.cercle=new Circle(this.personnage.getHauteur()/2);
		this.cercle.setFocusTraversable(true);
	}
	
	public Circle getCercle() {
		return this.cercle;
	}
	
	public Personnage getPersonnage() {
		return this.personnage;
	}
	
	
	
}
