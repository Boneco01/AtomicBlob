package controleur;

import java.awt.Dimension;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import modele.Monde;
import modele.TableCraft;

public class HUDController {
	
	private Pane hud;
	private Monde game;
	private InventaireController iv;
	private TableCraftController tc;
	

	public HUDController(Pane hud, Monde game, HBox inventaire, HBox equipements,GridPane tableCraftV) {
		TableCraft tableCraftM= new TableCraft();
		iv = new InventaireController(inventaire, equipements, game,tableCraftM);
		tc = new TableCraftController(iv,tableCraftV,tableCraftM);
		this.game = game;
		this.hud = hud;
	}
	
	public void suiviHud() {
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    	int height = (int)dimension.getHeight();
    	int width  = (int)dimension.getWidth();
    	if(-this.game.getJoueur().getXProperty().get()+width/2>=0)
    		this.hud.setTranslateX(0);
    	else if(this.game.getJoueur().getXProperty().get()+width/2>=game.getMap().largeurMap()*64)
    		hud.setTranslateX(game.getMap().largeurMap()*64-width);
    	else
    		hud.setTranslateX(this.game.getJoueur().getXProperty().get()-width/2);
    	
    	if(-this.game.getJoueur().getYProperty().get()+height/2>=0)
       		hud.setTranslateY(0);
    	else if(this.game.getJoueur().getYProperty().get()+height/2>=game.getMap().hauteurMap()*64)
       		hud.setTranslateY(game.getMap().hauteurMap()*64-height);
    	else
        	hud.setTranslateY(this.game.getJoueur().getYProperty().get()-(height-10)/2);

	}
	
	
	
	

}
