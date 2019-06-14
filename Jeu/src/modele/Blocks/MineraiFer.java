package modele.Blocks;

import modele.Items.Item;
import modele.Items.Block.ItemMineraiFer;

public class MineraiFer extends Block{
	public MineraiFer() {
		super('F',true, 1500);
	}

	@Override
	public Item itemADrop() {
		return new ItemMineraiFer();
	}
}
