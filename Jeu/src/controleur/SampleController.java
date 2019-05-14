package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import modele.Air;
import modele.Block;
import modele.Joueur;
import modele.Terrain;
import modele.Terre;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;



public class SampleController implements Initializable{

    private Sprite spriteJoueur;
    
    
    
    
    @FXML
    private Pane coucheJoueur;
    
    @FXML
    private Pane terrain;
    
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
    
    public ImageView imageDe(Block b) {
		if (b instanceof Terre)
			return new ImageView(new Image("file:../Sprites/Block/Terre.png"));
		else if (b instanceof Air)
			return new ImageView(new Image("file:../Sprites/Block/Air.png"));
		else 
			return new ImageView(new Image("file:../Sprites/Block/Air.png"));
	}
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	Joueur joueur=new Joueur(5,5,1,"Grisou",100,100);
    	this.spriteJoueur=new Sprite(joueur);
    	creerPersonnage();
      
         //déplacement du joueur à l'aide des flèches       
    	this.spriteJoueur.getCercle().setOnKeyPressed(e -> gererFleches(e));
    	
    	Terrain a=new Terrain("../Map/MapTestModele.csv");
	    for(int i=0;i<a.hauteurMap();i++) {
	    	for(int y=0;y<a.largeurMap();y++) {
	    		ImageView png = imageDe(a.getMap().get(i).get(y));
	    		this.terrain.getChildren().add(png);
	    		png.relocate(64*y, 64*i);
	    	}
	    }
    }

	
    
}





