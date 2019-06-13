package modele;

import modele.deplacements.Deplacement;

public abstract class Ennemi extends Personnage{

	public Ennemi(int vie, double vitesse, int largeur, int hauteur, String nom, int x, int y, int hauteurSaut, int vSaut, Deplacement deplacement, Monde monde) {
		super(vie, vitesse, largeur, hauteur, nom, x, y, vSaut, deplacement, monde);
	}
	
	public void bindCibleAuJoueur() {
		this.getXCibleProperty().bind(this.getMonde().getJoueur().getXProperty());
		this.getYCibleProperty().bind(this.getMonde().getJoueur().getYProperty());
	}

	public abstract void agir();

	public abstract void seDeplace();

}
