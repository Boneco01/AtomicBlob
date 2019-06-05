package modele;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.Blocks.Air;
import modele.Blocks.Block;
import modele.Blocks.Bois;
import modele.Blocks.Herbe;
import modele.Blocks.MineraiFer;
import modele.Blocks.MineraiRadium;
import modele.Blocks.Pierre;
import modele.Blocks.Sable;
import modele.Blocks.Terre;

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

	public Block blockDe(char a) {
		switch (a) {
		case 'T':
			return new Terre();
		case 'H':
			return new Herbe();
		case 'P':
			return new Pierre();
		case 'A':
			return new Air();
		case 'S':
			return new Sable();
		case 'B':
			return new Bois();
		case 'F':
			return new MineraiFer();
		case 'R':
			return new MineraiRadium();
		default:
			return new Pierre();
		}
	}
	
	public boolean estAir(Block block) {
		if (block instanceof Air) {
			return true;
		}
		return false;
	}
}