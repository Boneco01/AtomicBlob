package modele.Blocks;

import modele.Items.Item;
import modele.Items.Block.ItemTerre;

public class Herbe extends Block {
	public Herbe() {
		super('H', true, 250);
	}

	@Override
	public Item itemADrop() {
		return new ItemTerre();
	}
}
