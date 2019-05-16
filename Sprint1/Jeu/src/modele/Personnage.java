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
	private boolean droite;
	private boolean gauche;
	private boolean haut;
	
	
	public Personnage (int vie, double vitesse, int largeur, int hauteur, String nom, int x, int y) {
		this.vie=vie;
		this.vitesse=new SimpleDoubleProperty(vitesse);
		this.largeur = largeur;
		this.hauteur = hauteur;
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
		
		this.yProperty.setValue(this.yProperty.getValue()-30*(this.vitesse.getValue()));
	}
	
	public void tombe() {
		this.yProperty.setValue(this.yProperty.getValue()+5);
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
	
	public IntegerProperty getX() {
		return this.xProperty;
	}
	
	public void setX(int x) {
		this.xProperty.setValue(x);
	}
	
	public void setY(int y) {
		this.yProperty.setValue(y);
	}
	
	public IntegerProperty getY() {
		return this.yProperty;
	}
	
	public DoubleProperty getVitesse() {
		return this.vitesse;
	}
	
	public boolean getGauche() {
		return this.gauche;
	}
	
	public boolean getDroite() {
		return this.droite;
	}
	
	public boolean getHaut() {
		return this.haut;
	}
	
	public void setGauche(boolean estPresse) {
		this.gauche=estPresse;
	}
	
	public void setDroite(boolean estPresse) {
		this.droite=estPresse;
	}
	
	public void setHaut(boolean estPresse) {
		this.haut=estPresse;
	}
}
