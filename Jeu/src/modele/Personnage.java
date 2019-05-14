package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Personnage {
	private String nom;
	private int vie;
	private DoubleProperty vitesse; //nb de pixels parcourus en un d�placement ( un tour de jeu ) A utiliser plus tard.
	private int taille; // Sert � la hitbox ?
	private IntegerProperty xProperty;
	private IntegerProperty yProperty;
	
	
	public Personnage (int vie, double vitesse, int taille, String nom, int x, int y) {
		this.vie=vie;
		this.vitesse=new SimpleDoubleProperty(vitesse);
		this.taille=taille;
		this.nom=nom;
		this.xProperty=new SimpleIntegerProperty(x);
		this.yProperty=new SimpleIntegerProperty(y);
	}
	
	public void goDroite() {
		this.xProperty.setValue(this.xProperty.getValue()+this.vitesse.getValue());
	}
	
	public void goGauche() {
		this.xProperty.setValue(this.xProperty.getValue()-this.vitesse.getValue());
	}
	
	public void saute() {
		this.yProperty.setValue(this.yProperty.getValue()-2*(this.vitesse.getValue()));
	}
	
	public void tombe() {
		this.yProperty.setValue(this.yProperty.getValue()+2*(this.vitesse.getValue()));
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getVie() {
		return this.vie;
	}
	
	public int getTaille() {
		return this.taille;
	}
	
	public IntegerProperty getX() {
		return this.xProperty;
	}
	
	public IntegerProperty getY() {
		return this.yProperty;
	}
	
	public DoubleProperty getVitesse() {
		return this.vitesse;
	}
}
