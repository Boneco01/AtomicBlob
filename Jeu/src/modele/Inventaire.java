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
import modele.Items.ItemBois;
import modele.Items.ItemMineraiFer;
import modele.Items.ItemMineraiRadium;
import modele.Items.ItemPierre;
import modele.Items.ItemSable;
import modele.Items.ItemTerre;
import modele.Items.ItemVide;

public class Inventaire {

	private ObservableList<Item> inventaire;
	private int nbItems;
	private int limiteInventaire;
	private Item equipementDroite;
	private Item equipementGauche;
	private Monde monde;
	
	public Inventaire(Joueur joueur) {
		this.inventaire = FXCollections.observableList(new ArrayList<Item>());
		this.nbItems = 0;
		this.limiteInventaire = 10;
		this.monde=joueur.getMonde();
		this.equipementDroite = new ItemTerre(this.monde);
		this.equipementGauche = new ItemVide(this.monde);
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
			this.inventaire.add(new ItemVide(this.monde));
		}
	}
	
	public boolean addItemBlock(Block b) {
		
		Item itemBlock = blockToItem(b);
		return addItem(itemBlock);
	}
	
	private Item blockToItem(Block b) {
		if (b instanceof Terre) {
            return new ItemTerre(this.monde);
		} else if (b instanceof Bois) {
        	return new ItemBois(this.monde);
		} else if (b instanceof Herbe) {
        	return new ItemTerre(this.monde);
        } else if (b instanceof MineraiFer) {
        	return new ItemMineraiFer(this.monde);
        } else if (b instanceof MineraiRadium) {
        	return new ItemMineraiRadium(this.monde);
        } else if (b instanceof Pierre) {
        	return new ItemPierre(this.monde);
        } else if (b instanceof Sable) {
        	return new ItemSable(this.monde);
        } else {
            return new ItemVide(this.monde);
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
			this.inventaire.remove(0);
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
