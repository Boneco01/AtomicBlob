package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import modele.Air;
import modele.Block;
import modele.Bois;
import modele.MineraiFer;
import modele.MineraiRadium;
import modele.Monde;
import modele.Pierre;
import modele.Sable;
import modele.Terre;
import javafx.scene.control.ScrollPane;
import vue.Sprite;
import vue.SpriteJoueur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;



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

    	terrain.setMinSize(game.getMap().largeurMap()*64, 64*game.getMap().hauteurMap());
        terrain.setMaxSize(game.getMap().largeurMap()*64, 64*game.getMap().hauteurMap());
        terrain.setOnMousePressed(event->gererClicAppuye(event));
        terrain.setOnMouseReleased(event->gererClicRelache(event));
        for(int i=0;i<this.game.getMap().getListMap().size();i++) {
            ImageView png = imageDe(this.game.getMap().getListMap().get(i));
            this.terrain.getChildren().add(png);
        
    	}
        ecouterMap();
    }
    
    public void changerImageBlock() {
    	int xSouris=this.game.getJoueur().getXBlocAModifier();
    	int ySouris=this.game.getJoueur().getYBlocAModifier();
    	int index=(this.game.getMap().largeurMap()*ySouris)+xSouris;
    	
    	ImageView png = imageDe(this.game.getMap().getListMap().get(index));
    	this.terrain.getChildren().set(index, png);
    }
    
    public void ecouterMap() {
    	this.game.getMap().getListMap().addListener(new ListChangeListener<Block>() {

			@Override
			public void onChanged(Change<? extends Block> c) {
				while (c.next()) {
                    if (c.wasReplaced()) {
                    	changerImageBlock();
                    }
                }
			}
    	});
    		
    }
    
    public void gererClicAppuye(MouseEvent e) {
		int xSouris=(int)e.getX()/64;
		int ySouris=(int)e.getY()/64;
		this.game.getJoueur().setXBlocAModifier(xSouris);
		this.game.getJoueur().setYBlocAModifier(ySouris);
		
		if (e.getButton() == MouseButton.PRIMARY) {
			this.game.getJoueur().setCreuse(true);
		}
		else if(e.getButton() == MouseButton.SECONDARY) {
			this.game.getJoueur().setConstruire(true);
		}
		
		
	}
    
    public void gererClicRelache(MouseEvent e) {
    	if (e.getButton() == MouseButton.PRIMARY) {
			this.game.getJoueur().setCreuse(false);
		}
		else if(e.getButton() == MouseButton.SECONDARY) {
			this.game.getJoueur().setConstruire(false);
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
        else if (b instanceof Pierre)
            return new ImageView(new Image("file:../Sprites/Block/Pierre.png"));
        else if (b instanceof Bois)
            return new ImageView(new Image("file:../Sprites/Block/Bois.png"));
        else if (b instanceof Sable)
            return new ImageView(new Image("file:../Sprites/Block/Sable.png"));
        else if (b instanceof MineraiFer)
            return new ImageView(new Image("file:../Sprites/Block/MineraiFer.png"));
        else if (b instanceof MineraiRadium)
            return new ImageView(new Image("file:../Sprites/Block/MineraiRadium.png"));
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




    