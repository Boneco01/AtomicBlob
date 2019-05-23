package modele.Items;

public abstract class Item {

	private int quantitee;
	private int quantiteeMax;
	
	public Item(int quantiteeMax) {
		this.quantitee = 0;
		this.quantiteeMax = quantiteeMax;
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
	
	
	
}
