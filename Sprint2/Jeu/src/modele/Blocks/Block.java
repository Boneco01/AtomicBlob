package modele.Blocks;

public abstract class Block {
	
	private char id;
	private int taillePx = 64;
	private boolean collision;
	
	public Block(char id, boolean collision) {
		this.id = id;
		this.collision = collision;
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
	
}
