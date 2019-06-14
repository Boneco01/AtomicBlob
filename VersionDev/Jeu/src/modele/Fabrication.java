package modele;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import modele.Items.Item;
import modele.Items.ItemCraft;

public abstract class Fabrication {
	
	protected ArrayList<Item> comsomation;
	
	public Fabrication() {
		this.comsomation=definirComsomation();
	}
	public abstract ArrayList<Item> definirComsomation();
	public abstract ItemCraft creeItem();
	public abstract boolean egale(ObservableList<Item> table);

}
