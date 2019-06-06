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
import modele.Items.Craft.ItemBarreMetal;
import modele.Items.Craft.ItemFil;
import modele.Items.Craft.ItemHache;
import modele.Items.Craft.ItemLancePierre;
import modele.Items.Craft.ItemLingotFer;
import modele.Items.Craft.ItemPioche;
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
		for (int i = 0; i < this.limiteInventaire; i++) {
			this.inventaire.add(new ItemVide());
		}
		ItemBois b = new ItemBois();
		b.setQuantitee(8);
		ItemFil f = new ItemFil();
		f.setQuantitee(2);
		this.addItem(f);
		ItemPierre p = new ItemPierre();
		p.setQuantitee(7);
		this.addItem(p);
		ItemMineraiFer mf= new ItemMineraiFer();
		mf.setQuantitee(3);
		this.addItem(mf);
		ItemMineraiRadium mr =new ItemMineraiRadium();
		mr.setQuantitee(3);
		this.addItem(mr);
		
		
		
		
		this.addItem(b);
	}
	
	public void videInventaire(TableCraft tc) {
		for (int i = 0; i < tc.getTc().size(); i++)
			this.removeItemCraft(tc.getTc().get(i));
	}
	
	public Inventaire copyInv(Joueur joueur) {
		Inventaire inv = new Inventaire(joueur);
		for (int i = 0; i < inventaire.size(); i++)
			inv.getInventaire().add(this.copy(inventaire.get(i)));
		return inv;
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

		if (item instanceof ItemVide) {
			return false;
		}

		for (Item i : this.inventaire) {
			if (i.getId() == item.getId()) {
				if (i.getQuantitee() < i.getQuantiteeMax()) {
					i.setQuantitee(i.getQuantitee() + 1);
					return true;
				}
			}
		}

		if (this.nbItems < this.limiteInventaire) {
			for (int i = 0; i < this.limiteInventaire; i++) {
				if (this.inventaire.get(i).getId() == 0) {
					this.inventaire.set(i, item);
					this.nbItems++;
					return true;
				}
			}
		}

		return false;
	}

	public boolean removeItem() {

		for (int i = 0; i < this.inventaire.size(); i++) {
			if (this.inventaire.get(i).getQuantitee() <= 0) {
				this.inventaire.set(i, new ItemVide());
				this.nbItems--;
				return true;
			}
		}

		return false;
	}

	public boolean removeItemCraft(Item item) {
		if(item.getClass()==new ItemVide().getClass())
			return true;
		for (int i = this.inventaire.size()-1; i > 0; i--) {
			if (this.inventaire.get(i).getClass() == item.getClass()) {
				this.inventaire.get(i).setQuantitee(this.inventaire.get(i).getQuantitee() - 1);
			if (this.inventaire.get(i).getQuantitee()==0)
				this.removeItem();
			return true;
			}
		}

		return false;

	}

	public void equiper(Item item, char emplacement) {
		if (emplacement == 'g') {
			this.equipementGauche = item;
		} else {
			this.equipementDroite = item;
		}
	}

	public void desequiper(char emplacement) {
		if (emplacement == 'g') {
			this.equipementGauche = new ItemVide();
		} else {
			this.equipementDroite = new ItemVide();
		}
	}

	public int getNbItems() {
		return nbItems;
	}

	public int getLimiteInventaire() {
		return limiteInventaire;
	}
	public Item copy(Item i) {
		if(i.getId()==0) 
			return new ItemVide();	
		else if(i.getId()==1)
			return new ItemTerre();
		else if(i.getId()==2)
			return new ItemMineraiFer();
		else if(i.getId()==3)
			return new ItemVide();
		else if(i.getId()==4)
			return new ItemBois();
		else if(i.getId()==5)
			return new ItemPierre();
		else if(i.getId()==6)
			return new ItemMineraiRadium();
		else if(i.getId()==7)
			return new ItemSable();
		else if(i.getId()==8)
			return new ItemVide();
		else if(i.getId()==9)
			return new ItemVide();
		else if(i.getId()==10)
			return new ItemLingotFer();
		else if(i.getId()==11)
			return new ItemLancePierre();
		else if(i.getId()==12)
			return new ItemPioche();
		else if(i.getId()==13)
			return new ItemHache();
		//else if(i.getId()==14)
		//	return new ItemCoffre();
		else if(i.getId()==15)
			return new ItemFil();
		else if(i.getId()==16)
			return new ItemBarreMetal();
		return new ItemVide();
		
	}

}
