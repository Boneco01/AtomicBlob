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

    private Sprite spriteJoueur;
    
    
    
    
    @FXML
    private Pane coucheJoueur;
    
    public void creerPersonnage() {
    	System.out.println(coucheJoueur);
    	coucheJoueur.getChildren().add(this.spriteJoueur.getCercle());
    	this.spriteJoueur.getCercle().centerXProperty().bind(this.spriteJoueur.getPersonnage().getX());
    	this.spriteJoueur.getCercle().centerYProperty().bind(this.spriteJoueur.getPersonnage().getY());
    	
    	
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
        	case LEFT:  goGauche(this.spriteJoueur); break;
        	case RIGHT: goDroite(this.spriteJoueur); break;
        default: break;
    	}
	}
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	Joueur joueur=new Joueur(5,5,1,"Grisou",100,100);
    	this.spriteJoueur=new Sprite(joueur);
    	creerPersonnage();
        /*
         * déplacement du joueur à l'aide des flèches
         */
    	this.spriteJoueur.getCercle().setOnKeyPressed(e -> gererFleches(e));
    }

	
    
}





