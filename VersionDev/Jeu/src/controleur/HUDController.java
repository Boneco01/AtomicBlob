package controleur;

import java.awt.Dimension;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import modele.Monde;
import modele.TableCraft;
import modele.Items.Item;
import modele.Items.ItemVide;

public class HUDController {

	private Pane hud;
	private Monde game;
	private InventaireController iv;
	private TableCraftController tc;
	private VieController vc;
	private DropController dc;
	private Pane cj;

	public HUDController(Pane hud, Monde game, HBox inventaire, HBox equipements, HBox vie, GridPane tableCraftV,
			Button fabriquer,HBox poubelle,Button buttonJeter,Pane coucheJoueur) {
		TableCraft tableCraftM = new TableCraft();
		iv = new InventaireController(this, inventaire, equipements, game, tableCraftM);
		tc = new TableCraftController(this, tableCraftV, tableCraftM, fabriquer);
		dc = new DropController(this,poubelle,buttonJeter);
		cj = coucheJoueur;
		this.vc = new VieController(game, vie);
		this.game = game;
		this.hud = hud;
	}
	public Pane getCoucheJoueur() {
		return cj;
	}

	public Monde getGame() {
		return game;
	}

	public void suiviHud() {
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) dimension.getHeight();
		int width = (int) dimension.getWidth();
		if (-this.game.getJoueur().getXProperty().get() + width / 2 >= 0)
			this.hud.setTranslateX(0);
		else if (this.game.getJoueur().getXProperty().get() + width / 2 >= game.getMap().largeurMap() * 64)
			hud.setTranslateX(game.getMap().largeurMap() * 64 - width);
		else
			hud.setTranslateX(this.game.getJoueur().getXProperty().get() - width / 2);

		if (-this.game.getJoueur().getYProperty().get() + height / 2 >= 0)
			hud.setTranslateY(0);
		else if (this.game.getJoueur().getYProperty().get() + height / 2 >= game.getMap().hauteurMap() * 64)
			hud.setTranslateY(game.getMap().hauteurMap() * 64 - height);
		else
			hud.setTranslateY(this.game.getJoueur().getYProperty().get() - (height - 10) / 2);

	}

	public void gererDragOn(MouseEvent e, Pane p) {
		Dragboard dragBroard = p.startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		WritableImage capture = p.getChildren().get(0).snapshot(null, null);
		content.putImage(capture);
		dragBroard.setContent(content);
		e.consume();

	}

	public void gererDragUp(DragEvent e, Pane p) {
		if (e.getTarget() != null)
			e.acceptTransferModes(TransferMode.COPY);
		e.consume();

	}

	public void gererRecup(DragEvent e, Pane p) {

		// if(e == .PRIMARY)
		if (((Pane) e.getGestureSource()).getParent() == iv.getInventaire()
				&& ((Pane) e.getGestureTarget()).getParent() == tc.getTcv()) {
			int indexT = ((int) ((e.getSceneX() - 852) / 52)) + (((int) (e.getSceneY() / 52)) * 3);
			int indexS = (int) (((Pane) e.getGestureSource()).getLayoutX() / 66);
			if (quantiteItem(iv.getInvJoueur().get(indexS).getId(),
					iv.getInvJoueur()) > quantiteItem(iv.getInvJoueur().get(indexS).getId(), tc.getTcm().getTc()))
				tc.getTcm().addMateriaux(game.getJoueur().getInventaire().copy(iv.getInvJoueur().get(indexS)), indexT);

		} else if (((Pane) e.getGestureSource()).getParent() == iv.getInventaire()
				&& ((Pane) e.getGestureTarget()).getParent() == iv.getInventaire()) {
			int indexS = (int) (((Pane) e.getGestureSource()).getLayoutX() / 66);
			int indexT = (int) e.getSceneX() / 66;
			Item i = game.getJoueur().getInventaire().getInventaire().get(indexT);
			game.getJoueur().getInventaire().getInventaire().set(indexT,
					game.getJoueur().getInventaire().getInventaire().get(indexS));
			game.getJoueur().getInventaire().getInventaire().set(indexS, i);
			game.getJoueur().desequipeGauche();
		} else if (((Pane) e.getGestureSource()).getParent() == tc.getTcv()
				&& ((Pane) e.getGestureTarget()).getParent() == tc.getTcv()) {
			int indexS = ((int) ((((Pane) e.getGestureSource()).getLayoutX() / 52))
					+ (((int) ((Pane) e.getGestureSource()).getLayoutY() / 52)) * 3);
			int indexT = ((int) ((e.getSceneX() - 852) / 52)) + (((int) (e.getSceneY() / 52)) * 3);
			
			if (!e.isAccepted())// ((e.getSceneX() - 852) / 52)>=4 || (((int) (e.getSceneY() / 52)) * 3)>=4)
				tc.getTcm().addMateriaux(new ItemVide(), indexS);
			Item mat = tc.getTcm().getTc().get(indexT);
			tc.getTcm().addMateriaux(tc.getTcm().getTc().get(indexS), indexT);
			tc.getTcm().addMateriaux(mat, indexS);
		}
		else if (((Pane) e.getGestureSource()).getParent() == iv.getInventaire()
				&& ((Pane) e.getGestureTarget()).getParent() == dc.getPoubelle()) {
			int indexT =  (int) e.getSceneX() / 66;
			int indexS = (int) (((Pane) e.getGestureSource()).getLayoutX() / 66);
			dc.remplaceItem(indexT,iv.getInvJoueur().get(indexS));
		}
		else if (((Pane) e.getGestureSource()).getParent() == dc.getPoubelle()
				&& ((Pane) e.getGestureTarget()).getParent() == dc.getPoubelle()) {
			int indexT =  (int) e.getSceneX() / 66;
			int indexS = (int) (((Pane) e.getGestureSource()).getLayoutX() / 66);
			dc.echangeItem(indexS, indexT);
		}
	}

	public int quantiteItem(int id, ObservableList<Item> list) {
		int quantite = 0;
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getId() == id)
				quantite += list.get(i).getQuantitee();
		return quantite;

	}

	public InventaireController getIv() {
		return iv;
	}

}
