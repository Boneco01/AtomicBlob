package modele.Blocks;

import modele.Items.Block.ItemTerre;

public class Herbe extends Block {
	public Herbe() {
		super('H', true, 250, new ItemTerre());
	}
}
