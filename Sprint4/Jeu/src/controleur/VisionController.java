package controleur;

import java.awt.Dimension;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import modele.Monde;

/*
 * cette classe gere le scrolling de la camera, ainsi  que le clic de la souris
 */
public class VisionController {
	
	private Pane vision;
	private Pane coucheJoueur;
	private Monde game;
	
	public VisionController(Pane hud, Pane vision, Pane coucheJoueur, Monde game) {
		this.vision = vision;
		this.coucheJoueur = coucheJoueur;
		this.game = game;
		creerVision();
	}
	
	public void creerVision() {
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
    	int width  = (int)dimension.getWidth();
		coucheJoueur.setMinSize(game.getMap().largeurMap()*64, 64*game.getMap().hauteurMap());
        coucheJoueur.setMaxSize(game.getMap().largeurMap()*64, 64*game.getMap().hauteurMap());
        vision.setMinSize(width,height-10);
        vision.setMaxSize(width,height-10); 
        vision.setOnMousePressed(event->gererClicAppuye(event));
        vision.setOnMouseReleased(event->gererClicRelache(event));
        vision.setOnMouseDragged(event->bindSourisJoueur(event));
	}
	
	public void suiviVision() { 
    	Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    	int height = (int)dimension.getHeight();
    	int width  = (int)dimension.getWidth();

    	if(-this.game.getJoueur().getXProperty().get()+width/2>=0)
    		vision.setTranslateX(0);
    	else if(this.game.getJoueur().getXProperty().get()+width/2>=game.getMap().largeurMap()*64)
    		vision.setTranslateX(-game.getMap().largeurMap()*64+width);
    	else
    		vision.setTranslateX(-this.game.getJoueur().getXProperty().get()+width/2);
    	
    	if(-this.game.getJoueur().getYProperty().get()+height/2>=0)
       		vision.setTranslateY(0);
       	else if(this.game.getJoueur().getYProperty().get()+height/2>=game.getMap().hauteurMap()*64)
       		vision.setTranslateY(-game.getMap().hauteurMap()*64+height);
       	else
       		vision.setTranslateY(-this.game.getJoueur().getYProperty().get()+(height-10)/2);
       	
    }
	
	public void gererClicAppuye(MouseEvent e) {
		bindSourisJoueur(e);
		if (e.getButton() == MouseButton.PRIMARY) {
			this.game.getJoueur().setUtiliserMainGauche(true);
			
		}
		else if (e.getButton() == MouseButton.SECONDARY) {
			this.game.getJoueur().setUtiliserMainDroite(true);
		}
		
		
	}
    
    public void gererClicRelache(MouseEvent e) {
    	if (e.getButton() == MouseButton.PRIMARY) {
			this.game.getJoueur().setUtiliserMainGauche(false);
		}
    	else if (e.getButton() == MouseButton.SECONDARY) {
			this.game.getJoueur().setUtiliserMainDroite(false);
		}
    }
    
    public void bindSourisJoueur(MouseEvent e) {
    	IntegerProperty xSouris=new SimpleIntegerProperty((int)e.getX()/64);
    	IntegerProperty ySouris=new SimpleIntegerProperty((int)e.getY()/64);;
		this.game.getJoueur().getXCibleProperty().bind(xSouris);
		this.game.getJoueur().getYCibleProperty().bind(ySouris);
    }
}
