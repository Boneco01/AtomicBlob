package modele.Craft;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import modele.Items.*;
import modele.Items.Block.ItemBois;
import modele.Items.Craft.ItemLingotFer;
import modele.Items.Craft.ItemPioche;

public class CraftPioche extends Craft {
	private ObservableList<Item> craft;
	
	public CraftPioche () {
		super(new ItemPioche());
		
	}
	public ArrayList<Item> definirCraft(){
		ArrayList<Item> a=new ArrayList<>();
		a.add(new ItemLingotFer());
		a.add(new ItemLingotFer());
		a.add(new ItemLingotFer());
		a.add(new ItemVide());
		a.add(new ItemBois());
		a.add(new ItemVide());
		a.add(new ItemBois());
		a.add(new ItemVide());
		return a;
	}
}
