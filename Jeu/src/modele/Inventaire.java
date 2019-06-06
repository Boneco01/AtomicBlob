package modele;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.Blocks.*;
import modele.Items.*;
import modele.Items.Block.*;
import modele.Items.Craft.*;

public class Inventaire {

	private ObservableList<Item> inventaire;
	private Joueur joueur;
	private int nbItems;
	private int limiteInventaire;
	private Item equipementDroite;
	private Item equipementGauche;
	
	public Inventaire(Joueur joueur) {
		this.inventaire = FXCollections.observableList(new ArrayList<Item>());
		this.joueur = joueur;
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
		this.addItem(new ItemBarreMetal());
		this.addItem(new ItemFil());
		this.addItem(new ItemHache());
		this.addItem(new ItemLancePierre());
		this.addItem(new ItemLingotFer());
		this.addItem(new ItemPioche());
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
        } else if (b instanceof BlockDeDechets) {
        	int random = (int)(Math.random()*10);
    		if(random < 7) {
    			return new ItemFil();
    		} else if (random < 9) {
    			return new ItemLingotFer();
    		} else {
    			return new ItemBarreMetal();
    		}
        } else if (b instanceof BidonRadioactif) {
        	//Donner de la vie
            return new ItemVide();
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
				this.nbItems--;
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
