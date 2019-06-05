package modele.Blocks;

public abstract class Block {
	
	private char id;
	private int taillePx = 64;
	private boolean collision;
	private int resistanceTotale;
	private int resistanceRestante;
	
	public Block(char id, boolean collision, int resistanceTotale) {
		this.id = id;
		this.collision = collision;
		this.resistanceTotale=resistanceTotale;
		this.resistanceRestante=resistanceTotale;
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
	
}
