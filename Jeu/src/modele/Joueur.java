package modele;

import modele.Blocks.BidonRadioactif;
import modele.Blocks.Block;
import modele.Items.Item;
import modele.deplacements.DeplacementJoueur;

public class Joueur extends Personnage{
	
	private Monde monde;
	private Inventaire inventaire;
	private boolean utiliserMainGauche;
	private boolean utiliserMainDroite;
	
	public Joueur(int vie, double vitesse, String nom, int x, int y, Monde monde) {
		super(vie, vitesse, 44, 26, nom, x, y,3,new DeplacementJoueur(),monde,10);
		this.monde = monde;
		this.inventaire = new Inventaire(this);
		this.utiliserMainGauche=false;
		this.utiliserMainDroite=false;	
	}
	
	public void utiliserItemGauche() {
		
		if (this.utiliserMainGauche) {
			this.inventaire.getEquipementGauche().utiliser(monde);
			if(this.inventaire.getEquipementGauche().getQuantitee()<=0 || this.inventaire.getEquipementGauche().getDurabilite()<=0) {
				this.desequipeGauche();
			}
			this.inventaire.removeItem();
		}
	}
	
	public void utiliserItemDroite() {
		
		if (this.utiliserMainDroite) {
			this.inventaire.getEquipementDroite().utiliser(monde);
			if(this.inventaire.getEquipementDroite().getQuantitee()<=0 || this.inventaire.getEquipementDroite().getDurabilite()<=0) {
				this.desequipeDroite();
			}
			this.inventaire.removeItem();
		}
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
	
	public Monde getMonde() {
		return this.monde;
	}
	
	public Inventaire getInventaire() {
		return this.inventaire;
	}

	@Override
	public void agir() {
		
		this.getDeplacement().seDeplace(this);
		utiliserItemGauche();
		utiliserItemDroite();
	}
	
	public void ramasseBlock(Block b) {
		if(b instanceof BidonRadioactif) {
			this.setVie(this.getVie()+1);
		} else {
			this.inventaire.addItemBlock(b);
		}
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