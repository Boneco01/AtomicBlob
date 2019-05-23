package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Personnage {
	private String nom;
	private int vie;
	private DoubleProperty vitesse; //nb de pixels parcourus en un d�placement ( un tour de jeu ) A utiliser plus tard.
	private int largeur; //sert � la hitbox du personnage, � choisir en fonction de la largeur du sprite
	private int hauteur; //sert � la hitbox du personnage, � choisir en fonction du la hauteur du sprite
	private IntegerProperty xProperty;
	private IntegerProperty yProperty;
	private Monde monde;
	private BoiteCollision boite;
	
	
	public Personnage (int vie, double vitesse, int largeur, int hauteur, String nom, int x, int y, Monde monde) {
		this.vie=vie;
		this.vitesse=new SimpleDoubleProperty(vitesse);
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.nom=nom;
		this.xProperty=new SimpleIntegerProperty(x);
		this.yProperty=new SimpleIntegerProperty(y);
		this.monde=monde;
		this.boite=new BoiteCollision(this);
	}
	
	public void goDroite() {
		this.xProperty.setValue(this.xProperty.getValue()+this.vitesse.getValue());
	}
	
	public void goGauche() {
		this.xProperty.setValue(this.xProperty.getValue()-this.vitesse.getValue());
	}
	
	public void saute(int vSaut) {
		this.yProperty.setValue(this.yProperty.getValue()-(vSaut*this.vitesse.getValue()));
	}
	
	public void tombe() {
		this.yProperty.setValue(this.yProperty.getValue()+6);
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getVie() {
		return this.vie;
	}
	
	public int getLargeur() {
		return this.largeur;
	}
	
	public int getHauteur() {
		return this.hauteur;
	}
	
	public Monde getMonde() {
		return this.monde;
	}
	
	public BoiteCollision getBoite() {
		return this.boite;
	}
	
	public IntegerProperty getXProperty() {
		return this.xProperty;
	}
	
	public void setX(int x) {
		this.xProperty.setValue(x);
	}
	
	public void setY(int y) {
		this.yProperty.setValue(y);
	}
	
	public IntegerProperty getYProperty() {
		return this.yProperty;
	}
	
	public DoubleProperty getVitesse() {
		return this.vitesse;
	}
	
	public abstract void agir();
	
	public abstract void seDeplace();
}
