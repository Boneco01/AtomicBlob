package modele.Blocks;

import modele.Items.Item;

public abstract class Block {
	
	private char id;
	private int taillePx = 64;
	private boolean collision;
	private int resistanceTotale;
	private int resistanceRestante;
	private Item itemCorrespondant;
	
	public Block(char id, boolean collision, int resistanceTotale, Item itemCorrespondant) {
		this.id = id;
		this.collision = collision;
		this.resistanceTotale=resistanceTotale;
		this.resistanceRestante=resistanceTotale;
		this.itemCorrespondant = itemCorrespondant;
	}
	
	//Modif --> toString en getId
	public char getId() {
		return this.id;
	}
	
	public int getTaillePx() {
		return this.taillePx;
	}
	
	public boolean getCollision() {
		return this.collision;
	}
	
	public int getResistanceRestante() {
		return this.resistanceRestante;
	}
	
	public void reinitialiserResistanceRestante() {
		this.resistanceRestante=this.resistanceTotale;
	}
	
	public void setResistanceRestante(int a) {
		this.resistanceRestante=a;
	}
	
	public void seDetruire(int degats) {
		 this.resistanceRestante-=degats;
	}
	
	public Item itemADrop() {
		return this.itemCorrespondant;
	}
	
}
