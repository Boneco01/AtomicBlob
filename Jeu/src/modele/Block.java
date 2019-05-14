package modele;

public class Block {
	
	private char id;
	private boolean collision;
	
	public Block(char id, boolean collision) {
		this.id = id;
		this.collision = collision;
	}
	
	//Modif --> toString en getId
	public char getId() {
		return this.id;
	}
	
	public boolean getCollision() {
		return this.collision;
	}
	
}
