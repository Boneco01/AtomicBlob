package modele;

import modele.Blocks.Block;
import modele.Items.Item;

public class Joueur extends Personnage{
	
	private Monde monde;
	private Inventaire inventaire;
	private boolean droite;
	private boolean gauche;
	private boolean haut;
	private int hauteurSaut;
	private int vSaut;
	private boolean utiliser;
	private int xCible;
	private int yCible;
	private Item itemEquipe;
	
	public Joueur(int vie, double vitesse, int largeur, int hauteur, String nom, int x, int y, Monde monde, Item item) {
		super(vie, vitesse, largeur, hauteur, nom, x, y,monde);
		this.monde = monde;
		this.inventaire = new Inventaire(this);
		this.hauteurSaut = 0;
		this.vSaut = 3;
		this.utiliser=false;
		this.itemEquipe=item;
		
	}
	
	public void utiliserItem() {
		this.itemEquipe.utiliser(this.monde);
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
	
	public void setXCible(int x) {
		this.xCible=x;
	}
	
	public void setYCible(int y) {
		this.yCible=y;
	}
	
	public void setUtiliser(boolean a) {
		this.utiliser=a;
	}
	
	public boolean getUtiliser() {
		return this.utiliser;
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
	
	public int getXCible() {
		return this.xCible;
	}
	
	public int getYCible() {
		return this.yCible;
	}
	
	public Item getItem() {
		return this.itemEquipe;
	}
	
	public Monde getMonde() {
		return this.monde;
	}
	
	public Inventaire getInventaire() {
		return this.inventaire;
	}

	@Override
	public void agir() {
		
		seDeplace();
		utiliserItem();
		
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
	
	public void ramasseBlock(Block b) {
		this.inventaire.addItemBlock(b);
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