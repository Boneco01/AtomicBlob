package modele.Craft;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import modele.Items.Item;
import modele.Items.ItemCraft;

public abstract class Craft {

	private ArrayList<Item> schema;
	private ItemCraft cree;
	
	public Craft(ItemCraft item) {
		cree=item;
		this.schema= definirCraft();
	}
	public ArrayList<Item> getShema(){
		return schema;
	}
	public abstract ArrayList<Item> definirCraft();
	
	public ItemCraft getItem() {
		return cree;	
	}
	public boolean egale(ObservableList<Item> table) {
		for(int i=0;i<schema.size();i++)
			if((table.get(i).getClass()!=schema.get(i).getClass()))
			return false;
		return true;

	}
	

}
