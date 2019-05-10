package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Terrain {

	private ObservableList<Block> map = FXCollections.observableList(new ArrayList());
	
	public int tailleRecupMap(String adresseFichierMap) {
		int tailleLigne = -1;
		String line;
		try {
		File fichierMap = new File(adresseFichierMap);
		FileReader fr = new FileReader(fichierMap);
        BufferedReader br = new BufferedReader(fr);
        for (line = br.readLine(); line != null; line = br.readLine()) {
            String tableauChaine[] =  line.split(":");
            for(int i=0;i<tableauChaine.length;i++) 
            	map.add(new Block(tableauChaine[i].charAt(0)));
            	tailleLigne=line.split(":").length;
            
        }
        fr.close();
        br.close();
        
		}
		catch(Exception E) {
			E.printStackTrace();
		}
		return tailleLigne;
		
	}
	
	public ObservableList<Block> getMap() {
		return map;
	}
}
