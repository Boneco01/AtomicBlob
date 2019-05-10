package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import modele.Joueur;

public class SampleController implements Initializable{
	private Joueur joueur;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.joueur=new Joueur(10, 5, 1, "Jacques", 200, 200 );
		
	}
	
}

