package modele;

import modele.Blocks.Block;
import modele.Items.Item;

public class Joueur extends Personnage{
	
	private Monde monde;
	private Inventaire inventaire;
	private boolean haut;
	private int hauteurSaut;
	private int vSaut;
	private boolean utiliserMainGauche;
	private boolean utiliserMainDroite;
	
	public Joueur(int vie, double vitesse, int largeur, int hauteur, String nom, int x, int y, Monde monde) {
		super(vie, vitesse, largeur, hauteur, nom, x, y,monde);
		this.monde = monde;
		this.inventaire = new Inventaire(this);
		this.hauteurSaut = 0;
		this.vSaut = 3;
		this.utiliserMainGauche=false;
		this.utiliserMainDroite=false;	
	}
	
	public void utiliserItemGauche() {
		
		if (this.utiliserMainGauche) {
			this.inventaire.getEquipementGauche().utiliser(monde);
			if(this.inventaire.getEquipementGauche().getQuantitee()<=0) {
				this.desequipeGauche();
				this.inventaire.removeItem();
			}
		}
	}
	
	public void utiliserItemDroite() {
		
		if (this.utiliserMainDroite) {
			this.inventaire.getEquipementDroite().utiliser(monde);
			if(this.inventaire.getEquipementDroite().getQuantitee()<=0) {
				this.desequipeDroite();
				this.inventaire.removeItem();
			}
		}
	}
	
	public void setHaut(boolean a) {
		this.haut=a;
	}
	
	public void setUtiliserMainGauche(boolean a) {
		this.utiliserMainGauche=a;
	}
	
	public void setUtiliserMainDroite(boolean a) {
		this.utiliserMainDroite=a;
	}
	
	public boolean getUtiliserMainGauche() {
		return this.utiliserMainGauche;
	}
	
	public boolean getUtiliserMainDroite() {
		return this.utiliserMainDroite;
	}
	
	public boolean getHaut() {
		return this.haut;
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
		utiliserItemGauche();
		utiliserItemDroite();
		//annulationDestruction();
	}
	
	public void seDeplace() {
        if (getGauche() && !this.getBoite().collision(-3,6,-3,getHauteur()-6)){
        	this.goGauche();
        }
          
        if (getDroite() && !this.getBoite().collision(getLargeur()+3,6,getLargeur()+3,-6)){
        	this.goDroite();
        }
        
        if (haut && this.getBoite().collision(0, getHauteur(), getLargeur(), getHauteur())) {
        	this.hauteurSaut = 12;
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
		this.inventaire.desequiper('d');
	}
	
}