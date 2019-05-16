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
import modele.Jeu;
import modele.Terre;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;



public class SampleController implements Initializable{

    private Sprite spriteJoueur;
    
    @FXML
    private Pane coucheJoueur;
    
    @FXML
    private TilePane terrain;
    
    public void creerSprite() {
    	coucheJoueur.getChildren().add(this.spriteJoueur.getImage());
    	this.spriteJoueur.getImage().xProperty().bind(this.spriteJoueur.getPersonnage().getX());
    	this.spriteJoueur.getImage().yProperty().bind(this.spriteJoueur.getPersonnage().getY());
    	
    	
    }  
    
    public void gererFlechesAppuyees(KeyEvent e) {
        switch (e.getCode()) {
            case UP:    this.spriteJoueur.getPersonnage().setHaut(true); break;
            case LEFT:  this.spriteJoueur.getPersonnage().setGauche(true); break;
            case RIGHT: this.spriteJoueur.getPersonnage().setDroite(true); break;
        default: break;
        }
    }
    
    public void gererFlechesRelachees(KeyEvent e) {
    	switch (e.getCode()) {
        	case UP:    this.spriteJoueur.getPersonnage().setHaut(false); break;
        	case LEFT:  this.spriteJoueur.getPersonnage().setGauche(false); break;
        	case RIGHT: this.spriteJoueur.getPersonnage().setDroite(false); break;
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
    
    //private Timeline gameLoop;
    
    private Jeu game;
    
    private void initAnimation() {
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // Nombre de fps
                Duration.seconds(0.017),
                // A chaque frame
                (ev ->{
                    
                    if (this.game.getJoueur().getVie()==0) {
                        gameLoop.stop();
                    }
                    
                    if (this.spriteJoueur.getPersonnage().getGauche() &&
                    		this.game.gererCollision(this.game.getJoueur(), this.game.getMap(), -this.spriteJoueur.getPersonnage().getHauteur()+50, 0)){
                    	this.spriteJoueur.getPersonnage().goGauche();
                    }
                    
                    if (this.spriteJoueur.getPersonnage().getDroite() &&
                    		this.game.gererCollision(this.game.getJoueur(), this.game.getMap(), this.spriteJoueur.getPersonnage().getHauteur()+30, 0)){
                    	this.spriteJoueur.getPersonnage().goDroite();
                    }
                    
                    if (this.spriteJoueur.getPersonnage().getHaut() && 
                    		!this.game.gererCollision(this.game.getJoueur(), this.game.getMap(), 0, this.spriteJoueur.getPersonnage().getHauteur()/2))
                    {
                    	this.spriteJoueur.getPersonnage().saute();
                    	
                    }
                    
                  
                    if (this.game.gererCollision(this.game.getJoueur(), this.game.getMap(), 0, this.spriteJoueur.getPersonnage().getHauteur())) {
                            this.spriteJoueur.getPersonnage().tombe();
                    
                    }
                    
                })
        );
        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.game = new Jeu();
        
        this.spriteJoueur=new Sprite(this.game.getJoueur());
        creerSprite();
      
         //dÃ©placement du joueur Ã  l'aide des flÃ¨ches       
        this.spriteJoueur.getImage().setOnKeyPressed(e -> gererFlechesAppuyees(e));
        this.spriteJoueur.getImage().setOnKeyReleased(e -> gererFlechesRelachees(e));
        terrain.setMinSize(game.getMap().largeurMap()*64, 64*game.getMap().hauteurMap());
        terrain.setMaxSize(game.getMap().largeurMap()*64, 64*game.getMap().hauteurMap());
        for(int i=0;i<this.game.getMap().getMap().size();i++) {
                ImageView png = imageDe(this.game.getMap().getMap().get(i));
                this.terrain.getChildren().add(png);
            
        }
        
        initAnimation();
		
	}
	
	//public 
	
	
    

    

	
    
}





