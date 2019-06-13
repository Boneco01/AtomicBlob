package modele;

import modele.deplacements.DeplacementBasique;

public class Sentinelle extends Personnage {


	public Sentinelle(int vie, double vitesse, int largeur, int hauteur, String nom, int x, int y, Monde monde) {
		super(vie, vitesse, largeur, hauteur, nom, x, y, 3,new DeplacementBasique(monde.getJoueur()),monde);

	}
	
	public void bindCibleAuJoueur() { // ici une partie du modele ecoute une autre partie du modele, le bind n'est pas tres plaisant ici, mais vous me l'aviez autorise !
		this.getXCibleProperty().bind(this.getMonde().getJoueur().getXProperty());
		this.getYCibleProperty().bind(this.getMonde().getJoueur().getYProperty());
	}

	@Override
	public void agir() {
		this.getDeplacement().seDeplace(this);

	}

}