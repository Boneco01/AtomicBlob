package controleur;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import modele.Personnage;

public class Sprite {
	private Personnage personnage;
	//private ImageView spriteDuPerso;
	private ImageView image;  //a remplacer plus tard par un sprite
	
	public Sprite(Personnage personnage) {
		this.personnage=personnage;
		//this.spriteDuPerso = new ImageView(new Image("file:../Sprites/Joueur/JoueurBase/JoueurBase.gif"));
		this.image=new ImageView(new Image("file:../Sprites/Joueur/JoueurBase/JoueurBase.gif"));
		this.image.setFocusTraversable(true);
	}
	
	public ImageView getImage() {
		return this.image;
	}
	
	public Personnage getPersonnage() {
		return this.personnage;
	}
	
	
	
}
