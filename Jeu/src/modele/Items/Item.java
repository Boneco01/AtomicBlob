package modele.Items;

public abstract class Item {

	private int id;
	private int quantitee;
	private int quantiteeMax;
	
	public Item(int id, int quantiteeMax) {
		this.quantitee = 1;
		this.quantiteeMax = quantiteeMax;
		this.id = id;
	}
	
	public int getQuantitee() {
		return this.quantitee;
	}
	
	public void setQuantitee(int quantitee) {
		this.quantitee = quantitee;
	}
	
	public int getQuantiteeMax() {
		return this.quantiteeMax;
	}
	
	public int getId() {
		return this.id;
	}
	
	
}
