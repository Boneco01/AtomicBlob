package controleur;

import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import modele.TableCraft;
import modele.Items.Item;
import modele.Items.ItemVide;

public class TableCraftController {
	private TableCraft tcm;
	private GridPane tcv;
	private InventaireController inv;
	
	public TableCraftController(InventaireController inventaire,GridPane tableCraft,TableCraft tc) {
		tcv= tableCraft;
		tcm=tc;
		inv=inventaire;
		creerTableCraft();
	}
 public void creerTableCraft() {
		 
		 for(int i=0;i<this.tcm.getTc().size(); i++) {
			int index = i;
			Pane p = (Pane)this.tcv.getChildren().get(i);
			ImageView v = (ImageView)p.getChildren().get(0);
			v.setOnDragDetected(event->inv.gererDragOn(event, p)); // DRAG'N'DROP Pour le craft
			p.setOnDragOver(event->inv.gererDragUp(event, p));
			v.setOnDragDropped(event->inv.gererRecup(event, p));
			
     		changerImageTableCraft(i);
		 }
		 //tcv.setOnMousePressed(event->gererClic(event, tcv));
		 ecouterTableCraft();
		 
	 }
 	public void ecouterTableCraft() {
		 this.tcm.getTc().addListener(new ListChangeListener<Item>() {

			@Override
			public void onChanged(Change<? extends Item> c) {
				while (c.next()) {
                    if (c.wasAdded()) {
                    	for(int i=0;i<tcm.getTc().size(); i++) {
                    		changerImageTableCraft(i);
               		 	}
                    }
                }
			}
	    });
	 }
 	 public void changerImageTableCraft(int index) {
		 Pane p = (Pane)this.tcv.getChildren().get(index);
		 ImageView v = (ImageView)p.getChildren().get(0);
		 tcm.getTc().get(index).setQuantitee(1);
		 Image png = inv.imageDe(tcm.getTc().get(index));
		 v.setImage(png);
	 }
 	 
 	 
}
