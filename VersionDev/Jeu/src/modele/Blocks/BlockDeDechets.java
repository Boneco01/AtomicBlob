package modele.Blocks;

import modele.Items.Item;
import modele.Items.Block.ItemMineraiRadium;
import modele.Items.Craft.*;

public class BlockDeDechets extends Block {
	public BlockDeDechets() {
		super('D',true, 200);
	}
	
	@Override
	public Item itemADrop() {
		int alea = (int) (Math.random()*10);
		if(alea < 7) {
			return new ItemFil();
		} else if(alea < 9) {
			return new ItemMineraiRadium();
		} else {
			return new ItemLingotFer();
		}
	}
}
