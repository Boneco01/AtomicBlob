package modele.deplacements;

import modele.Personnage;

public class DeplacementJoueur extends Deplacement{
	
	public void seDeplace(Personnage personnage) {
        if (personnage.getGauche() && !personnage.getBoite().collision(-3,6,-3,personnage.getHauteur()-6)){
        	personnage.goGauche();
        }
          
        if (personnage.getDroite() && !personnage.getBoite().collision(personnage.getLargeur()+3,6,personnage.getLargeur()+3,-6)){
        	personnage.goDroite();
        }
        
        if (personnage.getHaut() && personnage.getBoite().collision(0, personnage.getHauteur(), personnage.getLargeur(), personnage.getHauteur())) {
        	personnage.setHauteurSaut(12);
        }
        
        if(personnage.getBoite().collision(0,-3,personnage.getLargeur(),-3)) {
        	personnage.setHauteurSaut(0);
        }
        
        if( personnage.getHauteurSaut() > 0 ) {
        	personnage.saute(personnage.getVSaut());
        	if(personnage.getHauteurSaut() == personnage.getHauteurSaut()/2) {
        		personnage.setVSaut(2);
        	} else if( personnage.getHauteurSaut() == personnage.getHauteurSaut()/3) {
        		personnage.setVSaut(1);
        	}
        	personnage.setHauteurSaut(personnage.getHauteurSaut()-1);
        }
        
        else if (!personnage.getBoite().collision(0, personnage.getHauteur(), personnage.getLargeur(), personnage.getHauteur())) {
        	personnage.tombe();
        }
        
        
	}
}
