package controleur;


import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import modele.Monde;
import modele.Blocks.*;

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
        else if (b instanceof Herbe)
        	return new ImageView(new Image("file:../Sprites/Block/Herbe.png"));
        else if (b instanceof Pierre)
        	return new ImageView(new Image("file:../Sprites/Block/Pierre.png"));
        else if (b instanceof Sable)
        	return new ImageView(new Image("file:../Sprites/Block/Sable.png"));
        else if (b instanceof MineraiFer)
        	return new ImageView(new Image("file:../Sprites/Block/MineraiFer.png"));
        else if (b instanceof MineraiRadium)
        	return new ImageView(new Image("file:../Sprites/Block/MineraiRadium.png"));
        else if (b instanceof Bois)
        	return new ImageView(new Image("file:../Sprites/Block/Bois.png"));
        else if (b instanceof BlockDeDechets)
        	return new ImageView(new Image("file:../Sprites/Block/BlockDeDechets.png"));
        else if (b instanceof Coffre)
        	return new ImageView(new Image("file:../Sprites/Block/Coffre.png"));
        else if (b instanceof BidonRadioactif)
        	return new ImageView(new Image("file:../Sprites/Block/BidonRadioactif.png"));
        else
            return new ImageView(new Image("file:../Sprites/Block/Pierre.png"));
    }
	
	public void ecouterMap() {
    	this.game.getMap().getListMap().addListener(new ListChangeListener<Block>() {

			@Override
			public void onChanged(Change<? extends Block> c) {
				while (c.next()) {
                    if (c.wasReplaced()) {
                    	int index=c.getFrom();
                    	changerImageBlock(index);
                    }
                }
			}
    	});
    }
	
	public void changerImageBlock(int index) {
    	ImageView png = imageDe(this.game.getMap().getListMap().get(index));
    	this.terrain.getChildren().set(index, png);
    }

}
