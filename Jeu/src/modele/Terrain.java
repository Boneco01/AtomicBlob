package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Terrain {

	private String cheminMap;
	private ObservableList<Block> map;
	private int hauteurMap;
	
	public Terrain(String cheminMap) {
		this.cheminMap = cheminMap;
		this.map = FXCollections.observableList(new ArrayList<Block>());
		this.hauteurMap = remplirMap();
	}
	
	private int remplirMap() {
		String line;
		int hauteur = 0;
		try {
			File fichierMap = new File(this.cheminMap);
			FileReader fr = new FileReader(fichierMap);
	        BufferedReader br = new BufferedReader(fr);
	        
	        for (line = br.readLine(); line != null; line = br.readLine()) {
	            String[] tableauChaine = line.split(":");
	            for(int i=0;i<tableauChaine.length;i++) {
	            	this.map.add(blockDe(tableauChaine[i].charAt(0)));
	            }
	            hauteur++;
	        }
	        
	        fr.close();
	        br.close();
	        
		} catch(Exception E) {
			E.printStackTrace();
		}
		
		return hauteur;
	}
	
	public int largeurMap() {
		int largeur = 0;
		
		for(Block b : this.map) {
			largeur++;
		}
		
		return largeur/this.hauteurMap;
	}
	
	public Block blockParCord(int x, int y) {
		
	}
	
	public int hauteurMap() {
		return this.hauteurMap;
	}
	
	public ObservableList<Block> getMap() {
		return this.map;
	}
	
	private Block blockDe(char a) {
		if(a=='A')
			return new Air();
		else if (a=='T')
			return new Terre();
		else
			return new Air();
	}
}
