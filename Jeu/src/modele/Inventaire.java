package modele;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.Blocks.Block;
import modele.Blocks.Bois;
import modele.Blocks.Herbe;
import modele.Blocks.MineraiFer;
import modele.Blocks.MineraiRadium;
import modele.Blocks.Pierre;
import modele.Blocks.Sable;
import modele.Blocks.Terre;
import modele.Items.Item;
import modele.Items.Block.*;
import modele.Items.ItemVide;

public class Inventaire {

	private ObservableList<Item> inventaire;
	private int nbItems;
	private int limiteInventaire;
	private Item equipementDroite;
	private Item equipementGauche;
	
	public Inventaire(Joueur joueur) {
		this.inventaire = FXCollections.observableList(new ArrayList<Item>());
		this.nbItems = 0;
		this.limiteInventaire = 10;
		this.equipementDroite = new ItemVide();
		this.equipementGauche = new ItemVide();
		initInventaire();
	}
	
	public ObservableList<Item> getInventaire() {
		return this.inventaire;
	}
	
	public Item getItem(int index) {
		return this.inventaire.get(index);
	}
	
	public Item getEquipementGauche() {
		return this.equipementGauche;
	}
	
	public Item getEquipementDroite() {
		return this.equipementDroite;
	}
	
	private void initInventaire() {
		for(int i=0; i<this.limiteInventaire;i++) {
			this.inventaire.add(new ItemVide());
		}
	}
	
	public boolean addItemBlock(Block b) {
		
		Item itemBlock = blockToItem(b);
		return addItem(itemBlock);
	}
	
	private Item blockToItem(Block b) {
		if (b instanceof Terre) {
            return new ItemTerre();
		} else if (b instanceof Bois) {
        	return new ItemBois();
		} else if (b instanceof Herbe) {
        	return new ItemTerre();
        } else if (b instanceof MineraiFer) {
        	return new ItemMineraiFer();
        } else if (b instanceof MineraiRadium) {
        	return new ItemMineraiRadium();
        } else if (b instanceof Pierre) {
        	return new ItemPierre();
        } else if (b instanceof Sable) {
        	return new ItemSable();
        } else {
            return new ItemVide();
		}
	}
	
	public boolean addItem(Item item) {
		
		if(item instanceof ItemVide) {
			return false;
		}
		
		for(Item i : this.inventaire) {
			if(i.getId()==item.getId()) {
				if(i.getQuantitee() < i.getQuantiteeMax()) {
					i.setQuantitee(i.getQuantitee()+1);
					return true;
				}
			}
		}
		
		if(this.nbItems < this.limiteInventaire) {
			for(int i=0;i<this.limiteInventaire;i++) {
				if(this.inventaire.get(i).getId()==0) {
					this.inventaire.set(i, item);
					this.nbItems++;
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean removeItem() {
		
		for(int i=0;i<this.inventaire.size();i++) {
			if(this.inventaire.get(i).getQuantitee()<=0) {
				this.inventaire.set(i, new ItemVide());
				return true;
			}
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
			this.equipementGauche = new ItemVide();
		} else {
			this.equipementDroite = new ItemVide();
		}
	}
	
}
