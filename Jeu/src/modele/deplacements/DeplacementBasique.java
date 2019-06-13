package modele.deplacements;

import modele.BoiteCollision;
import modele.Personnage;

public class DeplacementBasique extends Deplacement{
	
	private Personnage personnageCible;
	
	public DeplacementBasique(Personnage personnageCible) {
		this.personnageCible=personnageCible;
	}
	
	@Override
	public void seDeplace(Personnage personnage) {
		int largeur=personnage.getLargeur();
		int hauteur=personnage.getHauteur();
		BoiteCollision boite=personnage.getBoite();
		
		if (personnage.estADroiteDe(this.personnageCible) && !boite.collision(-3, 6, -3, hauteur - 6)) {
			personnage.goGauche();
			personnage.setGauche(true);
			personnage.setDroite(false);
		}

		if (personnage.estAGaucheDe(this.personnageCible) && !boite.collision(largeur + 3, 6, largeur + 3, hauteur - 6)) {
			personnage.goDroite();
			personnage.setGauche(false);
			personnage.setDroite(true);
		}

		if ((personnage.getBoite().collision(-3, hauteur - 6, -3, hauteur - 6)
				|| personnage.getBoite().collision(largeur + 3, hauteur - 6, largeur + 3, hauteur - 6))
				&& personnage.getBoite().collision(0, hauteur, largeur, hauteur)) {
			personnage.setHauteurSaut(30);
		}

		if (personnage.getBoite().collision(0, -3, largeur, -3)) {
			personnage.setHauteurSaut(0);
		}

		if (personnage.getHauteurSaut() > 0) {
			personnage.saute(personnage.getVSaut());
			if (personnage.getHauteurSaut() == personnage.getHauteurSaut() / 2) {
				personnage.setVSaut(2);
			} else if (personnage.getHauteurSaut() == personnage.getHauteurSaut() / 3) {
				personnage.setVSaut(1);
			}
			personnage.setHauteurSaut(personnage.getHauteurSaut()-1);
		}

		else if (!personnage.getBoite().collision(0, personnage.getHauteur(), personnage.getLargeur(), personnage.getHauteur())) {
			personnage.tombe();
		}

		else if (!personnage.estAGaucheDe(this.personnageCible) && !personnage.estADroiteDe(this.personnageCible)) {
			personnage.setGauche(false);
			personnage.setDroite(false);
			// this.attaque(joueur);
		}
	}
}
