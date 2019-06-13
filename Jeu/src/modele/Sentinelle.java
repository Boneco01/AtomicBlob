package modele;

public class Sentinelle extends Ennemi {
	
	private int hauteurSaut;
	private int vSaut;

	public Sentinelle(double vitesse, int largeur, int hauteur, String nom, int x, int y, Monde monde) {
		super(15, vitesse, largeur, hauteur, nom, x, y, monde, 50);
		this.hauteurSaut = 0;
		this.vSaut = 3;
		
	}
	
	public void bindCibleAuJoueur() { // ici une partie du modele ecoute une autre partie du modele, le bind n'est pas tres plaisant ici, mais vous me l'aviez autorise !
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
        	if(this.getY()<(this.getMonde().getJoueur().getY()+100) && this.getY()>(this.getMonde().getJoueur().getY()-100)) {
        		this.attaque(this.getMonde().getJoueur(), 1);
        	}
        }
	}

}