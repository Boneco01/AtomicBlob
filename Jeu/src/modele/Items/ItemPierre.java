package modele.Items;

import modele.Monde;
import modele.Blocks.Pierre;

public class ItemPierre extends ItemBlock {
	public ItemPierre(Monde monde) {
		super(2, new Pierre(), monde);
	}
}
