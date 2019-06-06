package modele.Craft;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import modele.Items.Item;
import modele.Items.ItemCraft;

public abstract class Craft {

	private ArrayList<Item> schema;

	public Craft() {
		this.schema = definirCraft();
	}

	public ArrayList<Item> getShema() {
		return schema;
	}

	public abstract ArrayList<Item> definirCraft();

	public abstract ItemCraft creeItem();

	public boolean egale(ObservableList<Item> table) {
		for (int index = 0; index < schema.size(); index++) {
			if ((table.get(index).getClass() != schema.get(index).getClass())) {
				System.out.println(table.get(index).getClass() + "=" + schema.get(index).getClass());
				return false;
			}
		}
		return true;

	}

}
