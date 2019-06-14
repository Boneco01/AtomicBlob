package controleur;

import java.util.ArrayList;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import modele.Drone;
import modele.Ennemi;
import modele.Monde;
import modele.Sentinelle;
import vue.Sprite;
import vue.SpriteDrone;
import vue.SpriteSentinelle;

public class EnnemisController {

	private Pane coucheJoueur;
	private Monde game;
	private ArrayList<Sprite> spriteEnnemis;

	public EnnemisController(Pane coucheJoueur, Monde game) {
		this.coucheJoueur = coucheJoueur;
		this.game = game;
		this.spriteEnnemis = new ArrayList<Sprite>();
		creerSprite();
		ecouterEnnemis();
	}

	public void creerSprite() {
		for(Ennemi e : this.game.getEnnemis()) {
			Sprite spriteEnnemi = spriteCorrespondant(e);
			coucheJoueur.getChildren().add(spriteEnnemi.getSprite());
			spriteEnnemi.getSprite().xProperty().bind(e.getXProperty());
			spriteEnnemi.getSprite().yProperty().bind(e.getYProperty());
			this.spriteEnnemis.add(spriteEnnemi);
		}
	}
	
	public void ecouterEnnemis() {
		this.game.getEnnemis().addListener(new ListChangeListener<Ennemi>() {

			@Override
			public void onChanged(Change<? extends Ennemi> c) {
				while (c.next()) {
                  if (c.wasRemoved()) {
                	  removeSpriteEnnemi(c.getFrom());
                  } else if (c.wasAdded()) {
                	  creerSpriteEnnemi(c.getFrom());
                  }
              }
			}
	    });
	}
	
	public void removeSpriteEnnemi(int index) {
		//this.spriteEnnemis.get(index).changerSprite();
		this.spriteEnnemis.get(index).setSpriteMort();
		this.spriteEnnemis.remove(index);
	}
	
	public void creerSpriteEnnemi(int index) {
		Sprite spriteEnnemi = spriteCorrespondant(this.game.getEnnemis().get(index));
		coucheJoueur.getChildren().add(spriteEnnemi.getSprite());
		spriteEnnemi.getSprite().xProperty().bind(this.game.getEnnemis().get(index).getXProperty());
		spriteEnnemi.getSprite().yProperty().bind(this.game.getEnnemis().get(index).getYProperty());
		this.spriteEnnemis.add(spriteEnnemi);
	}
	
	public Sprite spriteCorrespondant(Ennemi e) {
		if(e instanceof Sentinelle) {
			Sentinelle s = (Sentinelle)e;
			return new SpriteSentinelle(s);
		} else {
			Drone d = (Drone)e;
			return new SpriteDrone(d);
		}
	}
	
	public Sprite getSpriteEnnemi(int index) {
		return this.spriteEnnemis.get(index);
	}

}