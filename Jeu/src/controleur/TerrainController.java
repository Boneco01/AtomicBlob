package controleur;


import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import modele.Monde;
import modele.Blocks.Air;
import modele.Blocks.Block;
import modele.Blocks.Terre;

public class TerrainController {
	
	private TilePane terrain;
	private Monde game;
	
	public TerrainController(TilePane terrain, Monde game) {
		this.terrain = terrain;
		this.game = game;
		creerTerrain();
	}
	
	public void creerTerrain() {

    	terrain.setMinSize(game.getMap().largeurMap()*64, 64*game.getMap().hauteurMap());
        terrain.setMaxSize(game.getMap().largeurMap()*64, 64*game.getMap().hauteurMap());
        for(int i=0;i<this.game.getMap().getListMap().size();i++) {
            ImageView png = imageDe(this.game.getMap().getListMap().get(i));
            this.terrain.getChildren().add(png);
        
    	}
        ecouterMap();
    }
	
	public ImageView imageDe(Block b) {
        if (b instanceof Terre)
            return new ImageView(new Image("file:../Sprites/Block/Terre.png"));
        else if (b instanceof Air)
            return new ImageView(new Image("file:../Sprites/Block/Air.png"));
        else
            return new ImageView(new Image("file:../Sprites/Block/Air.png"));
    }
	
	public void ecouterMap() {
    	this.game.getMap().getListMap().addListener(new ListChangeListener<Block>() {

			@Override
			public void onChanged(Change<? extends Block> c) {
				while (c.next()) {
                    if (c.wasReplaced()) {
                    	changerImageBlock();
                    }
                }
			}
    	});
    }
	
	public void changerImageBlock() {
    	int xSouris=this.game.getJoueur().getXCible();
    	int ySouris=this.game.getJoueur().getYCible();
    	int index=(this.game.getMap().largeurMap()*ySouris)+xSouris;
    	
    	ImageView png = imageDe(this.game.getMap().getListMap().get(index));
    	this.terrain.getChildren().set(index, png);
    }

}
