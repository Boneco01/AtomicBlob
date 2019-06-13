package modele.Items;

import modele.Ennemi;
import modele.Monde;
import modele.Portee;
import modele.Blocks.Air;
import modele.Blocks.Block;

public abstract class ItemCraft extends Item {
	
	private int degatsBlocks;
	private int degatsEnnemis;

	public ItemCraft(int id , int quantiteMax, int degatsBlocks, int degatsEnnemis) {
		super(id, quantiteMax, 1);
		this.degatsBlocks = degatsBlocks;
		this.degatsEnnemis = degatsEnnemis;

	}
	
	@Override
	public void utiliser(Monde monde) {
		Ennemi ennemiCible;
		int xPositionJoueur=monde.getJoueur().getXProperty().getValue();
		int yPositionJoueur=monde.getJoueur().getYProperty().getValue();
		int xCible=monde.getJoueur().getXCible();
		int yCible=monde.getJoueur().getYCible();
		if(Portee.estAPortee(1,xPositionJoueur,yPositionJoueur, xCible, yCible)) {
			ennemiCible = surEnnemi(monde);
			if(ennemiCible!=null) {
				monde.getJoueur().attaque(ennemiCible, this.degatsEnnemis);
			} else {
				monde.getMap().blockParCord(xCible, yCible).seDetruire(this.efficacite(monde.getMap().blockParCord(xCible, yCible)));
				if (monde.getMap().blockParCord(xCible, yCible).getResistanceRestante()<=0) {
					Air blockAir=new Air();
					monde.getJoueur().ramasseBlock(monde.getJoueur().getMonde().getMap().blockParCord(xCible, yCible));
					monde.getJoueur().getMonde().getMap().remplacerBlock(blockAir, xCible, yCible);
				}
			}
		}
	}
	
	public int efficacite(Block b) {
		return this.getDegatsBlocks();
	}
	
	public int getDegatsBlocks() {
		return this.degatsBlocks;
	}
	
	public int getDegatsEnnemis() {
		return this.degatsEnnemis;
	}
	
}
