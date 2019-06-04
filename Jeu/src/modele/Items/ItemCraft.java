package modele.Items;
import modele.Monde;

public abstract class ItemCraft extends Item {
	

	public ItemCraft(int id , int quantiteMax, int degatsBlocks) {
		super(id, quantiteMax, 3, degatsBlocks);

	}

	@Override
	public void utiliser(Monde monde) {
	}
	
}
