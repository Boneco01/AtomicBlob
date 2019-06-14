package controleur;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import modele.Poubelle;
import modele.Items.*;
import vue.SpritePoubelle;

public class DropController {
	private HUDController hud;
	private HBox poubelle;
	private ArrayList<SpritePoubelle> estJeter;

	private ObservableList<Item> corbeille;

	public DropController(HUDController hud, HBox poubelle, Button buttonJeter) {
		this.hud = hud;
		this.poubelle = poubelle;
		estJeter= new ArrayList<>();
		corbeille = creationCorbeille();
		buttonJeter.setOnAction(e -> jeterItemButton());
		creerPoubelle();

	}

	private ObservableList<Item> creationCorbeille() {
		ObservableList<Item> c = FXCollections.observableList(new ArrayList<Item>());
		c.add(new ItemVide());
		c.add(new ItemVide());
		c.add(new ItemVide());
		return c;
	}

	private void jeterItemButton() {
			ArrayList<Item> c = new ArrayList<>();
			for (int i = 0; i < corbeille.size(); i++) {
				c.add(corbeille.get(i));
				if(hud.getGame().getJoueur().getInventaire().getEquipementGauche()==corbeille.get(i))
					hud.getGame().getJoueur().desequipeGauche();
				else if(hud.getGame().getJoueur().getInventaire().getEquipementDroite()==corbeille.get(i))
					hud.getGame().getJoueur().desequipeDroite();				
				
				hud.getIv().changerImageInventaire(removeItemInt(hud.getIv().getInvJoueur(), corbeille.get(i)));
				corbeille.remove(i);
				corbeille.add(i, new ItemVide());
				changerImagePoubelle(i);
			}
			SpritePoubelle sp=new SpritePoubelle(new Poubelle(c));
			hud.getCoucheJoueur().getChildren().add(sp.getSprite());
			sp.getSprite().setOnMouseClicked(e->gererClickPoubelle(e));
			sp.getSprite().xProperty().set(hud.getGame().getJoueur().getXProperty().get());
			sp.getSprite().yProperty().set(hud.getGame().getJoueur().getYProperty().get());
			estJeter.add(sp);
	}
	private void gererClickPoubelle(Event e) {
		
	}

	public void creerPoubelle() {

		for (int i = 0; i < this.corbeille.size(); i++) {
			int index = i;
			Pane p = (Pane) this.poubelle.getChildren().get(i);
			p.setOnDragDetected(event -> hud.gererDragOn(event, p)); // DRAG'N'DROP Pour le craft
			p.setOnDragOver(event -> hud.gererDragUp(event, p));
			p.setOnDragDropped(event -> hud.gererRecup(event, p));
			p.setOnMousePressed(event -> gererClic(event, p, index));
			changerImagePoubelle(i);
		}

		ecouterPoubelle();

	}

	public void changerImagePoubelle(int index) {
		Pane p = (Pane) this.poubelle.getChildren().get(index);
		ImageView v = (ImageView) p.getChildren().get(0);
		Label nbItem = (Label) p.getChildren().get(1);
		Image png = hud.getIv().imageDe(corbeille.get(index));
		nbItem.textProperty().bind(corbeille.get(index).quantiteeProperty().asString());
		v.setImage(png);
	}

	public void gererClic(MouseEvent e, Pane p, int index) {
		System.out.print("");
	}

	@SuppressWarnings("unused")
	private boolean corbeilleEgaleA(ObservableList<Item> c) {
		for (int i = 0; i < corbeille.size(); i++) {
			if (corbeille.get(i).getClass() != c.get(i).getClass())
				return false;
		}
		return true;
	}

	public int removeItemInt(ObservableList<Item> a, Item i) {// Ajouter Try Catch
		for (int index = 0; index < a.size(); index++) {
			if (a.get(index) == i) {
				a.set(index, new ItemVide());
				return index;
			}
		}
		return 0;
	}

	public void ecouterPoubelle() {
		this.corbeille.addListener(new ListChangeListener<Item>() {

			@Override
			public void onChanged(Change<? extends Item> c) {
				while (c.next()) {
					if (c.wasReplaced()) {
						changerImagePoubelle(c.getFrom());
					}
				}
			}
		});
	}

	public ObservableList<Item> getCorbeille() {
		return corbeille;
	}

	public HBox getPoubelle() {
		return poubelle;
	}

	public void remplaceItem(int index, Item i) {
		for (int j = 0; j < corbeille.size(); j++) {
			if (i == corbeille.get(j)) {
				corbeille.remove(j);
				corbeille.add(j, new ItemVide());
				changerImagePoubelle(j);
				
			}
		}
		corbeille.remove(index);
		corbeille.add(index, i);
		changerImagePoubelle(index);
	}

	public void echangeItem(int i, int y) {
		Item a = corbeille.get(i);
		remplaceItem(i, corbeille.get(y));
		remplaceItem(y, a);
	}
}
