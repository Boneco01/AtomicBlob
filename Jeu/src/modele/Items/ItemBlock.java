package modele.Items;

import modele.Blocks.Block;

public class ItemBlock extends Item {

	private Block blockCorrespondant;
	private char id;
	
	public ItemBlock(char id, Block blockCorrespondant) {
		super(64);
		this.blockCorrespondant = blockCorrespondant;
		this.id = id;
	}
	
}
