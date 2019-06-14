package modele.Blocks;

import modele.Items.Item;
import modele.Items.Block.ItemTerre;

public class Terre extends Block {
	public Terre() {
		super('T',true, 250);
	}

	@Override
	public Item itemADrop() {
		return new ItemTerre();
	}

}
