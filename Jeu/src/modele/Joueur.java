package modele;

public class Joueur extends Personnage{
	
	private Monde monde;
	private boolean droite;
	private boolean gauche;
	private boolean haut;

	public Joueur(int vie, double vitesse, int largeur, int hauteur, String nom, int x, int y, Monde monde) {
		super(vie, vitesse, largeur, hauteur, nom, x, y);
		this.monde = monde;
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
        		this.monde.gererCollision(this, this.monde.getMap(), -this.getHauteur(), 0)){
        	this.goGauche();
        }
        
        if (this.getDroite() &&
        		this.monde.gererCollision(this, this.monde.getMap(), this.getHauteur(), 0)){
        	this.goDroite();
        }
        
        if (this.getHaut() && 
        		!this.monde.gererCollision(this, this.monde.getMap(), 0, this.getHauteur()))
        {
        	this.saute();
        	
        }
        
      
        if (this.monde.gererCollision(this, this.monde.getMap(), 0, this.getHauteur())) {
                this.tombe();
        
        }
	}
	
}
