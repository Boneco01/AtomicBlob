package modele;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.Combustion.FondableLingotFer;
import modele.Craft.*;
import modele.Items.Item;
import modele.Items.ItemVide;

public class TableCraft {

	private ObservableList<Item> tc;
	private ArrayList<Fabrication> craftable;

	public TableCraft() {
		tc = FXCollections.observableList(tableVide());
		craftable = new ArrayList<>();
		rempliCraft();
	}

	private void rempliCraft() {
		craftable.add(new CraftPioche());
		craftable.add(new CraftHache());
		craftable.add(new CraftBarreMetal());
		craftable.add(new CraftLancePierre());
		craftable.add(new FondableLingotFer());
	}

	public Item aCraft() {
		for (int i = 0; i < craftable.size(); i++) {
			if (craftable.get(i).egale(tc))
				return craftable.get(i).creeItem();
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

	public void setTableVide() {
		for (int index = 0; index < tc.size();)
			tc.remove(index);
		System.out.println(tc.size());
		tc.add(new ItemVide());// 1
		tc.add(new ItemVide());// 2
		tc.add(new ItemVide());// 3
		tc.add(new ItemVide());// 4
		tc.add(new ItemVide());// 5
		tc.add(new ItemVide());// 6
		tc.add(new ItemVide());// 7
		tc.add(new ItemVide());// 8
		tc.add(new ItemVide());// 9
	}

	public ArrayList<Item> tableVide() {
		ArrayList<Item> table = new ArrayList<Item>();
		table.add(new ItemVide());// 1
		table.add(new ItemVide());// 2
		table.add(new ItemVide());// 3
		table.add(new ItemVide());// 4
		table.add(new ItemVide());// 5
		table.add(new ItemVide());// 6
		table.add(new ItemVide());// 7
		table.add(new ItemVide());// 8
		table.add(new ItemVide());// 9
		return table;
	}

	public ArrayList<Fabrication> getCraftable() {
		return craftable;
	}

	public ObservableList<Item> getTc() {
		return tc;
	}

}