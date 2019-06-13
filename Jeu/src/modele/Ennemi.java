package modele;

import modele.deplacements.Deplacement;

public abstract class Ennemi extends Personnage{

	public Ennemi(int vie, double vitesse, int largeur, int hauteur, String nom, int x, int y, int vSaut, Deplacement deplacement, Monde monde,int vitesseAttaque) {
		super(vie, vitesse, largeur, hauteur, nom, x, y, vSaut, deplacement, monde,vitesseAttaque);

	}
	
	public void bindCibleAuJoueur() {
		this.getXCibleProperty().bind(this.getMonde().getJoueur().getXProperty());
		this.getYCibleProperty().bind(this.getMonde().getJoueur().getYProperty());
	}

	public abstract void agir();

	
	public boolean estAGaucheJoueur() {
		return this.estAGaucheCible(this.getMonde().getJoueur());
	}
	
	public boolean estADroiteJoueur() {
		return this.estADroiteCible(this.getMonde().getJoueur());
	}
	
	
}
