package controleur;

import java.awt.Dimension;

import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import modele.Monde;

public class VisionController {
	
	private HBox inventaire;
	private Pane vision;
	private Pane coucheJoueur;
	private Monde game;
	
	public VisionController(Pane vision, Pane coucheJoueur, Monde game, HBox inventaire) {
		this.vision = vision;
		this.coucheJoueur = coucheJoueur;
		this.game = game;
		this.inventaire=inventaire;
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
    	suiviInventaire();
       	
    }
	private void suiviInventaire() {
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    	int height = (int)dimension.getHeight();
    	int width  = (int)dimension.getWidth();
    	if(-this.game.getJoueur().getXProperty().get()+width/2>=0)
    		inventaire.setTranslateX(0);
    	else if(this.game.getJoueur().getXProperty().get()+width/2>=game.getMap().largeurMap()*64)
    		inventaire.setTranslateX(game.getMap().largeurMap()*64-width);
    	else
    		inventaire.setTranslateX(this.game.getJoueur().getXProperty().get()-width/2);
    	
    	if(-this.game.getJoueur().getYProperty().get()+height/2>=0)
       		inventaire.setTranslateY(0);
    	else if(this.game.getJoueur().getYProperty().get()+height/2>=game.getMap().hauteurMap()*64)
       		inventaire.setTranslateY(game.getMap().hauteurMap()*64-height);
    	else
        	inventaire.setTranslateY(this.game.getJoueur().getYProperty().get()-(height-10)/2);

	}
	
	public void gererClicAppuye(MouseEvent e) {
		int xSouris=(int)e.getX()/64;
		int ySouris=(int)e.getY()/64;
		this.game.getJoueur().setXCible(xSouris);
		this.game.getJoueur().setYCible(ySouris);
		
		if (e.getButton() == MouseButton.PRIMARY) {
			this.game.getJoueur().setUtiliser(true);
		}
		
		
	}
    
    public void gererClicRelache(MouseEvent e) {
    	if (e.getButton() == MouseButton.PRIMARY) {
			this.game.getJoueur().setUtiliser(false);
		}
    }
	
}
