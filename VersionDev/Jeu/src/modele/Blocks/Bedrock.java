package modele.Blocks;

import modele.Items.Item;
import modele.Items.ItemVide;

public class Bedrock extends Block {

	public Bedrock() {
		super('X',true, 1);
	}
	
	@Override
	public Item itemADrop() {
		return new ItemVide();
	}
	
}
