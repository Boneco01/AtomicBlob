package modele;

import modele.deplacements.DeplacementAStar;

public class Drone extends Ennemi{

	public Drone(String nom, int x, int y, Monde monde) {
		super(10, 2, 44, 26, nom, x, y, 0, new DeplacementAStar(monde.getJoueur()), monde, 50);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void agir() {
		this.getDeplacement().seDeplace(this);
	}
}
