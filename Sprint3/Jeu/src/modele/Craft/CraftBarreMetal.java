package modele.Craft;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import modele.Items.*;
import modele.Items.Block.ItemBois;
import modele.Items.Block.ItemPierre;
import modele.Items.Craft.ItemBarreMetal;
import modele.Items.Craft.ItemLingotFer;
import modele.Items.Craft.ItemPioche;

public class CraftBarreMetal extends Craft {
	private ObservableList<Item> craft;
	
	@Override
	public ArrayList<Item> definirComsomation(){
		ArrayList<Item> a=new ArrayList<>();
		a.add(new ItemVide());
		a.add(new ItemLingotFer());
		a.add(new ItemVide());
		a.add(new ItemVide());
		a.add(new ItemLingotFer());
		a.add(new ItemVide());
		a.add(new ItemVide());
		a.add(new ItemLingotFer());
		a.add(new ItemVide());
		return a;
	}
	@Override
	public ItemCraft creeItem() {
		
		return new ItemBarreMetal();
	}
}
