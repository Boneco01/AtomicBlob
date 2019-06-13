package modele;

import modele.deplacements.DeplacementBasique;

public class Sentinelle extends Personnage {


	public Sentinelle(int vie, double vitesse, int largeur, int hauteur, String nom, int x, int y, Monde monde) {
		super(vie, vitesse, largeur, hauteur, nom, x, y, 3,new DeplacementBasique(monde.getJoueur()),monde);

	}

	@Override
	public void agir() {
		this.getDeplacement().seDeplace(this);

	}

}