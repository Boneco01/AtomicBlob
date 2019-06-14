package controleur;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import modele.Monde;

public class VieController {

	private Monde game;
	private HBox vie;
	
	public VieController(Monde game, HBox vie) {
		this.game = game;
		this.vie = vie;
		creerVie();
	}
	
	public void creerVie() {
		changerImagesCoeurs();
		ecouterVie();
	}
	
	public void changerImagesCoeurs() {
		int vie = this.game.getJoueur().getVie();
		for (int i = 0; i < vie/2; i++) {
			Pane p = (Pane) this.vie.getChildren().get(i);
			ImageView v = (ImageView) p.getChildren().get(0);
			v.setImage(new Image("file:../Sprites/Joueur/VieJoueur/CoeurPlein.png"));
		}
		if(vie%2 == 1) {
			Pane p = (Pane) this.vie.getChildren().get((vie/2));
			ImageView v = (ImageView) p.getChildren().get(0);
			v.setImage(new Image("file:../Sprites/Joueur/VieJoueur/DemiCoeur.png"));
		}
	}
	
	public void ecouterVie() {
		 this.game.getJoueur().getVieProperty().addListener( new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				changerImagesCoeurs();
			}
		 });
	}
}
