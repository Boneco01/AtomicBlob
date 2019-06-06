package modele.Items.Craft;

import modele.Blocks.*;
import modele.Items.ItemCraft;

public class ItemPioche extends ItemCraft {
	public ItemPioche() {
		super(12, 1, 10, 2);
	}
	
	@Override
	public int efficacite(Block b) {
		if(b instanceof Pierre || b instanceof MineraiFer || b instanceof MineraiRadium) {
			return this.getDegatsBlocks()*5;
		} else {
			return this.getDegatsBlocks();
		}
	}

}
