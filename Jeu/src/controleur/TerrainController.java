package controleur;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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

    	this.terrain.setMinSize(this.game.getMap().largeurMap()*64, 64*this.game.getMap().hauteurMap());
        this.terrain.setMaxSize(this.game.getMap().largeurMap()*64, 64*this.game.getMap().hauteurMap());
        this.terrain.setOnMousePressed(event->gererClicAppuye(event));
        this.terrain.setOnMouseReleased(event->gererClicRelache(event));
        for(int i=0;i<this.game.getMap().getListMap().size();i++) {
            ImageView png = imageDe(this.game.getMap().getListMap().get(i));
            this.terrain.getChildren().add(png);
        
    	}
        ecouterMap();
    }
	
	public void gererClicAppuye(MouseEvent e) {
		int xSouris=(int)e.getX()/64;
		int ySouris=(int)e.getY()/64;
		this.game.getJoueur().setXBlocAModifier(xSouris);
		this.game.getJoueur().setYBlocAModifier(ySouris);
		
		if (e.getButton() == MouseButton.PRIMARY) {
			this.game.getJoueur().setCreuse(true);
		}
		else if(e.getButton() == MouseButton.SECONDARY) {
			this.game.getJoueur().setConstruire(true);
		}
		
		
	}
    
    public void gererClicRelache(MouseEvent e) {
    	if (e.getButton() == MouseButton.PRIMARY) {
			this.game.getJoueur().setCreuse(false);
		}
		else if(e.getButton() == MouseButton.SECONDARY) {
			this.game.getJoueur().setConstruire(false);
		}
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
    	int xSouris=this.game.getJoueur().getXBlocAModifier();
    	int ySouris=this.game.getJoueur().getYBlocAModifier();
    	int index=(this.game.getMap().largeurMap()*ySouris)+xSouris;
    	
    	ImageView png = imageDe(this.game.getMap().getListMap().get(index));
    	this.terrain.getChildren().set(index, png);
    }

}
