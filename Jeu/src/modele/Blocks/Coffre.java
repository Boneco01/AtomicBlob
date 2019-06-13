package modele.Blocks;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.Items.Item;
import modele.Items.ItemVide;

public class Coffre extends Block {
	
	private ObservableList<Item> contenu;
	private int limiteContenu;
	private int nbItems;
	
	public Coffre() {
		super('C',true,300, null); //Doit correspondre un itemCoffre
		this.contenu = FXCollections.observableList(new ArrayList<Item>());
		this.limiteContenu = 5;
		this.nbItems = 0;
	}
	
	public ObservableList<Item> getContenu() {
		return this.contenu;
	}
	
	public Item getItem(int index) {
		return this.contenu.get(index);
	}
	
	public boolean addItem(Item item) {
		
		if(item instanceof ItemVide) {
			return false;
		}
		
		for(Item i : this.contenu) {
			if(i.getId()==item.getId()) {
				if(i.getQuantitee() < i.getQuantiteeMax()) {
					i.setQuantitee(i.getQuantitee()+1);
					return true;
				}
			}
		}
		
		if(this.nbItems < this.limiteContenu) {
			for(int i=0;i<this.limiteContenu;i++) {
				if(this.contenu.get(i).getId()==0) {
					this.contenu.set(i, item);
					this.nbItems++;
					return true;
				}
			}
		}
		
		return false;
	}
	
}
