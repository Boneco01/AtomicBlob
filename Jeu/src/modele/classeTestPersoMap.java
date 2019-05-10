package modele;

public class classeTestPersoMap {

	public static void main(String[] args) {
		
		String cheminMap = "/home/etudiants/info/prynkiewicz/prive/S2/Projet/AtomicBlob/Map/MapTestModele.csv";
		
		Terrain testTerrain = new Terrain();
		System.out.println(testTerrain.tailleRecupMap(cheminMap));
		
	}
	
}
