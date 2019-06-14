package modele.Craft;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import modele.Items.*;
import modele.Items.Block.ItemBois;
import modele.Items.Block.ItemPierre;
import modele.Items.Craft.ItemFil;
import modele.Items.Craft.ItemLancePierre;

public class CraftLancePierre extends Craft {
	private ObservableList<Item> craft;
	@Override
	public ArrayList<Item> definirComsomation(){
		ArrayList<Item> a=new ArrayList<>();
		a.add(new ItemBois());
		a.add(new ItemPierre());
		a.add(new ItemBois());
		a.add(new ItemFil());
		a.add(new ItemBois());
		a.add(new ItemFil());
		a.add(new ItemVide());
		a.add(new ItemBois());
		a.add(new ItemVide());
		return a;
	}
	@Override
	public ItemCraft creeItem() {
		
		return new ItemLancePierre();
	}
}
