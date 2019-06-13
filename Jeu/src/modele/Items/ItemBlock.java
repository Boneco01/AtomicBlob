package modele.Items;

import modele.Ennemi;
import modele.Monde;
import modele.Portee;
import modele.Blocks.Block;
import modele.Blocks.Air;

public abstract class ItemBlock extends Item {

	private char blockCorrespondant;
	
	public ItemBlock(int id, char blockCorrespondant) {
		super(id, 64, 2);
		this.blockCorrespondant = blockCorrespondant;
	}
	
	public boolean pasSurUnAutreBlock(Monde monde) {
		int xCible=monde.getJoueur().getXCible();
		int yCible=monde.getJoueur().getYCible();
		if (monde.getMap().blockParCord(xCible, yCible) instanceof Air) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public void utiliser(Monde monde) {
		Ennemi ennemiCible;
		
		int xCible=monde.getJoueur().getXCible();
		int yCible=monde.getJoueur().getYCible();
		int xJoueur=monde.getJoueur().getXProperty().getValue();
		int yJoueur=monde.getJoueur().getYProperty().getValue();
		ennemiCible = surEnnemi(monde);
		if(verificationPointParPoint(monde,monde.getJoueur()) 
			&& ennemiCible==null && Portee.estAPortee(2,xJoueur, yJoueur, xCible,yCible) && pasSurUnAutreBlock(monde)) {		
			Block block = monde.getMap().blockDe(blockCorrespondant);
			monde.getMap().remplacerBlock(block, xCible, yCible);
			this.setQuantitee(this.getQuantitee()-1);
		}
	}
	
}
