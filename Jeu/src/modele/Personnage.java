package modele;

public class Personnage {
	private String nom;
	private int vie;
	private double vitesse;
	private int taille;
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
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}
