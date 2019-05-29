package controleur;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import modele.Monde;
import modele.Items.Item;
import modele.Items.Block.*;
import modele.Items.ItemBois;
import modele.Items.ItemMineraiFer;
import modele.Items.ItemMineraiRadium;
import modele.Items.ItemPierre;
import modele.Items.ItemSable;


public class InventaireController {

	 private HBox inventaire;
	 private Monde game;
	 private ObservableList<Item> invJoueur;
	 
	 public InventaireController(HBox inventaire, Monde game) {
		 this.inventaire = inventaire;
		 this.game = game;
		 this.invJoueur = this.game.getJoueur().getInventaire().getInventaire();
		 creerInventaire();
	 }
	 
	 public void creerInventaire() {
		 
		 for(int i=0;i<this.invJoueur.size(); i++) {
     		changerImageItem(i);
		 }
		 
		 ecouterInventaire();
		 
	 }
	 
	 public void ecouterInventaire() {
		 this.invJoueur.addListener(new ListChangeListener<Item>() {

			@Override
			public void onChanged(Change<? extends Item> c) {
				while (c.next()) {
                    if (c.wasAdded()) {
                    	for(int i=0;i<invJoueur.size(); i++) {
                    		changerImageItem(i);
               		 	}
                    }
                }
			}
	    });
	 }
	 
	 public void changerImageItem(int index) {
		 Pane p = (Pane)this.inventaire.getChildren().get(index);
		 ImageView v = (ImageView)p.getChildren().get(0);
		 Label nbItem = (Label)p.getChildren().get(1);
		 Image png = imageDe(this.game.getJoueur().getInventaire().getItem(index));
		 nbItem.textProperty().bind(this.invJoueur.get(index).quantiteeProperty().asString());
		 v.setImage(png);
	 }
	 
	 public Image imageDe(Item item) {
	        if (item instanceof ItemTerre)
	            return new Image("file:../Sprites/Item/ItemBlock/Terre.png");
	        if (item instanceof ItemBois)
	            return new Image("file:../Sprites/Item/ItemBlock/Bois.png");
	        if (item instanceof ItemMineraiFer)
	            return new Image("file:../Sprites/Item/ItemBlock/MineraiFer.png");
	        if (item instanceof ItemMineraiRadium)
	            return new Image("file:../Sprites/Item/ItemBlock/MineraiRadium.png");
	        if (item instanceof ItemPierre)
	            return new Image("file:../Sprites/Item/ItemBlock/Pierre.png");
	        if (item instanceof ItemSable)
	            return new Image("file:../Sprites/Item/ItemBlock/Sable.png");
	        else
	            return new Image("file:../Sprites/Item/ItemVide.png");
	    }
	
}
