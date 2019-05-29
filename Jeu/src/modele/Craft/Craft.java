package modele.Craft;

import java.util.ArrayList;
import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.Blocks.Block;
import modele.Items.Item;
import modele.Items.ItemCraft;

public abstract class Craft {

	private ObservableList<Item> craft;
	private ItemCraft cree;
	
	public Craft(ItemCraft item,ArrayList<Item> craft) {
		cree=item;
		this.craft= FXCollections.observableList(craft);
	}
	public ObservableList<Item> getCraft(){
		return craft;
	}
	public abstract ArrayList<Item> definirCraft();

}
