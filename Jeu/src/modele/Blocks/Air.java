package modele.Blocks;

import modele.Items.Item;
import modele.Items.ItemVide;

public class Air extends Block {
	public Air() {
		super('A',false, 1);
	}

	@Override
	public Item itemADrop() {
		return new ItemVide();
	}

}
