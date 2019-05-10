

package controleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.input.KeyEvent;



public class SampleController implements Initializable{

    private Circle joueur;
    
    
    @FXML
    private Pane terrain;
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        joueur=new Circle(5);
        terrain.getChildren().add(joueur);
        /*
         * déplacement du joueur à l'aide des flèches
         */
		terrain.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    joueur.setLayoutY(joueur.getLayoutY()-5); break;
                    case LEFT:  joueur.setLayoutX(joueur.getLayoutX()-2); break;
                    case RIGHT: joueur.setLayoutX(joueur.getLayoutX()+2); break;
                    default: break;
                }
            }

		});
    }
    
}



