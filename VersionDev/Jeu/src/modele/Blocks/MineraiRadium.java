package modele.Blocks;

import modele.Items.Item;
import modele.Items.Block.ItemMineraiRadium;

public class MineraiRadium extends Block {
	public MineraiRadium() {
		super('R',true, 2000);
	}

	@Override
	public Item itemADrop() {
		return new ItemMineraiRadium();
	}
}
