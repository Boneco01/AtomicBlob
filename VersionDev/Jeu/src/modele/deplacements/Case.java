package modele.deplacements;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Case {
	private Case parent;
	private int x;
	private int y;
	private boolean traversable;
	private boolean visitee;
	private boolean chemin;
	private IntegerProperty gCost; //distance depuis la case de depart
	private IntegerProperty hCost; //distance depuis la case d'arrivee
	private IntegerProperty fCost; //gCost + hCost
	
	public Case(int x, int y, boolean traversable) {
		this.gCost=new SimpleIntegerProperty();
		this.hCost=new SimpleIntegerProperty();
		this.fCost=new SimpleIntegerProperty();
		this.x=x;
		this.y=y;
		this.traversable=traversable;
		this.visitee=false;
		this.chemin=false;
	}
	
	public void setVisitee(boolean a) {
		this.visitee=a;
	}
	
	public void setChemin(boolean a) {
		this.chemin=a;
	}
	
	public void setParent(Case parent) {
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
	
	public void setTraversable(boolean a) {
		this.traversable=a;
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
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public boolean getTraversable() {
		return this.traversable;
	}
	
	public boolean getVisitee() {
		return this.visitee;
	}
	
	public boolean getChemin() {
		return this.chemin;
	}
	
	public Case getParent() {
		return this.parent;
	}
	
	public String toString() {
		String affichage="";
		affichage+="x : "+this.x+" y : "+this.y+"\n G : "+this.gCost.getValue()+"\n H : "+this.hCost.getValue()+"\n F : "+this.fCost.getValue()+"\n\n";
		return affichage;
	}
	
}

