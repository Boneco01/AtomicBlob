package modele;

public class Block {
	
	private char id;
	private int taille;
	private String sprite;
	
	public Block(char id) {
		this.id = id;
		this.taille = 16;
		this.sprite = spriteById();
	}
	
}
