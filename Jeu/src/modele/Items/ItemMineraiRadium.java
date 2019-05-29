package modele.Items;

import modele.Monde;
import modele.Blocks.MineraiRadium;

public class ItemMineraiRadium extends ItemBlock {
	public ItemMineraiRadium(Monde monde) {
		super(7, new MineraiRadium(), monde);
	}
}
