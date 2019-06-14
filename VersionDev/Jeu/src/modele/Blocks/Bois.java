package modele.Blocks;

import modele.Items.Item;
import modele.Items.Block.ItemBois;

public class Bois extends Block{
	public Bois() {
		super('B',false,500);
	}

	@Override
	public Item itemADrop() {
		return new ItemBois();
	}
}
