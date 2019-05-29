package modele.Craft;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.Items.Item;
import modele.Items.ItemCraft;

public abstract class Craft {

	private ArrayList<Item> shema;
	private ItemCraft cree;
	
	public Craft(ItemCraft item) {
		cree=item;
		this.shema= definirCraft();
	}
	public ArrayList<Item> getShema(){
		return shema;
	}
	public abstract ArrayList<Item> definirCraft();
	
	public ItemCraft getItem() {
		return cree;	
	}
	public boolean egale(ObservableList<Item> table) {
		for(int i=0;i<shema.size();i++)
			if((table.get(i).getClass()!=shema.get(i).getClass()))
			return false;
		return true;

	}
	

}
