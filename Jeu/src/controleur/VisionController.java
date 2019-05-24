package controleur;

import java.awt.Dimension;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import modele.Monde;

public class VisionController {

	private Pane vision;
	private Pane coucheJoueur;
	private Monde game;
	
	public VisionController(Pane vision, Pane coucheJoueur, Monde game) {
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
	}
	
	public void suiviVision() {
    	Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    	int height = (int)dimension.getHeight();
    	int width  = (int)dimension.getWidth();
    	vision.setTranslateX(-this.game.getJoueur().getXProperty().get()+width/2);
    	vision.setTranslateY(-this.game.getJoueur().getYProperty().get()+(height-10)/2);
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
	
}
