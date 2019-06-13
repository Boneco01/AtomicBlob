package vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Sprite {
	
	private char spriteCode;
	private ImageView sprite;
	private int tempsAnim;
	
	public Sprite(String cheminSprite) {
		this.spriteCode = 'b';
		this.sprite=new ImageView(new Image(cheminSprite));
		this.sprite.setFocusTraversable(true);
		this.tempsAnim = 10;
	}
	
	public ImageView getSprite() {
		return this.sprite;
	}
	
	public void setSprite(String sprite) {
		this.sprite.setImage(new Image(sprite));
	}
	
	public char getSpriteCode() {
		return this.spriteCode;
	}
	
	public int getTempsAnime() {
		return this.tempsAnim;
	}
	
	public void setTempsAnime(int temps) {
		this.tempsAnim = temps;
	}
	
	public void resetTempsAnime() {
		this.tempsAnim = 10;
	}
	
	public void setSpriteCode(char spriteCode) {
		this.spriteCode = spriteCode;
	}
	
	public void deleteImageView() {
		this.sprite.setVisible(false);
	}
	
	public abstract void changerSprite();
	
	
	
}
