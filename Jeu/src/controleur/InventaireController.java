package controleur;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import modele.Monde;
import modele.Items.Item;
import modele.Items.ItemVide;
import modele.Items.Block.*;


public class InventaireController {

	 private HBox inventaire;
	 private HBox equipements;
	 private Monde game;
	 private ObservableList<Item> invJoueur;
	 
	 public InventaireController(HBox inventaire, HBox equipements, Monde game) {
		 this.inventaire = inventaire;
		 this.equipements = equipements;
		 this.game = game;
		 this.invJoueur = this.game.getJoueur().getInventaire().getInventaire();
		 creerInventaire();
		 creerEquipements();
	 }
	 
	 public void creerInventaire() {
		 
		 for(int i=0;i<this.invJoueur.size(); i++) {
			int index = i;
			Pane p = (Pane)this.inventaire.getChildren().get(i);
			ImageView v = (ImageView)p.getChildren().get(0);
			//v.setOnDragDetected(event->gererDragOn(event, p, v)); // DRAG'N'DROP Pour le craft
			v.setOnMousePressed(event->gererClic(event, p, index));
     		changerImageInventaire(i);
		 }
		 
		 ecouterInventaire();
		 
	 }
	 
	 public void creerEquipements() {
		 
		 Pane p1 = (Pane)this.equipements.getChildren().get(0);
		 p1.setStyle("-fx-background-color: blue;");
		 Pane p2 = (Pane)this.equipements.getChildren().get(1);
		 p2.setStyle("-fx-background-color: red;");
		 changerImageEquipement('g');
		 changerImageEquipement('d');
	 }
	 
	 public void gererDragOn(MouseEvent e, Pane p, ImageView v) {
			
			if (e.getButton() == MouseButton.PRIMARY) {
				System.out.println("DnD detect�."); 
	            Dragboard dragBroard = v.startDragAndDrop(TransferMode.COPY); 
	            // Remlissage du contenu. 
	            ClipboardContent content = new ClipboardContent();  
	            // Exporter en tant qu'image.        
	            WritableImage capture = v.snapshot(null, null); 
	            content.putImage(capture); 
	            // 
	            dragBroard.setContent(content);
	            e.consume();
			}
			
			
		}
	    
	 public void gererClic(MouseEvent e, Pane p, int index) {
		 
	    	if (e.getButton() == MouseButton.PRIMARY) {
	    		
	    		for(int i=0;i<this.invJoueur.size();i++) {
	    			Pane paneColor = (Pane)this.inventaire.getChildren().get(i);
	    			if(paneColor.getStyle().equals("-fx-background-color: blue;") && paneColor!=p) {
	    				paneColor.setStyle("-fx-background-color: grey;");
	    			}
	    		}
	    		
	    		if(p.getStyle().equals("-fx-background-color: blue;")) {
	    			p.setStyle("-fx-background-color: grey;");
	    			this.game.getJoueur().equipeGauche(new ItemVide());
	    			changerImageEquipement('g');
	    		} else {
	    			if(p.getStyle().equals("-fx-background-color: red;")) {
	    				this.game.getJoueur().equipeDroit(new ItemVide());
		    			changerImageEquipement('d');
	    			}
	    			p.setStyle("-fx-background-color: blue;");
	    			this.game.getJoueur().equipeGauche(itemDe(index));
	    			changerImageEquipement('g');
	    		}
			} else {
				
				for(int i=0;i<this.invJoueur.size();i++) {
	    			Pane paneColor = (Pane)this.inventaire.getChildren().get(i);
	    			if(paneColor.getStyle().equals("-fx-background-color: red;") && paneColor!=p) {
	    				paneColor.setStyle("-fx-background-color: grey;");
	    			}
	    		}
				
				if(p.getStyle().equals("-fx-background-color: red;")) {
	    			p.setStyle("-fx-background-color: grey;");
	    			this.game.getJoueur().equipeDroit(new ItemVide());
	    			changerImageEquipement('d');
	    		} else {
	    			if(p.getStyle().equals("-fx-background-color: blue;")) {
	    				this.game.getJoueur().equipeGauche(new ItemVide());
		    			changerImageEquipement('g');
	    			}
	    			p.setStyle("-fx-background-color: red;");
	    			this.game.getJoueur().equipeDroit(itemDe(index));
	    			changerImageEquipement('d');
	    		}
			}
	    	
	    }
	 
	 public void ecouterInventaire() {
		 this.invJoueur.addListener(new ListChangeListener<Item>() {

			@Override
			public void onChanged(Change<? extends Item> c) {
				while (c.next()) {
                    if (c.wasAdded()) {
                    	for(int i=0;i<invJoueur.size(); i++) {
                    		changerImageInventaire(i);
               		 	}
                    }
                }
			}
	    });
	 }
	 
	 public void changerImageEquipement(char emplacement) {
		 if(emplacement=='g') {
			 Pane p = (Pane)this.equipements.getChildren().get(0);
			 ImageView v = (ImageView)p.getChildren().get(0);
			 Image png = imageDe(this.game.getJoueur().getInventaire().getEquipementGauche());
			 v.setImage(png);
		 } else {
			 Pane p = (Pane)this.equipements.getChildren().get(1);
			 ImageView v = (ImageView)p.getChildren().get(0);
			 Image png = imageDe(this.game.getJoueur().getInventaire().getEquipementDroite());
			 v.setImage(png);
		 }
	 }
	 
	 public void changerImageInventaire(int index) {
		 Pane p = (Pane)this.inventaire.getChildren().get(index);
		 ImageView v = (ImageView)p.getChildren().get(0);
		 Label nbItem = (Label)p.getChildren().get(1);
		 Image png = imageDe(this.game.getJoueur().getInventaire().getItem(index));
		 nbItem.textProperty().bind(this.invJoueur.get(index).quantiteeProperty().asString());
		 v.setImage(png);
	 }
	 
	 public Item itemDe(int index) {
		 return this.invJoueur.get(index);
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
