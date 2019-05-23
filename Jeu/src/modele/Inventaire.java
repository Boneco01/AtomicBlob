package modele;

import java.util.ArrayList;

import modele.Items.Item;

public class Inventaire {

	private ArrayList<Item> inventaire;
	private int nbItems;
	private int limiteInventaire;
	private Item equipementDroite;
	private Item equipementGauche;
	
	public Inventaire() {
		this.inventaire = new ArrayList<Item>();
		this.nbItems = 0;
		this.limiteInventaire = 10;
		this.equipementDroite = null;
		this.equipementGauche = null;
	}
	
	public boolean addItem(Item item) {
		for(Item i : this.inventaire) {
			if(i == item) {
				if(i.getQuantitee() < i.getQuantiteeMax()) {
					i.setQuantitee(i.getQuantitee()+1);
					return true;
				}
			}
		}
		if(this.nbItems < this.limiteInventaire) {
			this.inventaire.add(item);
			this.nbItems++;
			return true;
		}
		
		return false;
	}
	
	public void equiper(Item item, char emplacement) {
		if(emplacement == 'g') {
			this.equipementGauche = item;
		} else {
			this.equipementDroite = item;
		}
	}
	
	public void desequiper(char emplacement) {
		if(emplacement == 'g') {
			this.equipementGauche = null;
		} else {
			this.equipementDroite = null;
		}
	}
	
}
