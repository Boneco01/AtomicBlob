package modele.Items;

import modele.Joueur;
import modele.Monde;
import modele.Personnage;
import modele.Portee;

public abstract class Item {

	private int id;
	private int quantitee;
	private int quantiteeMax;
	private Portee portee;
	protected Monde monde;
	
	public Item(int id, int quantiteeMax, Monde monde, int distance) {
		this.quantitee = 0;
		this.quantiteeMax = quantiteeMax;
		this.id = id;
		this.monde=monde;
		this.portee=new Portee(distance);
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
	
	public Portee getPortee() {
		return this.portee;
	}
	
	public Monde getMonde() {
		return this.monde;
	}
	
	public abstract void utiliser();
	
}
