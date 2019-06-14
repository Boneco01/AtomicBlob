package modele.Blocks;

import modele.Items.Item;
import modele.Items.ItemVide;

public class BidonRadioactif extends Block {
	
	public BidonRadioactif() {
		super('I',true,500);
	}
	
	@Override
	public Item itemADrop() {
		return new ItemVide();
	}
	
}
