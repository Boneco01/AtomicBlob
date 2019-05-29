package modele;

import java.util.ArrayList;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.Craft.*;
import modele.Items.Item;
import modele.Items.ItemCraft;
import modele.Items.ItemVide;

public class TableCraft {

	private ObservableList<Item> tc;
	private ArrayList<Craft> craftable;

	public TableCraft() {
		tc = FXCollections.observableList(tableVide());
		craftable = new ArrayList<>();
		rempliCraft();
	}

	private void rempliCraft() {
		craftable.add(new CraftPioche());
	}

	public Item aCraft() {
		for (int i = 0; i < craftable.size(); i++) {
			if (craftable.get(i).egale(tc))
				return craftable.get(i).getItem();
		}

		return new ItemVide();

	}

	public void add(Item m) {
		tc.add(m);
	}

	public void addMateriaux(Item m, int i) {
		tc.remove(i);
		tc.add(i, m);
	}

	public ArrayList<Item> tableVide() {
		ArrayList<Item> table = new ArrayList<Item>();
	/*	table.add(new ItemVide());// 1
		table.add(new ItemVide());// 2
		table.add(new ItemVide());// 3
		table.add(new ItemVide());// 4
		table.add(new ItemVide());// 5
		table.add(new ItemVide());// 6
		table.add(new ItemVide());// 7
		table.add(new ItemVide());// 8
		table.add(new ItemVide());// 9*/
		return table;
	}


	public ArrayList<Craft> getCraftable() {
		return craftable;
	}

	public ObservableList<Item> getTc() {
		return tc;
	}
}