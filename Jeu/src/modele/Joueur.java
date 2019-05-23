package modele;

public class Joueur extends Personnage{
	
	private Monde monde;
	private boolean droite;
	private boolean gauche;
	private boolean haut;
	private int hauteurSaut;
	private int vSaut;
	private boolean construire;
	private boolean creuse;
	private int xBlocAModifier;
	private int yBlocAModifier;
	private BoiteCollision boiteJoueur;
	
	public Joueur(int vie, double vitesse, int largeur, int hauteur, String nom, int x, int y, Monde monde) {
		super(vie, vitesse, largeur, hauteur, nom, x, y);
		this.monde = monde;
		this.hauteurSaut = 0;
		this.vSaut = 3;
		this.construire=false;
		this.creuse=false;
		this.boiteJoueur=new BoiteCollision(this);
		
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
	
	public void setXBlocAModifier(int x) {
		this.xBlocAModifier=x;
	}
	
	public void setYBlocAModifier(int y) {
		this.yBlocAModifier=y;
	}
	
	public void setConstruire(boolean a) {
		this.construire=a;
	}
	
	public void setCreuse(boolean a) {
		this.creuse=a;
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
	
	public int getXBlocAModifier() {
		return this.xBlocAModifier;
	}
	
	public int getYBlocAModifier() {
		return this.yBlocAModifier;
	}
	
	public Monde getMonde() {
		return this.monde;
	}

	@Override
	public void agir() {
		
		seDeplace();
		creuse();
		construit();
		
	}
	
	public void seDeplace() {
		
        if (gauche && !this.boiteJoueur.collisionGauche()){
        	this.goGauche();
        }
          
        if (droite && !this.boiteJoueur.collisionDroite()){
        	this.goDroite();
        }       
        
        if (haut && this.boiteJoueur.collisionBas()) {
        	this.hauteurSaut = 12;
        }
        
        if(this.boiteJoueur.collisionHaut()) {
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
        
        else if (!this.boiteJoueur.collisionBas()) {
        	this.tombe();
        }
        
        
	}
	
	public void creuse() {
		if(this.creuse) {
			Air blockAir=new Air();
			this.monde.getMap().remplacerBlock(blockAir, this.xBlocAModifier, this.yBlocAModifier);
		}
	}
	
	public void construit() {
		if (this.construire && this.boiteJoueur.peutConstruire())
			{		
			Terre blockTerre=new Terre(); //ici le block sera determine en fonction du block tenu par le joueur
			this.monde.getMap().remplacerBlock(blockTerre, this.xBlocAModifier, this.yBlocAModifier);
		}
	}
	
}