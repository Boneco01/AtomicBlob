package modele.Items;

import modele.Monde;
import modele.Blocks.MineraiFer;

public class ItemMineraiFer extends ItemBlock {
	public ItemMineraiFer(Monde monde) {
		super(6, new MineraiFer(), monde);
	}
}
