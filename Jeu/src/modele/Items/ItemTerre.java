package modele.Items;

import modele.Monde;
import modele.Blocks.Terre;

public class ItemTerre extends ItemBlock {
	
	public ItemTerre(Monde monde) {
		super(1, new Terre(), monde);
	}
	
}
