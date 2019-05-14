package app;
	
import javafx.application.Application;
import javafx.stage.Stage;
import modele.Air;
import modele.Block;
import modele.Terrain;
import modele.Terre;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane rootMap = FXMLLoader.load(getClass().getResource("../vue/Sample.fxml"));
			Scene map = new Scene(rootMap, 1920, 1080);
		    primaryStage.setTitle("AtomicBlob");
		    primaryStage.setScene(map);
		    map.getStylesheets().add(getClass().getResource("../vue/application.css").toExternalForm());
		    primaryStage.show();
		    Terrain a=new Terrain("../Map/MapTestModele.csv");
		    for(int i=0;i<a.hauteurMap();i++) {
		    	for(int y=0;y<a.largeurMap();y++) {
		    		ImageView png = imageDe(a.getMap().get(i).get(y));
		    		rootMap.getChildren().add(png);
		    		png.relocate(64*y, 64*i);
		    	}
		    }
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../vue/Sample.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("../vue/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	public ImageView imageDe(Block b) {
		if (b instanceof Terre)
			return new ImageView(new Image("file:../Sprites/Block/Terre.png"));
		else if (b instanceof Air)
			return new ImageView(new Image("file:../Sprites/Block/Air.png"));
		else 
			return new ImageView(new Image("file:../Sprites/Block/Air.png"));
	}
}
