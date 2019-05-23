package modele;

import modele.Blocks.Air;
import modele.Blocks.Block;
import modele.Blocks.Terre;
import modele.Items.Item;
import modele.Items.ItemBlock;

public class Joueur extends Personnage{
	
	private Monde monde;
	private Inventaire inventaire;
	private boolean droite;
	private boolean gauche;
	private boolean haut;
	private int hauteurSaut;
	private int vSaut;
	private boolean construire;
	private boolean creuse;
	private int xBlocAModifier;
	private int yBlocAModifier;
	
	
	public Joueur(int vie, double vitesse, int largeur, int hauteur, String nom, int x, int y, Monde monde) {
		super(vie, vitesse, largeur, hauteur, nom, x, y,monde);
		this.monde = monde;
		this.hauteurSaut = 0;
		this.vSaut = 3;
		this.construire=false;
		this.creuse=false;
		
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
        if (gauche && !this.getBoite().collisionGauche()){
        	this.goGauche();
        }
          
        if (droite && !this.getBoite().collisionDroite()){
        	this.goDroite();
        }
        
        if (haut && this.getBoite().collisionBas()) {
        	this.hauteurSaut = 12;
        }
        
        if(this.getBoite().collisionHaut()) {
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
        
        else if (!this.getBoite().collisionBas()) {
        	this.tombe();
        }
        
        
	}
	
	public void creuse() {
		if(this.creuse) {
			Air blockAir=new Air();
			this.ramasseBlock(this.monde.getMap().blockParCord(xBlocAModifier, yBlocAModifier));
			this.monde.getMap().remplacerBlock(blockAir, this.xBlocAModifier, this.yBlocAModifier);
		}
	}
	
	public boolean verificationPointBlock(String point) {
		int largeur=0;
		int hauteur=0;
		switch (point) {
		 case "hd" : largeur=this.getLargeur(); break;
		 case "bg" : hauteur=this.getHauteur(); break;
		 case "bd" : largeur=this.getLargeur(); hauteur=this.getHauteur(); break;
		}
		if (this.getXBlocAModifier()!=(this.getXProperty().getValue()+largeur)/64 ||
				this.getYBlocAModifier()!=(this.getYProperty().getValue()+hauteur)/64) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean peutConstruire() {
		if (verificationPointBlock("") && verificationPointBlock ("hd") && verificationPointBlock("bg") && verificationPointBlock("bd")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void construit() {
		if (this.construire && peutConstruire())
			{		
			Terre blockTerre=new Terre(); //ici le block sera determine en fonction du block tenu par le joueur
			this.monde.getMap().remplacerBlock(blockTerre, this.xBlocAModifier, this.yBlocAModifier);
		}
	}
	
	public void ramasseBlock(Block b) {
		Item item = new ItemBlock(b.getId(), b);
		this.inventaire.addItem(item);
	}
	
	public void equipeGauche(Item item) {
		this.inventaire.equiper(item, 'g');
	}
	public void equipeDroit(Item item) {
		this.inventaire.equiper(item, 'd');
	}
	
	public void desequipeGauche() {
		this.inventaire.desequiper('g');
	}
	
	public void desequipeDroite() {
		this.inventaire.desequiper('g');
	}
	
}