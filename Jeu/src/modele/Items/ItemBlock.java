package modele.Items;

import modele.Blocks.Block;

public abstract class ItemBlock extends Item {

	private Block blockCorrespondant;
	
	public ItemBlock(int id, Block blockCorrespondant) {
		super(id, 64);
		this.blockCorrespondant = blockCorrespondant;
	}
	
	public Block getBlockCorrespondant() {
		return this.blockCorrespondant;
	}
	
}
