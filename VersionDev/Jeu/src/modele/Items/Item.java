package modele.Items;

import modele.Ennemi;
import modele.Monde;
import modele.Personnage;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Item  {

	private int id;
	private IntegerProperty quantitee;
	private int quantiteeMax;
	private int durabilite;
	
	public Item(int id, int quantiteeMax, int distance) {
		this.quantitee = new SimpleIntegerProperty(1);
		this.quantiteeMax = quantiteeMax;
		this.id = id;
		this.durabilite = 20;
	}
	
	public int getDurabilite() {
		return this.durabilite;
	}
	
	public void setDurabilite(int durabilite) {
		this.durabilite = durabilite;
	}
	
	public int getQuantitee() {
		return this.quantitee.getValue();
	}
	
	public IntegerProperty quantiteeProperty() {
		return this.quantitee;
	}
	
	public void setQuantitee(int quantitee) {
		this.quantitee.setValue(quantitee);
	}
	
	public int getQuantiteeMax() {
		return this.quantiteeMax;
	}
	
	public int getId() {
		return this.id;
	}
	
	public boolean verificationPointBlock(String point, Monde monde, Personnage personnage) {
		int largeur=0;
		int hauteur=0;
		switch (point) {
		 case "hd" : largeur=personnage.getLargeur(); break;
		 case "bg" : hauteur=personnage.getHauteur(); break;
		 case "bd" : largeur=personnage.getLargeur(); hauteur=personnage.getHauteur(); break;
		}
		if (monde.getJoueur().getXCible()!=(personnage.getX()+largeur)/64 ||
				monde.getJoueur().getYCible()!=(personnage.getY()+hauteur-3)/64) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean verificationPointParPoint(Monde monde, Personnage personnage) {
		if (verificationPointBlock("", monde, personnage) && verificationPointBlock ("hd",monde, personnage) && verificationPointBlock("bg",monde,personnage) 
				&& verificationPointBlock("bd",monde,personnage)) {
			return true;
		}
		return false;
	}
	
	public Ennemi surEnnemi(Monde monde) {
		for(Ennemi e : monde.getEnnemis()) {
			if(!verificationPointParPoint(monde,e)) {
				return e;
			}
		}
		return null;
	}
	
	public abstract void utiliser(Monde monde);
	
	
}
