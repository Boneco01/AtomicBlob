package modele.Items;


import modele.Items.Item;
import modele.Monde;
import modele.Portee;
import modele.Blocks.Air;


public class ItemVide extends Item {
	
	public ItemVide() {
		super(0, 0, 1, 5);
	}

	@Override
	public void utiliser(Monde monde) {
		int xPositionJoueur=monde.getJoueur().getXProperty().getValue();
		int yPositionJoueur=monde.getJoueur().getYProperty().getValue();
		int xCible=monde.getJoueur().getXCible().getValue();
		int yCible=monde.getJoueur().getYCible().getValue();
		if(Portee.estAPortee(1,xPositionJoueur,yPositionJoueur, xCible, yCible)) {
			monde.getMap().blockParCord(xCible, yCible).seDetruire(this.getDegatsBlocks());
			if (monde.getMap().blockParCord(xCible, yCible).getResistanceRestante()<=0) {
				Air blockAir=new Air();
				monde.getJoueur().ramasseBlock(monde.getJoueur().getMonde().getMap().blockParCord(xCible, yCible));
				monde.getJoueur().getMonde().getMap().remplacerBlock(blockAir, xCible, yCible);
			}
		}
	}
}