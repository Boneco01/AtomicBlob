package modele.deplacements;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Graphe {
	
	private int largeur;
	private int hauteur;
	private ObservableList<Case> map;
	private ArrayList<Case> chemin;
	
	public Graphe(int largeur, int hauteur) {
		this.largeur=largeur;
		this.hauteur=hauteur;
		this.map=FXCollections.observableList(creerMap());
	}
	
	public ArrayList<Case> creerMap() {
		ArrayList<Case> map=new ArrayList<Case>();
		for (int i=0; i<largeur; i++) {
			for (int j=0;j<hauteur;j++) {
				Case caseMap=new Case(j,i,true);
				map.add(caseMap);
			}
		}
		return map;
	}
	
	public ArrayList<Case> getVoisins(Case laCase) {
		ArrayList<Case> voisins = new ArrayList<Case>();

		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				if (x == 0 && y == 0)
					continue;

				int voisinX = laCase.getX() + x;
				int voisinY = laCase.getY() + y;

				if (voisinX >= 0 && voisinX < largeur && voisinY >= 0 && voisinY < hauteur) {
					voisins.add(getCaseByCoords(voisinX,voisinY));
					getCaseByCoords(voisinX,voisinY).setVisitee(true);
				}
			}
		}

		return voisins;
	}
	
	public int getLargeur() {
		return this.largeur;
	}
	
	public int getHauteur() {
		return this.hauteur;
	}
	
	public void setChemin(ArrayList<Case> chemin) {
		this.chemin=chemin;
	}
	
	public ArrayList<Case> getChemin() {
		return this.chemin;
	}
	
	public ObservableList<Case> getMap() {
		return this.map;
	}
	
	public Case getCaseByCoords(int x, int y) {
		for (int i=0; i<hauteur;i++) {
			if (i==y) {
				return this.getMap().get((largeur*y)+x);
			}
		}
		return null;
	}
}

