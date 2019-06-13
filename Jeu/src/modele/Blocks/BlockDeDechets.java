package modele.Blocks;

import modele.Items.Item;
import modele.Items.ItemVide;

public class BlockDeDechets extends Block {
	public BlockDeDechets() {
		super('D',true, 200, new ItemVide());
	}
	
	@Override
	public Item itemADrop() {
		return new ItemVide(); //Drop aléatoire
	}
}
