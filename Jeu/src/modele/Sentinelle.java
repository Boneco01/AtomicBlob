package modele;

public class Sentinelle extends Personnage{
	
	private int hauteurSaut;
	private int vSaut;

	public Sentinelle(int vie, double vitesse, int largeur, int hauteur, String nom, int x, int y, Monde monde) {
		super(vie, vitesse, largeur, hauteur, nom, x, y, monde);
		this.hauteurSaut = 0;
		this.vSaut = 3;
		
	}
	
	public void bindCibleAuJoueur() {
		this.getXCibleProperty().bind(this.getMonde().getJoueur().getXProperty());
		this.getYCibleProperty().bind(this.getMonde().getJoueur().getYProperty());
	}

	@Override
	public void agir() {
		seDeplace();
		
	}

	@Override
	public void seDeplace() {
		if (estADroiteJoueur() && !this.getBoite().collision(-3,6,-3,getHauteur()-6)){
        	this.goGauche();
        	setGauche(true);
        	setDroite(false);
        }
          
        if (estAGaucheJoueur() && !this.getBoite().collision(getLargeur()+3,6,getLargeur()+3,getHauteur()-6)){
        	this.goDroite();
        	setGauche(false);
        	setDroite(true);
        }
        
        if (( this.getBoite().collision(-3, getHauteur()-6, -3, getHauteur()-6) || this.getBoite().collision(getLargeur()+3, getHauteur()-6,getLargeur()+3, getHauteur()-6)) 
        		&& this.getBoite().collision(0, getHauteur(), getLargeur(), getHauteur())) {
        	this.hauteurSaut = 30;
        }
        
        if(this.getBoite().collision(0,-3,getLargeur(),-3)) {
        	this.hauteurSaut = 0;
        }
        
        if( this.hauteurSaut > 0 ) {
        	this.saute(this.vSaut);
        	if(this.hauteurSaut == this.hauteurSaut/2) {
        		this.vSaut = 2;
        	} else if( this.hauteurSaut == this.hauteurSaut/3) {
        		this.vSaut = 1;
        	}
        	this.hauteurSaut--;
        }
        
        else if (!this.getBoite().collision(0, getHauteur(), getLargeur(), getHauteur())) {
        	this.tombe();
        }
        
        else if (!estAGaucheJoueur() && !estADroiteJoueur() ){
        	setGauche(false);
        	setDroite(false);
        	//this.attaque(joueur);
        }
	}
	
	public boolean estAGaucheJoueur() {
		if (this.getXProperty().getValue()/64<this.getMonde().getJoueur().getXProperty().getValue()/64) {
			return true;
		}
		return false;
	}
	
	public boolean estADroiteJoueur() {
		if (this.getXProperty().getValue()/64>this.getMonde().getJoueur().getXProperty().getValue()/64) {
			return true;
		}
		return false;
	}

}