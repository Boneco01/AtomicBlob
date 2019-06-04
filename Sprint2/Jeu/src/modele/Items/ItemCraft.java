package modele.Items;
import modele.Monde;

public abstract class ItemCraft extends Item {
	

	public ItemCraft(int id , int quantiteMax) {
		super(id, quantiteMax, 3);

	}

	@Override
	public void utiliser(Monde monde) {
	}
	
}
