package modele.Combustion;

import java.util.ArrayList;

import modele.Items.Item;
import modele.Items.ItemCraft;
import modele.Items.Block.ItemMineraiFer;
import modele.Items.Block.ItemMineraiRadium;
import modele.Items.Craft.ItemLingotFer;

public class FondableLingotFer extends Fondable {
	@Override
	public ItemCraft creeItem() {
		return new ItemLingotFer();
	}
	@Override
	public ArrayList<Item> definirComsomation() {
		ArrayList<Item> c =new ArrayList<>();;
		c.add(new ItemMineraiFer());
		c.add(new ItemMineraiRadium());
		return c;
	}

}
