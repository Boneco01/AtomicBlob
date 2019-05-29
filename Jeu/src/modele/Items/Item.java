package modele.Items;

import modele.Monde;
import modele.Portee;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Item {

	private int id;
	private IntegerProperty quantitee;
	private int quantiteeMax;
	private Portee portee;
	protected Monde monde;
	
	public Item(int id, int quantiteeMax, int distance, Monde monde) {
		this.quantitee = new SimpleIntegerProperty(1);
		this.quantiteeMax = quantiteeMax;
		this.id = id;
		this.portee=new Portee(distance);
		this.monde=monde;
	}
	public int getQuantitee() {
		return this.quantitee.getValue();
	}
	
	public IntegerProperty quantiteeProperty() {
		return this.quantitee;
	}
	
	public void setQuantitee(int quantitee) {
		this.quantitee.setValue(quantitee);;
	}
	
	public int getQuantiteeMax() {
		return this.quantiteeMax;
	}
	
	public int getId() {
		return this.id;
	}
	
	public Portee getPortee() {
		return this.portee;
	}
	
	public Monde getMonde() {
		return this.monde;
	}
	
	public abstract void utiliser(Monde monde);
	
}
