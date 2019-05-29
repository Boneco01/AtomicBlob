package modele.Items;

public abstract class ItemCraft extends Item {

	private int id;
	
	public ItemCraft(int id,int quantiteMax) {
		super(id, quantiteMax);
	}
	
}
