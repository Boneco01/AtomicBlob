package vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Sprite {
	
	private char spriteCode;
	private ImageView sprite;
	
	public Sprite(String cheminSprite) {
		this.spriteCode = 'b';
		this.sprite=new ImageView(new Image(cheminSprite));
		this.sprite.setFocusTraversable(true);
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
	
	public void setSpriteCode(char spriteCode) {
		this.spriteCode = spriteCode;
	}
	
	public abstract void changerSprite();
	
	
	
}
