package controleur;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import modele.Monde;
import vue.Sprite;
import vue.SpriteJoueur;

public class JoueurController {

	private Sprite spriteJoueur;
	private Pane coucheJoueur;
	private Monde game;
	
	public JoueurController(Pane coucheJoueur, Monde game) {
		this.coucheJoueur = coucheJoueur;
		this.game = game;
		creerSprite();
	}
	
	public void creerSprite() {
    	this.spriteJoueur=new SpriteJoueur(this.game.getJoueur());
    	coucheJoueur.getChildren().add(this.spriteJoueur.getSprite());
    	this.spriteJoueur.getSprite().xProperty().bind(this.game.getJoueur().getXProperty());
    	this.spriteJoueur.getSprite().yProperty().bind(this.game.getJoueur().getYProperty());
    	this.spriteJoueur.getSprite().setOnKeyPressed(e -> gererFlechesAppuyees(e));
        this.spriteJoueur.getSprite().setOnKeyReleased(e -> gererFlechesRelachees(e));
    }
	
	public void gererFlechesAppuyees(KeyEvent e) {
        switch (e.getCode()) {
            case UP:    this.game.getJoueur().setHaut(true); break;
            case LEFT:  this.game.getJoueur().setGauche(true); break;
            case RIGHT: this.game.getJoueur().setDroite(true); break;
        default: break;
        }
    }
    
    public void gererFlechesRelachees(KeyEvent e) {
    	switch (e.getCode()) {
        	case UP:    this.game.getJoueur().setHaut(false); break;
        	case LEFT:  this.game.getJoueur().setGauche(false); break;
        	case RIGHT: this.game.getJoueur().setDroite(false); break;
        	default: break;
    	}
    }
    
    public Sprite getSpriteJoueur() {
    	return this.spriteJoueur;
    }
	
	
}
