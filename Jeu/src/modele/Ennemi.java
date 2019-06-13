package modele;

public abstract class Ennemi extends Personnage {
	
	public Ennemi(int vie, double vitesse, int largeur, int hauteur, String nom, int x, int y, Monde monde, int vitesseAttaque) {
		super(vie, vitesse, largeur, hauteur, nom, x, y, monde, vitesseAttaque);
	}
	
	public void bindCibleAuJoueur() {
		this.getXCibleProperty().bind(this.getMonde().getJoueur().getXProperty());
		this.getYCibleProperty().bind(this.getMonde().getJoueur().getYProperty());
	}
	
	public boolean estAGaucheJoueur() {
		return this.estAGaucheCible(this.getMonde().getJoueur());
	}
	
	public boolean estADroiteJoueur() {
		return this.estADroiteCible(this.getMonde().getJoueur());
	}
	
	@Override
	public void attaque(Personnage cible, int degats) {
		if(this.getTempsAttaque() == 0) {
			if(this.estADroiteCible(cible)) {
				cible.setEstRepousse('d');
			} else {
				cible.setEstRepousse('g');
			}
			cible.setVie(cible.getVie()-degats);
    		this.setTempsAttaque(this.getVitesseAttaque());
    	}
    	this.setTempsAttaque(this.getTempsAttaque()-1);
	}
	
	@Override
	public void agir() {
		seDeplace();
	}
}
