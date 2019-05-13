package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Terrain {

	// Ajout du constructeur pour séparer remplissage de la map avec le calcul de sa largeur + ajout des calculs de largeur et de hauteur.
	// A voir si possible de faire une ObservableList à deux dimensions pour faciliter les interactions joueur/map.
	
	private String cheminMap;
	//private ObservableList<ArrayList<Block>> map; // <-- Deux dimensions ?
	private ObservableList<Block> map;
	
	public Terrain(String cheminMap) {
		this.cheminMap = cheminMap;
		this.map = FXCollections.observableList(new ArrayList<Block>());
		remplirMap();
	}
	
	private void remplirMap() {
		String line;
		try {
		File fichierMap = new File(this.cheminMap);
		FileReader fr = new FileReader(fichierMap);
        BufferedReader br = new BufferedReader(fr);
        for (line = br.readLine(); line != null; line = br.readLine()) {
            String[] tableauChaine = line.split(":");
            for(int i=0;i<tableauChaine.length;i++) 
            	map.add(new Block(tableauChaine[i].charAt(0)));
            //Ne récupère pas le bon caractère
        }
        fr.close();
        br.close();
        
		}
		catch(Exception E) {
			E.printStackTrace();
		}
	}
	
	public int largeurMap() {
		int largeur = -1;
		String line;
		try {
		File fichierMap = new File(this.cheminMap);
		FileReader fr = new FileReader(fichierMap);
        BufferedReader br = new BufferedReader(fr);
        for (line = br.readLine(); line != null; line = br.readLine()) {
            String tableauChaine[] = line.split(":");
            for(int i=0;i<tableauChaine.length;i++) 
            	largeur=line.split(":").length;
            
        }
        fr.close();
        br.close();
        
		}
		catch(Exception E) {
			E.printStackTrace();
		}
		return largeur;
	}
	
	public int hauteurMap() {
		int hauteur=0;
		
		for(Block b : this.map) {
			hauteur++;
		}
		
		return hauteur/largeurMap();
	}
	
	public ObservableList<Block> getMap() {
		return map;
	}
}
