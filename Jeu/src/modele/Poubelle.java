package modele;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.Items.Item;
import modele.Items.ItemVide;

public class Poubelle {

	private ObservableList<Item> sac;

	public Poubelle(ArrayList<Item> sacJetable) {
		sac = FXCollections.observableArrayList(sacJetable);
	}

	public Item recupererItem(int index) {
		Item i = sac.get(index);
		sac.set(index, new ItemVide());
		return i;
	}

	public int nbItem() {
		int nb = 0;
		for (int i = 0; i < sac.size(); i++)
			if (sac.get(i).getClass() != new ItemVide().getClass())
				nb++;
		return nb;
	}

	private int sameItem(Item i) {
		if(i.getQuantiteeMax()==1)
			return -1;
		for (int index = 0; index < sac.size(); index++) {
			if (sac.get(index).getClass() == i.getClass()&&sac.get(index).getQuantitee()<sac.get(index).getQuantiteeMax())
				return index;
		}
		return -1;
	}

	private int itemVide() {
		for (int index = 0; index < sac.size(); index++) {
			if (sac.get(index).getClass() == new ItemVide().getClass())
				return index;
		}
		return -1;
	}

	public boolean ajouterItem(Item i, int index) {
		if (nbItem() < sac.size()) {
			if (sameItem(i) >= 0) {
				while(sameItem(i)>=0 && i.getQuantitee()>0) {
					sac.get(sameItem(i)).setQuantitee(sac.get(sameItem(i)).getQuantitee()+1);
					i.setQuantitee(i.getQuantitee()-1);
				}
			} 
			else {
				if (sac.get(index).getClass() == new ItemVide().getClass()) {
					sac.set(index, i);
					return true;
				} else {
					sac.set(itemVide(), sac.get(index));
					sac.set(index, i);
					return true;
				}
			}
		}
		return false;
	}
	public ObservableList<Item> getSac() {
		return sac;
	}
}
