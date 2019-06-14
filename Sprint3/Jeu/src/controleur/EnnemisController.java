package controleur;

import javafx.scene.layout.Pane;
import modele.Monde;
import vue.Sprite;
import vue.SpriteSentinelle;

public class EnnemisController {

	private Sprite spriteSentinelle;
	private Pane coucheJoueur;
	private Monde game;

	public EnnemisController(Pane coucheJoueur, Monde game) {
		this.coucheJoueur = coucheJoueur;
		this.game = game;
		creerSprite();
	}

	public void creerSprite() {
		this.spriteSentinelle = new SpriteSentinelle(this.game.getSentinelle());
		coucheJoueur.getChildren().add(this.spriteSentinelle.getSprite());
		this.spriteSentinelle.getSprite().xProperty().bind(this.game.getSentinelle().getXProperty());
		this.spriteSentinelle.getSprite().yProperty().bind(this.game.getSentinelle().getYProperty());
	}

	public Sprite getSpriteSentinelle() {
		return this.spriteSentinelle;
	}

}