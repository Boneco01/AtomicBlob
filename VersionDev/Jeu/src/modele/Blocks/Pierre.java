package modele.Blocks;

import modele.Items.Item;
import modele.Items.Block.ItemPierre;

public class Pierre extends Block{
	public Pierre() {
		super('P',true, 1000);
	}

	@Override
	public Item itemADrop() {
		return new ItemPierre();
	}
}
