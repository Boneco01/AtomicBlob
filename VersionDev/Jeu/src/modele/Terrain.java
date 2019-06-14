package modele;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.Blocks.*;

public class Terrain {

	private String cheminMap;
	private ObservableList<Block> map;
	private HashMap<Character, Block> correspondanceIdBlock;
	private int hauteurMap;
	private ArrayList<Block> chemin;

	public Terrain(String cheminMap) {
		this.cheminMap = cheminMap;
		this.map = FXCollections.observableList(new ArrayList<Block>());
		this.correspondanceIdBlock = new HashMap<Character, Block>(); // HASHMAP ICI !
		remplirCorresIdBlock();
		this.hauteurMap = remplirMap();
		
	}

	private void remplirCorresIdBlock() {
		this.correspondanceIdBlock.put('T', new Terre());
		this.correspondanceIdBlock.put('H', new Herbe());
		this.correspondanceIdBlock.put('P', new Pierre());
		this.correspondanceIdBlock.put('R', new MineraiRadium());
		this.correspondanceIdBlock.put('A', new Air());
		this.correspondanceIdBlock.put('S', new Sable());
		this.correspondanceIdBlock.put('B', new Bois());
		this.correspondanceIdBlock.put('F', new MineraiFer());
		this.correspondanceIdBlock.put('D', new BlockDeDechets());
		this.correspondanceIdBlock.put('I', new BidonRadioactif());
		this.correspondanceIdBlock.put('X', new Bedrock());
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
				for (int i = 0; i < tableauChaine.length; i++) {
					this.map.add(blockDe(tableauChaine[i].charAt(0)));
				}
				hauteur++;
				
			}

			fr.close();
			br.close();

		} catch (Exception E) {
			E.printStackTrace();
		}

		return hauteur;
	}

	public void sauvegardeMap() {
		try (FileWriter fw = new FileWriter(new File(cheminMap))) {
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < hauteurMap(); i++) {
				boolean first = true;
				for (int y = 0; y < map.size() / hauteurMap(); y++) {
					if (first)
						first = false;
					else
						bw.write(":");
					bw.write(map.get(i * hauteurMap() + y).getId());

				}
				bw.write("\n");	
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int largeurMap() {
		int largeur = 0;

		for (Block b : this.map) {
			largeur++;

		}

		return largeur / this.hauteurMap;
	}

	public Block blockParCord(int x, int y) {
		return this.map.get((largeurMap() * y) + x);
	}

	public void remplacerBlock(Block blockQuiRemplace, int x, int y) {
		this.map.set((largeurMap() * y) + x, blockQuiRemplace);
		if (y>0 && blockQuiRemplace instanceof Terre && blockParCord(x, y-1) instanceof Air) {
			this.map.set((largeurMap() * y) + x, new Herbe());
		}
		if (y<this.hauteurMap && estAir(blockQuiRemplace) && blockParCord(x, y+1) instanceof Terre) {
			this.map.set((largeurMap() * y) + x+largeurMap(), new Herbe());
		}
		
		if (y<this.hauteurMap && !estAir(blockQuiRemplace) && blockParCord(x, y+1) instanceof Herbe) {
			this.map.set((largeurMap() * y) + x+largeurMap(), new Terre());
		}
	}

	public int hauteurMap() {
		return this.hauteurMap;
	}

	public ObservableList<Block> getListMap() {
		return this.map;
	}
	
	public ArrayList<Block> getVoisins(Block blockCible) {
		ArrayList<Block> voisins = new ArrayList<Block>();

		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				if (x == 0 && y == 0)
					continue;

				int voisinX = blockCible.getX() + x;
				int voisinY = blockCible.getY() + y;

				if (voisinX >= 0 && voisinX < largeurMap() && voisinY >= 0 && voisinY < hauteurMap()) {
					voisins.add(blockParCord(voisinX,voisinY));
					blockParCord(voisinX,voisinY).setVisitee(true);
				}
			}
		}

		return voisins;
	}
	
	
	public void setChemin(ArrayList<Block> chemin) {
		this.chemin=chemin;
	}
	
	public ArrayList<Block> getChemin() {
		return this.chemin;
	}
	
	public ObservableList<Block> getMap() {
		return this.map;
	}

	public Block blockDe(char a) {
		Character charA = a;
		if(this.correspondanceIdBlock.containsKey(charA)) {
			return this.correspondanceIdBlock.get(charA);
		} else {
			return null;
		}
	}
	
	public boolean estAir(Block block) {
		if (block instanceof Air) {
			return true;
		}
		return false;
	}
}