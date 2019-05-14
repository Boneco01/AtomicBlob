package controleur;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.scene.shape.Circle;
import modele.Personnage;

public class Sprite {
	private Personnage personnage;
	private Circle cercle;  //a remplacer plus tard par un sprite
	
	public Sprite(Personnage personnage) {
		this.personnage=personnage;
		this.cercle=new Circle(10);
	}
	
	public Circle getCercle() {
		return this.cercle;
	}
	
	public Personnage getPersonnage() {
		return this.personnage;
	}
	
	
	
}
