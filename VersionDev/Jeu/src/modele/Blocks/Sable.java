package modele.Blocks;

import modele.Items.Item;
import modele.Items.Block.ItemSable;

public class Sable extends Block{
	public Sable() {
		super('S',true, 250);
	}

	@Override
	public Item itemADrop() {
		return new ItemSable();
	}
}
