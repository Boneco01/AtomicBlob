package modele.Items;

import modele.Monde;
import modele.Blocks.Bois;

public class ItemBois extends ItemBlock{
	public ItemBois(Monde monde) {
		super(4, new Bois(), monde);
	}
}
