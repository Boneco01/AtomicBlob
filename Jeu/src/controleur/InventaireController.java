package controleur;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import modele.Monde;
import modele.Items.Item;
import modele.Items.Block.*;

public class InventaireController {

	 private HBox inventaire;
	 private Monde game;
	 
	 public InventaireController(HBox inventaire, Monde game) {
		 this.inventaire = inventaire;
		 this.game = game;
		 creerInventaire();
	 }
	 
	 public void creerInventaire() {
		 ObservableList<Item> invJoueur = this.game.getJoueur().getInventaire().getInventaire();
		 
		 for(int i=0;i<invJoueur.size(); i++) {
			 changerImageItem(i);
		 }
		 
	 }
	 
	 public void ecouterInventaire() {
		 this.game.getJoueur().getInventaire().getInventaire().addListener(new ListChangeListener<Item>() {

			@Override
			public void onChanged(Change<? extends Item> c) {
				while (c.next()) {
                    if (c.wasReplaced()) {
                    	changerImageItem(c.getFrom());
                    }
                }
			}
	    });
	 }
	 
	 public void changerImageItem(int index) {
		 Pane p = (Pane)this.inventaire.getChildren().get(index);
		 ImageView v = (ImageView)p.getChildren().get(0);
		 Image png = imageDe(this.game.getJoueur().getInventaire().getItem(index));
		 v.setImage(png);
	 }
	 
	 public Image imageDe(Item item) {
	        if (item instanceof ItemTerre)
	            return new Image("file:../Sprites/Item/ItemBlock/Terre.png");
	        else
	            return new Image("file:../Sprites/Item/ItemVide.png");
	    }
	
}
