package modele.Items;

import modele.Monde;
import modele.Blocks.Sable;

public class ItemSable extends ItemBlock {
	public ItemSable(Monde monde) {
		super(5, new Sable(), monde);
	}
}
