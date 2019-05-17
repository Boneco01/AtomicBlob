package modele;

public class Joueur extends Personnage{
	
	private Monde monde;
	private boolean droite;
	private boolean gauche;
	private boolean haut;
	private int hauteurSaut;
	private int vSaut;

	public Joueur(int vie, double vitesse, int largeur, int hauteur, String nom, int x, int y, Monde monde) {
		super(vie, vitesse, largeur, hauteur, nom, x, y);
		this.monde = monde;
		this.hauteurSaut = 0;
		this.vSaut = 3;
	}
	
	public void setGauche(boolean estPresse) {
		this.gauche=estPresse;
	}
	
	public void setDroite(boolean estPresse) {
		this.droite=estPresse;
	}
	
	public void setHaut(boolean estPresse) {
		this.haut=estPresse;
	}
	
	public boolean getGauche() {
		return this.gauche;
	}
	
	public boolean getDroite() {
		return this.droite;
	}
	
	public boolean getHaut() {
		return this.haut;
	}

	@Override
	public void agir() {
		
		seDeplace();
		
	}
	
	public void seDeplace() {
		
        if (this.getGauche() &&
        		this.monde.gererCollision(this, this.monde.getMap(), 0, 0)){
        	this.goGauche();
        }
        
        if (this.getDroite() &&
        		this.monde.gererCollision(this, this.monde.getMap(), this.getLargeur(), 0)){
        	this.goDroite();
        }
        
        if (this.getHaut() && 
        		!this.monde.gererCollision(this, this.monde.getMap(), 0, this.getHauteur()))
        {
        	this.hauteurSaut = 12;
        } 
        
        if( this.hauteurSaut > 0) {
        	this.saute(this.vSaut);
        	if(this.hauteurSaut == this.hauteurSaut/2) {
        		this.vSaut = 2;
        	} else if( this.hauteurSaut == this.hauteurSaut/3) {
        		this.vSaut = 1;
        	}
        	this.hauteurSaut--;
        }
        
        else if (this.monde.gererCollision(this, this.monde.getMap(), 0, this.getHauteur())) {
        	this.tombe();
        }
	}
	
}
