package modele.Blocks;

import modele.Items.Block.ItemPierre;

public class Pierre extends Block{
	public Pierre() {
		super('P',true, 1000, new ItemPierre());
	}
}
