package modele.Items;

import modele.Monde;
import modele.Blocks.Air;

public class ItemVide extends Item {
	
	public ItemVide(Monde monde) {
		super(0, 1, monde, 1);
	}

	@Override
	public void utiliser() {
		if(this.monde.getJoueur().getUtiliser() && 
				this.getPortee().estAPortee(this.monde.getJoueur().getXProperty().getValue(), 
						this.monde.getJoueur().getYProperty().getValue(), this.monde.getJoueur().getXCible(),
						this.monde.getJoueur().getYCible())) {
			Air blockAir=new Air();
			this.monde.getJoueur().ramasseBlock(this.monde.getJoueur().getMonde().getMap().blockParCord(this.monde.getJoueur().getXCible(), this.monde.getJoueur().getYCible()));
			this.monde.getJoueur().getMonde().getMap().remplacerBlock(blockAir, this.monde.getJoueur().getXCible(), this.monde.getJoueur().getYCible());
		}
	}
	
}
