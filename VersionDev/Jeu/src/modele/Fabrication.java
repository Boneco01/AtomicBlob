package modele;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import modele.Items.Item;
import modele.Items.ItemCraft;

public abstract class Fabrication {
	
	protected ArrayList<Item> consommation;
	
	public Fabrication() {
		this.consommation=definirConsommation();
	}
	public abstract ArrayList<Item> definirConsommation();
	public abstract ItemCraft creeItem();
	public abstract boolean egale(ObservableList<Item> table);

}
