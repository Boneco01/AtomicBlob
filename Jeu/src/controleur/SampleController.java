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
    
    public void creerPersonnage() {
    	coucheJoueur.getChildren().add(this.spriteJoueur.getCercle());
    	this.spriteJoueur.getCercle().centerXProperty().bind(this.spriteJoueur.getPersonnage().getX());
    	this.spriteJoueur.getCercle().centerYProperty().bind(this.spriteJoueur.getPersonnage().getY());
    	
    	
    }
    
    public void sauter(Sprite personnage) {
    	personnage.getPersonnage().saute();
    }
    
    public void goDroite(Sprite personnage) {
    	personnage.getPersonnage().goDroite();
    }
    
    public void goGauche(Sprite personnage) {
    	personnage.getPersonnage().goGauche();
    }
    
    public void gererFleches(KeyEvent e) {
		switch (e.getCode()) {
        	case UP:    sauter(this.spriteJoueur); break;
        	case LEFT:  goGauche(this.spriteJoueur); break;
        	case RIGHT: goDroite(this.spriteJoueur); break;
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
					if(this.game.getJoueur().getVie() == 0){
					System.out.println("Fin du jeu");
					gameLoop.stop();
					}
					else {
						if(this.game.gererCollision(this.game.getJoueur(), this.game.getMap())) {
							this.game.getJoueur().tombe();
						}
        		
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
    	creerPersonnage();
      
         //déplacement du joueur à l'aide des flèches       
    	this.spriteJoueur.getCercle().setOnKeyPressed(e -> gererFleches(e));
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





