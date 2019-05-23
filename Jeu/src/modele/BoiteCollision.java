package modele;

import javafx.beans.property.IntegerProperty;

public class BoiteCollision {
	private Joueur personnage;//TODO 
	private int largeurPerso;
	private int hauteurPerso;
	
	public BoiteCollision(Joueur personnage) {
		this.personnage=personnage;
		this.largeurPerso=personnage.getLargeur();
		this.hauteurPerso=personnage.getHauteur();
		
	}
	
	public boolean collisionGauche() {
		if (this.personnage.getMonde().gererCollision(this.personnage.getMonde().getMap(), -3, 0) &&
			this.personnage.getMonde().gererCollision(this.personnage.getMonde().getMap(), -3, this.personnage.getHauteur()-3)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean collisionDroite() {
		if(this.personnage.getMonde().gererCollision(this.personnage.getMonde().getMap(), this.personnage.getLargeur()+3, 0) &&
		this.personnage.getMonde().gererCollision(this.personnage.getMonde().getMap(), this.personnage.getLargeur()+3, this.personnage.getHauteur()-3)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean collisionHaut() {
		if(this.personnage.getMonde().gererCollision(this.personnage.getMonde().getMap(), 0, -3) && 
		this.personnage.getMonde().gererCollision(this.personnage.getMonde().getMap(), this.personnage.getLargeur(), -3)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean collisionBas() {
		if(this.personnage.getMonde().gererCollision(this.personnage.getMonde().getMap(), 0, this.personnage.getHauteur()) && 
		this.personnage.getMonde().gererCollision(this.personnage.getMonde().getMap(), this.personnage.getLargeur(), this.personnage.getHauteur())) {
			return false;
		}
		else {
			return true;
		}
	}
	
	
	public boolean verificationPointBlock(String point) {
		int largeur=0;
		int hauteur=0;
		switch (point) {
		 case "hd" : largeur=this.largeurPerso; break;
		 case "bg" : hauteur=this.hauteurPerso; break;
		 case "bd" : largeur=this.largeurPerso; hauteur=this.hauteurPerso; break;
		}
		if (this.personnage.getXBlocAModifier()!=(this.personnage.getXProperty().getValue()+largeur)/64 ||
				this.personnage.getYBlocAModifier()!=(this.personnage.getYProperty().getValue()+hauteur)/64) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean peutConstruire() {
		if (verificationPointBlock("") && verificationPointBlock ("hd") && verificationPointBlock("bg") && verificationPointBlock("bd")) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
