package modele.Items.Craft;

import modele.Blocks.*;
import modele.Items.ItemCraft;

public class ItemHache extends ItemCraft{

	public ItemHache() {
		super(13, 1, 10, 2);
		this.setDurabilite(50);
	}
	
	@Override
	public int efficacite(Block b) {
		if(b instanceof Bois) {
			return this.getDegatsBlocks()*5;
		} else {
			return this.getDegatsBlocks();
		}
	}
	
}
