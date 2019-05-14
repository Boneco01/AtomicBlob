package modele;

public class Personnage {
	private String nom;
	private int vie;
	private double vitesse; //nb de pixels parcourus en un déplacement ( un tour de jeu ) A utiliser plus tard.
	private int taille; // Sert à la hitbox ?
	private int x;
	private int y;
	
	
	public Personnage (int vie, double vitesse, int taille, String nom, int x, int y) {
		this.vie=vie;
		this.vitesse=vitesse;
		this.taille=taille;
		this.nom=nom;
		this.x=x;
		this.y=y;
	}
	
	public void goDroite() {
		this.x += vitesse;
	}
	
	public void goGauche() {
		this.x -= vitesse;
	}
	
	public void saute() {
		this.y -= vitesse;
	}
	
	public void tombe() {
		this.y += vitesse;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getVie() {
		return this.vie;
	}
	
	public int getTaille() {
		return this.taille;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}
