package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import modele.Monde;

public class GameController implements Initializable{

	private TerrainController tc;
	private JoueurController jc;
	private EnnemisController ec;
	private VisionController vc;
	private HUDController hudc;
	private InventaireController iv;
	private Monde game;
	
	@FXML
	private Button fabriquer;
	
	@FXML
	private GridPane tableCraft;
	
	@FXML
    private HBox inventaire;
	
	@FXML
	private HBox equipements;
	
	@FXML
    private Pane hud;
	
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
                    this.game.getSentinelle().agir();
                    this.jc.getSpriteJoueur().changerSprite(); //TODO faire avec un écouteur
                    this.ec.getSpriteSentinelle().changerSprite();
                    this.vc.suiviVision();
                    this.hudc.suiviHud();
                    
                })
        );
        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.game = new Monde();
        this.tc = new TerrainController(this.terrain, this.game);
        this.jc = new JoueurController(this.coucheJoueur, this.game);
        this.ec = new EnnemisController(this.coucheJoueur, this.game); 
        this.vc = new VisionController(this.hud, this.vision, this.coucheJoueur, this.game);
        this.hudc = new HUDController(this.hud, this.game, this.inventaire, this.equipements, this.tableCraft, this.fabriquer);
        initAnimation();
	}
    
}


    