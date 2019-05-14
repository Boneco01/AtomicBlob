package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import modele.Joueur;
import modele.Personnage;
import javafx.scene.input.KeyEvent;



public class SampleController implements Initializable{

    private Sprite modele;
    
    
    
    
    @FXML
    private Pane terrain;
    
    public void creerPersonnage() {
    	terrain.getChildren().add(this.modele.getCercle());
    	this.modele.getCercle().centerXProperty().bind(this.modele.getPersonnage().getX());
    	this.modele.getCercle().centerYProperty().bind(this.modele.getPersonnage().getY());
    	
    	
    }
    
    public void goDroite(Sprite personnage) {
    	personnage.getPersonnage().goDroite();
    }
    
    public void goGauche(Sprite personnage) {
    	personnage.getPersonnage().goGauche();
    }
    
    public void gererFleches(KeyEvent e) {
		switch (e.getCode()) {
        	//case UP:    ; break;
        	case LEFT:  goGauche(this.modele); break;
        	case RIGHT: goDroite(this.modele); break;
        default: break;
    	}
	}
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	Joueur joueur=new Joueur(5,5,1,"Grisou",100,100);
    	this.modele=new Sprite(joueur);
    	creerPersonnage();
        /*
         * déplacement du joueur à l'aide des flèches
         */
    	terrain.setOnKeyPressed(e -> gererFleches(e));
    }

	
    
}





