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
import modele.Monde;



public class GameController implements Initializable{

	private TerrainController tc;
	private JoueurController jc;
	private VisionController vc;
	private Monde game;
	
    @FXML
    private Pane coucheJoueur;
    
    @FXML
    private Pane vision;
    
    @FXML
    private TilePane terrain;
    
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
                    this.jc.getSpriteJoueur().changerSprite();
                    this.vc.suiviVision();
                    
                })
        );
        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.game = new Monde();
        System.out.println("Coucou");
        this.tc = new TerrainController(this.terrain, this.game);
        System.out.println("Coucou2");
        this.jc = new JoueurController(this.coucheJoueur, this.game);
        System.out.println("Coucou3");
        this.vc = new VisionController(this.vision, this.coucheJoueur, this.game);
        System.out.println(coucheJoueur.getHeight());
        initAnimation();
	}
    
}


    