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
	private int xSouris;
	private int ySouris;

	public Joueur(int vie, double vitesse, int largeur, int hauteur, String nom, int x, int y, Monde monde) {
		super(vie, vitesse, largeur, hauteur, nom, x, y);
		this.monde = monde;
		this.hauteurSaut = 0;
		this.vSaut = 3;
		this.construire=false;
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
	
	public void setXSouris(int x) {
		this.xSouris=x;
	}
	
	public void setYSouris(int y) {
		this.ySouris=y;
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
	
	public int getXSouris() {
		return this.xSouris;
	}
	
	public int getYSouris() {
		return this.ySouris;
	}

	@Override
	public void agir() {
		
		seDeplace();
		creuse();
		construit();
		
	}
	
	public void seDeplace() {
		
        if (this.getGauche() &&
        		this.monde.gererCollision(this.monde.getMap(), -3, 0) &&
        		this.monde.gererCollision(this.monde.getMap(), -3, this.getHauteur()-3)){
        	this.goGauche();
        }
          
        if (this.getDroite() &&
        		this.monde.gererCollision(this.monde.getMap(), this.getLargeur()+3, 0) &&
        		this.monde.gererCollision(this.monde.getMap(), this.getLargeur()+3, this.getHauteur()-3)){
        	this.goDroite();
        }
        
        
        
        if (this.getHaut() && 
        		(!this.monde.gererCollision(this.monde.getMap(), 0, this.getHauteur()) ||
        		!this.monde.gererCollision(this.monde.getMap(), this.getLargeur(), this.getHauteur())))
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
        
        else if (this.monde.gererCollision(this.monde.getMap(), 0, this.getHauteur()) && 
        		this.monde.gererCollision(this.monde.getMap(), this.getLargeur(), this.getHauteur())) {
        	this.tombe();
        }
        
        
	}
	
	public void creuse() {
		if(this.creuse) {
			Air blockAir=new Air();
			this.monde.getMap().remplacerBlock(blockAir, this.xSouris, this.ySouris);
		}
	}
	
	public void construit() {
		if (this.construire) {
			Terre blockTerre=new Terre(); //ici le block sera determine en fonction du block tenu par le joueur
			this.monde.getMap().remplacerBlock(blockTerre, this.xSouris, this.ySouris);
		}
	}
	
}