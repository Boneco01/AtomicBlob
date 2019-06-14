package controleur;

import java.util.HashMap;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import modele.Monde;
import modele.TableCraft;
import modele.Items.Item;
import modele.Items.ItemVide;
import modele.Items.Block.*;
import modele.Items.Craft.*;

public class InventaireController {

	private HBox inventaire;
	private HBox equipements;
	private Monde game;
	private ObservableList<Item> invJoueur;
	private HashMap<Image, Item> correspondanceImageItem;
	private TableCraft tc;
	private HUDController hud;
	
	public InventaireController(HUDController HUD,HBox inventaire, HBox equipements, Monde game, TableCraft tc) {
		hud=HUD;
		this.inventaire = inventaire;
		this.equipements = equipements;
		this.game = game;
		this.invJoueur = this.game.getJoueur().getInventaire().getInventaire();
		this.correspondanceImageItem = new HashMap<Image, Item>(); //Trouver une utilisation
		this.tc = tc;
		remplirCorrespondance();
		creerInventaire();
		creerEquipements();
	}

	public void remplirCorrespondance() {
		for(Item i : this.invJoueur) {
			this.correspondanceImageItem.put(imageDe(i), i);
		}
	}
	
	public void creerInventaire() {

		for (int i = 0; i < this.invJoueur.size(); i++) {
			int index = i;
			Pane p = (Pane) this.inventaire.getChildren().get(i);
			p.setOnDragDetected(event -> hud.gererDragOn(event, p)); // DRAG'N'DROP Pour le craft
			p.setOnDragOver(event -> hud.gererDragUp(event, p));
			p.setOnDragDropped(event -> hud.gererRecup(event, p));
			p.setOnMousePressed(event -> gererClic(event, p, index));
			changerImageInventaire(i);
		}

		ecouterInventaire();

	}

	public int quantiteItem(int id, ObservableList<Item> list) {
		int quantite = 0;
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getId() == id)
				quantite += list.get(i).getQuantitee();
		return quantite;

	}

	public void creerEquipements() {

		Pane p1 = (Pane) this.equipements.getChildren().get(0);
		p1.setStyle("-fx-background-color: blue;");
		Pane p2 = (Pane) this.equipements.getChildren().get(1);
		p2.setStyle("-fx-background-color: red;");
		changerImageEquipement('g');
		changerImageEquipement('d');
	}

	public void gererClic(MouseEvent e, Pane p, int index) {

		if (e.getButton() == MouseButton.PRIMARY) {

			for (int i = 0; i < this.invJoueur.size(); i++) {
				Pane paneColor = (Pane) this.inventaire.getChildren().get(i);
				if (paneColor.getStyle().equals("-fx-background-color: blue;") && paneColor != p) {
					paneColor.setStyle("-fx-background-color: grey;");
				}
			}

			if (p.getStyle().equals("-fx-background-color: blue;")) {
				p.setStyle("-fx-background-color: grey;");
				this.game.getJoueur().equipeGauche(new ItemVide());
				changerImageEquipement('g');
			} else {
				if (p.getStyle().equals("-fx-background-color: red;")) {
					this.game.getJoueur().equipeDroit(new ItemVide());
					changerImageEquipement('d');
				}
				
				p.setStyle("-fx-background-color: blue;");
				this.game.getJoueur().equipeGauche(itemDe(index));
				changerImageEquipement('g');
				
			}
		} else {

			for (int i = 0; i < this.invJoueur.size(); i++) {
				Pane paneColor = (Pane) this.inventaire.getChildren().get(i);
				if (paneColor.getStyle().equals("-fx-background-color: red;") && paneColor != p) {
					paneColor.setStyle("-fx-background-color: grey;");
				}
			}
			if (p.getStyle().equals("-fx-background-color: red;")) {
				p.setStyle("-fx-background-color: grey;");
				this.game.getJoueur().equipeDroit(new ItemVide());
				changerImageEquipement('d');
			}
			else {
			if (p.getStyle().equals("-fx-background-color: blue;")) {
				this.game.getJoueur().equipeGauche(new ItemVide());
				changerImageEquipement('g');
			}
			
			p.setStyle("-fx-background-color: red;");
			this.game.getJoueur().equipeDroit(itemDe(index));
			changerImageEquipement('d');
		}
		}
	}

	public void miseAJourCouleurs() {
		for (int i = 0; i < this.invJoueur.size(); i++) {
			Pane p = (Pane) this.inventaire.getChildren().get(i);
			if (this.invJoueur.get(i).getId() == 0) {
				p.setStyle("-fx-background-color: grey;");
			}
		}
	}

	public void ecouterInventaire() {
		 this.invJoueur.addListener(new ListChangeListener<Item>() {

			@Override
			public void onChanged(Change<? extends Item> c) {
				while (c.next()) {
                  if (c.wasReplaced()) {
                	changerImageInventaire(c.getFrom());
                  	miseAJourCouleurs();
                  	changerImageEquipement('g');
              		changerImageEquipement('d');
                  }
              }
			}
	    });
	 }

	

	public void changerImageEquipement(char emplacement) {
		if (emplacement == 'g') {
			Pane p = (Pane) this.equipements.getChildren().get(0);
			ImageView v = (ImageView) p.getChildren().get(0);
			Image png = imageDe(this.game.getJoueur().getInventaire().getEquipementGauche());
			v.setImage(png);
		} else {
			Pane p = (Pane) this.equipements.getChildren().get(1);
			ImageView v = (ImageView) p.getChildren().get(0);
			Image png = imageDe(this.game.getJoueur().getInventaire().getEquipementDroite());
			v.setImage(png);
		}
	}

	public void changerImageInventaire(int index) {
		Pane p = (Pane) this.inventaire.getChildren().get(index);
		ImageView v = (ImageView) p.getChildren().get(0);
		Label nbItem = (Label) p.getChildren().get(1);
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
        if (item instanceof ItemBarreMetal)
            return new Image("file:../Sprites/Item/ItemCraft/BarreMetal.png");
        if (item instanceof ItemFil)
            return new Image("file:../Sprites/Item/ItemCraft/Fil.png");
        if (item instanceof ItemHache)
            return new Image("file:../Sprites/Item/ItemCraft/Hache.png");
        if (item instanceof ItemLancePierre)
            return new Image("file:../Sprites/Item/ItemCraft/LancePierre.png");
        if (item instanceof ItemLingotFer)
            return new Image("file:../Sprites/Item/ItemCraft/LingotFer.png");
        if (item instanceof ItemPioche)
            return new Image("file:../Sprites/Item/ItemCraft/Pioche.png");
        else
            return new Image("file:../Sprites/Item/ItemVide.png");
    }

	public Item itemDe(int index) {
		return this.invJoueur.get(index);
	}
	
	public ObservableList<Item> getInvJoueur() {
		return invJoueur;
	}
	public HBox getInventaire() {
		return inventaire;
	}
}
