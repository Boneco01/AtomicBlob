package modele;

import java.util.Scanner;

public class classeTestPersoMap {

	public static void main(String[] args) {
		
		//Faire la classe jeu qui comprends toutes les autres classes
		//Faire une classe mapJoueur ou un attribut mapJoueur dans jeu ?
		//Jeu correspond aux méthodes dans cette classe de test, c'est le "moteur de jeu".
		//Gerer les collisions.
		
		//Ubuntu
		//String cheminMap = "/home/etudiants/info/prynkiewicz/prive/S2/Projet/AtomicBlob/Map/MapTestModele.csv";
		
		//Windows
		String cheminMap = "C:\\Users\\Paul\\Documents\\DUT\\S2\\Projet\\AtomicBlob\\Map\\MapTestModele.csv";
		
		Terrain testTerrain = new Terrain(cheminMap);
		Personnage joueur = new Joueur(10, 1, 32, "Boneco", 5, 4);
		Personnage[][] mapJoueur = new Personnage[testTerrain.largeurMap()][testTerrain.hauteurMap()];
		mapJoueur = remplirMapJoueur(mapJoueur, joueur);
		
		Scanner saisie = new Scanner(System.in);
		String commande = null;
		
		System.out.println("Début du test");
		afficherMapJoueurDansConsole(mapJoueur);
		System.out.println();
		afficherTerrainDansConsole(testTerrain);
		
		//Game Loop
		while(commande==null) {
		
			commande = saisie.nextLine();
			if(!checkCommande(commande)) {
				System.out.println("Mauvaise commande.");
			} else {
				mapJoueur[joueur.getY()][joueur.getX()] = null;
				switch(commande) {
				
					case "godroite" : joueur.goDroite();
									  break;
					
					case "gogauche" : joueur.goGauche();
					  				  break;
					  				  
					case "sauter" : joueur.saute();
	  								break;
	  								
					case "tomber" : joueur.tombe();
									break;
					
					default : break;
				}
				
				mapJoueur = repositionnerPersonnage(joueur, mapJoueur);
				afficherMapJoueurDansConsole(mapJoueur);
				System.out.println();
				afficherTerrainDansConsole(testTerrain);
			}
			
			commande = null;
		}
		
		saisie.close();
		
		
	}
	
	private static boolean checkCommande(String commande) {
		if(!commande.equals("godroite") && !commande.equals("gogauche") && !commande.equals("sauter") && !commande.equals("tomber")) {
			return false;
		}
		
		return true;
	}
	
	private static Personnage[][] remplirMapJoueur(Personnage[][] mapJoueur, Personnage joueur) {
		for(int i=0; i<mapJoueur.length;i++) {
			for(int j=0;j<mapJoueur[i].length;j++) {
				if(joueur.getY()==i && joueur.getX()==j) {
					mapJoueur[i][j] = joueur;
				} else {
					mapJoueur[i][j] = null;
				}
			}
		}
		
		return mapJoueur;
	}
	
	private static void afficherMapJoueurDansConsole(Personnage[][] mapJoueur) {
		
		for(int i=0; i<mapJoueur.length;i++) {
			for(int j=0;j<mapJoueur[i].length;j++) {
				if(mapJoueur[i][j] != null) {
					System.out.print("J|");
				} else {
					System.out.print(" |");
				}
			}
			System.out.println();
		}
	}
	
	private static void afficherTerrainDansConsole(Terrain terrain) {
		
		for(int i=0;i<terrain.hauteurMap();i++) {
			for(int j=0;j<terrain.largeurMap();j++) {
				System.out.print(terrain.getMap().get(j).getId() + "|");
			}
			System.out.println();
		}
		
	}
	
	private static Personnage[][] repositionnerPersonnage(Personnage joueur, Personnage[][] mapJoueur) {
		
		mapJoueur[joueur.getY()][joueur.getX()] = joueur;
		return mapJoueur;
	}
	
	
}
