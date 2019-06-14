package modele.Craft;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import modele.Items.*;
import modele.Items.Block.ItemBois;
import modele.Items.Block.ItemPierre;
import modele.Items.Craft.ItemHache;

public class CraftHache extends Craft {
	private ObservableList<Item> craft;
	@Override
	public ArrayList<Item> definirConsommation(){
		ArrayList<Item> a=new ArrayList<>();
		a.add(new ItemPierre());
		a.add(new ItemPierre());
		a.add(new ItemVide());
		a.add(new ItemPierre());
		a.add(new ItemBois());
		a.add(new ItemVide());
		a.add(new ItemVide());
		a.add(new ItemBois());
		a.add(new ItemVide());
		return a;
	}
	@Override
	public ItemCraft creeItem() {
		
		return new ItemHache();
	}
}
