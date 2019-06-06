package controleur;

import javafx.collections.ListChangeListener;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import modele.TableCraft;
import modele.Items.Item;
import modele.Items.ItemVide;

public class TableCraftController {
	private TableCraft tcm;
	private GridPane tcv;
	private InventaireController inv;
	private Button fabriquer;

	public TableCraftController(InventaireController inventaire, GridPane tableCraft, TableCraft tc, Button fabriquer) {
		tcv = tableCraft;
		tcm = tc;
		inv = inventaire;
		this.fabriquer = fabriquer;
		creerTableCraft();
	}

	public void creerTableCraft() {
		
		for (int i = 0; i < this.tcm.getTc().size(); i++) {

			Pane p = (Pane) this.tcv.getChildren().get(i);
			ImageView v = (ImageView) p.getChildren().get(0);
			v.setOnDragDetected(event -> inv.gererDragOn(event, p)); // DRAG'N'DROP Pour le craft
			p.setOnDragOver(event -> inv.gererDragUp(event, p));
			v.setOnDragDropped(event -> inv.gererRecup(event, p));
			p.setOnMousePressed(event -> gererClic(event));
			fabriquer.setOnAction(event -> lanceFabrique());
			changerImageTableCraft(i);
		}
		ecouterTableCraft();

	}

	public void lanceFabrique() {
		
		if (inv.getGame().getJoueur().getInventaire().getNbItems() < inv.getGame().getJoueur().getInventaire()
				.getLimiteInventaire())			
		{
			if (tcm.aCraft().getClass() != new ItemVide().getClass()) {
				inv.getGame().getJoueur().getInventaire().addItem(tcm.aCraft());
				this.videTableCraft();
				tcm.setTableVide();
			}
		}
	}

	public void videTableCraft() {
		for (int i = 0; i < tcm.getTc().size(); i++)
			inv.getGame().getJoueur().getInventaire().removeItemCraft(tcm.getTc().get(i));
	}

	public void gererClic(MouseEvent e) {
		int index = ((int) ((e.getSceneX() - 852) / 52)) + (((int) (e.getSceneY() / 52)) * 3);
		tcm.getTc().set(index, new ItemVide());
	}

	public void ecouterTableCraft() {
		this.tcm.getTc().addListener(new ListChangeListener<Item>() {

			@Override
			public void onChanged(Change<? extends Item> c) {
				while (c.next()) {
					if (c.wasAdded()) {
						for (int i = 0; i < tcm.getTc().size(); i++) {
							changerImageTableCraft(i);
						}
					}
				}
			}
		});
	}

	public void changerImageTableCraft(int index) {
		Pane p = (Pane) this.tcv.getChildren().get(index);
		ImageView v = (ImageView) p.getChildren().get(0);
		tcm.getTc().get(index).setQuantitee(1);
		Image png = inv.imageDe(tcm.getTc().get(index));
		v.setImage(png);
	}

}
