package modele.Blocks;

import modele.Items.Item;

import javafx.beans.property.IntegerProperty;

public abstract class Block {
	
	private char id;
	private Block parent;
	private int x;
	private int y;
	private boolean visitee;
	private boolean chemin;
	private IntegerProperty gCost; //distance depuis la case de depart
	private IntegerProperty hCost; //distance depuis la case d'arrivee
	private IntegerProperty fCost; //gCost + hCost
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
		this.visitee=false;
		this.chemin=false;
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
	
	public void setVisitee(boolean a) {
		this.visitee=a;
	}
	
	public void setChemin(boolean a) {
		this.chemin=a;
	}
	
	public void setParent(Block parent) {
		this.parent=parent;
	}
	
	public void setGCost(int g) {
		this.gCost.setValue(g);
	}
	
	public void setHCost(int h) {
		this.hCost.setValue(h);
	}
	
	public void calculerFCost() {
		this.fCost.setValue(this.gCost.getValue()+this.hCost.getValue());
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public IntegerProperty getGCost() {
		return this.gCost;
	}
	
	public IntegerProperty getHCost() {
		return this.hCost;
	}
	
	public IntegerProperty getFCost() {
		return this.fCost;
	}
	
	public boolean getVisitee() {
		return this.visitee;
	}
	
	public boolean getChemin() {
		return this.chemin;
	}
	
	public Block getParent() {
		return this.parent;
	}
	
	public Item itemADrop() {
		return this.itemCorrespondant;
	}
	
}
