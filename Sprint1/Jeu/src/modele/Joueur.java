package modele;

public class Joueur extends Personnage{
	
	private boolean droite;
	private boolean gauche;
	private boolean haut;

	public Joueur(int vie, double vitesse, int largeur, int hauteur, String nom, int x, int y) {
		super(vie, vitesse, largeur, hauteur, nom, x, y);
	}
	
}
