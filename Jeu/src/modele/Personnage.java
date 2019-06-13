package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import modele.deplacements.Deplacement;

public abstract class Personnage {
	private String nom;
	private int hauteurSaut;
	private int vSaut;
	private IntegerProperty vie;
	private DoubleProperty vitesse; //nb de pixels parcourus en un d�placement ( un tour de jeu ) A utiliser plus tard.
	private int largeur; //sert � la hitbox du personnage, � choisir en fonction de la largeur du sprite
	private int hauteur; //sert � la hitbox du personnage, � choisir en fonction du la hauteur du sprite
	private IntegerProperty xProperty;
	private IntegerProperty yProperty;
	private Monde monde;
	private IntegerProperty xCibleProperty;
	private IntegerProperty yCibleProperty;
	private boolean droite;
	private boolean gauche;
	private boolean haut;
	private BoiteCollision boite;
	private Deplacement deplacement;
	
	
	public Personnage (int vie, double vitesse, int largeur, int hauteur, String nom, int x, int y, int vSaut, Deplacement deplacement, Monde monde) {
		this.vie = new SimpleIntegerProperty(vie);
		this.vitesse=new SimpleDoubleProperty(vitesse);
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.nom=nom;
		this.deplacement=deplacement;
		this.xProperty=new SimpleIntegerProperty(x);
		this.yProperty=new SimpleIntegerProperty(y);
		this.monde=monde;
		this.boite=new BoiteCollision(this);
		this.xCibleProperty=new SimpleIntegerProperty(0);
		this.yCibleProperty=new SimpleIntegerProperty(0);
		this.vSaut=vSaut;
		this.hauteurSaut=0;
	}
	
	public boolean estAGaucheDe(Personnage personnage) {
		if (this.getXProperty().getValue() / 64 < personnage.getXProperty().getValue() / 64) {
			return true;
		}
		return false;
	}

	public boolean estADroiteDe(Personnage personnage) {
		if (this.getXProperty().getValue() / 64 > personnage.getXProperty().getValue() / 64) {
			return true;
		}
		return false;
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
		return this.vie.getValue();
	}
	
	public Deplacement getDeplacement() {
		return this.deplacement;
	}
	
	public IntegerProperty getVieProperty() {
		return this.vie;
	}
	
	public void setVie(int vie) {
		this.vie.setValue(vie);
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
	
	public void setHauteurSaut(int h) {
		this.hauteurSaut=h;
	}
	
	public void setVSaut(int v) {
		this.vSaut=v;
	}
	
	public IntegerProperty getYProperty() {
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
	
	public void setGauche(boolean a) {
		this.gauche=a;
	}
	
	public void setDroite(boolean a) {
		this.droite=a;
	}
	
	public void setHaut(boolean a) {
		this.haut=a;
	}
	
	public IntegerProperty getXCibleProperty() {
		return this.xCibleProperty;
	}
	
	public IntegerProperty getYCibleProperty() {
		return this.yCibleProperty;
	}
	
	public int getXCible() {
		return this.xCibleProperty.getValue();
	}
	
	public int getYCible() {
		return this.yCibleProperty.getValue();
	}
	
	public int getHauteurSaut() {
		return this.hauteurSaut;
	}
	
	public int getVSaut() {
		return this.vSaut;
	}
	
	public boolean getHaut() {
		return this.haut;
	}
	
	public abstract void agir();
}