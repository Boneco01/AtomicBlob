package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Terrain {

	private String cheminMap;
	private ArrayList<ObservableList<Block>> map;
	
	public Terrain(String cheminMap) {
		this.cheminMap = cheminMap;
		this.map = new ArrayList<ObservableList<Block>>();
		remplirMap();
	}
	
	private void remplirMap() {
		String line;
		ObservableList<Block> ligneMap;
		try {
			File fichierMap = new File(this.cheminMap);
			FileReader fr = new FileReader(fichierMap);
	        BufferedReader br = new BufferedReader(fr);
	        
	        for (line = br.readLine(); line != null; line = br.readLine()) {
	            String[] tableauChaine = line.split(":");
	            ligneMap = FXCollections.observableList(new ArrayList<Block>());
	            for(int i=0;i<tableauChaine.length;i++) {
	            	ligneMap.add(new Block(tableauChaine[i].charAt(0), creerCollision(tableauChaine[i].charAt(0))));
	            }
	            this.map.add(ligneMap);
	        }
	        
	        fr.close();
	        br.close();
	        
		} catch(Exception E) {
			E.printStackTrace();
		}
	}
	
	private boolean creerCollision(char id) {
		boolean collision=false;
		
		switch(id) {
		
			case 'A' : break;
			
			case 'T' : collision = true;
					   break;
			
			default :  break;
		
		}
		
		return collision;
	}
	
	public int largeurMap() {
		int largeur = 0;
		
		for(Block b : this.map.get(0)) {
			largeur++;
		}
		
		return largeur;
	}
	
	public int hauteurMap() {
		int hauteur=0;
		
		for(ObservableList<Block> l : this.map) {
			hauteur++;
		}
		
		return hauteur;
	}
	
	public ArrayList<ObservableList<Block>> getMap() {
		return this.map;
	}
}
