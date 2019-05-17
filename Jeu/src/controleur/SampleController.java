package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import modele.Air;
import modele.Block;
import modele.Monde;
import modele.Terre;
import javafx.scene.control.ScrollPane;
import vue.Sprite;
import vue.SpriteJoueur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;



public class SampleController implements Initializable{

    private Sprite spriteJoueur;
    
    @FXML
    private Pane coucheJoueur;
    
    @FXML
    private ScrollPane vision;
    
    @FXML
    private TilePane terrain;
    
    public void creerSprite() {
    	this.spriteJoueur=new SpriteJoueur(this.game.getJoueur());
    	coucheJoueur.getChildren().add(this.spriteJoueur.getSprite());
    	this.spriteJoueur.getSprite().xProperty().bind(this.game.getJoueur().getXProperty());
    	this.spriteJoueur.getSprite().yProperty().bind(this.game.getJoueur().getYProperty());
    	this.spriteJoueur.getSprite().setOnKeyPressed(e -> gererFlechesAppuyees(e));
        this.spriteJoueur.getSprite().setOnKeyReleased(e -> gererFlechesRelachees(e));
    } 
    
    public void creerTerrain() {

   	 	vision.setPannable(true);
    	terrain.setMinSize(game.getMap().largeurMap()*64, 64*game.getMap().hauteurMap());
        terrain.setMaxSize(game.getMap().largeurMap()*64, 64*game.getMap().hauteurMap());
        for(int i=0;i<this.game.getMap().getMap().size();i++) {
                ImageView png = imageDe(this.game.getMap().getMap().get(i));
                this.terrain.getChildren().add(png);
            
        }
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
    
    public ImageView imageDe(Block b) {
        if (b instanceof Terre)
            return new ImageView(new Image("file:../Sprites/Block/Terre.png"));
        else if (b instanceof Air)
            return new ImageView(new Image("file:../Sprites/Block/Air.png"));
        else
            return new ImageView(new Image("file:../Sprites/Block/Air.png"));
    }
    
    private Monde game;
    
    private void initAnimation() {
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // Nombre de fps
                Duration.seconds(0.015),
                // A chaque frame
                (ev ->{
                    
                    if (this.game.getJoueur().getVie()==0) {
                        gameLoop.stop();
                    }

                    this.game.getJoueur().agir();
                    this.spriteJoueur.changerSprite();
                    
                })
        );
        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.game = new Monde();
        creerSprite();
        creerTerrain();
        initAnimation();
	}
    
}





