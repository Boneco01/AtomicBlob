package modele.deplacements;

import java.util.ArrayList;

import modele.Personnage;
import modele.Terrain;
import modele.Blocks.Block;
/*
 * cette classe est un deplacement. Ce deplacement A* fonctionne mais souffre d'une mauvaise
 * optimisation. A utiliser avec parcimonie.
 */
public class DeplacementAStar extends Deplacement{
	private Terrain terrain; 
	private Personnage joueur;
	private ArrayList<Block> chemin;
	private int frameFixeAStar; //nb de frames au bout duquel un nouveau chemin est calcule.
	private int frameActuelleAStar;
	private int xJoueur;
	private int yJoueur;
	private int xDrone;
	private int yDrone;

	public DeplacementAStar(Personnage joueur) {
		this.joueur=joueur;
		this.chemin=new ArrayList<Block>();
		this.terrain=joueur.getMonde().getMap();
		this.frameFixeAStar=30;
		this.frameActuelleAStar=this.frameFixeAStar;
		this.xJoueur=0;
		this.yJoueur=0;
		this.xDrone=0;
		this.yDrone=0;
	}
	
	/*
	 * cette procedure calcule un nouveau chemin le moins couteux toutes les n frames
	 * d'un joueur a un drone
	 */
	public void AStar(Personnage drone) {
		if (this.frameActuelleAStar==0) {
			this.xJoueur=joueur.getXProperty().getValue()/64;
			this.yJoueur=joueur.getYProperty().getValue()/64;
			this.xDrone=(drone.getXProperty().getValue()+drone.getLargeur()/2)/64;	
			this.yDrone=(drone.getYProperty().getValue()+drone.getLargeur()/2)/64;
			Block blockDepart = terrain.blockParCord(xJoueur, yJoueur);
			Block blockArrivee = terrain.blockParCord(xDrone, yDrone);
			ArrayList<Block> open = new ArrayList<Block>();
			ArrayList<Block> closed = new ArrayList<Block>();
			open.add(blockDepart);

			while (open.size() > 0) {
				Block current = open.get(0);
				for (int i = 1; i < open.size(); i ++) {
					if (open.get(i).getFCost() < current.getFCost() || open.get(i).getFCost() == current.getFCost()) {
						if (open.get(i).getHCost() < current.getHCost())
							current = open.get(i);
					}
				}

				open.remove(current);
				closed.add(current);


				if (current == blockArrivee) {
					remonterChemin(terrain, blockDepart,blockArrivee);
				}
				for (Block voisin : getVoisins(current)) {
					if (!voisin.getCollision() && !closed.contains(voisin)) {


						int coutVersVoisin = current.getGCost() + getDistance(current, voisin);
						if (coutVersVoisin < voisin.getGCost() || !open.contains(voisin)) {
							voisin.setGCost(coutVersVoisin);
							voisin.setHCost(getDistance(voisin, blockArrivee));
							voisin.setParent(current);

							if (!open.contains(voisin))
								open.add(voisin);
						}
					}

				}
			}
		}

	}

	public ArrayList<Block> getVoisins(Block blockCible) {
		ArrayList<Block> voisins = new ArrayList<Block>();

		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				if (x == 0 && y == 0)
					continue;

				int voisinX = blockCible.getX() + x;
				int voisinY = blockCible.getY() + y;

				if (voisinX >= 0 && voisinX < this.terrain.largeurMap() && voisinY >= 0 && voisinY < this.terrain.hauteurMap()){
					if (voisinX>=this.xDrone-5 && voisinX<=this.xDrone+5 &&
							voisinY>=this.yDrone-5 && voisinY<=this.yDrone+5) { 

						voisins.add(this.terrain.blockParCord(voisinX,voisinY));
						this.terrain.blockParCord(voisinX,voisinY).setVisitee(true);
					}

				}
			}
		}
		return voisins;

	}
	
	/*
	 * cette procedure permet de generer le chemin 
	 */

	public void remonterChemin(Terrain terrain, Block blockDepart, Block blockArrivee) {
		ArrayList<Block> chemin = new ArrayList<Block>();
		Block blockActuel = blockArrivee;

		while (blockActuel != blockDepart) {
			blockActuel.setChemin(true);
			chemin.add(blockActuel);
			blockActuel = blockActuel.getParent();
		}
		chemin.add(this.terrain.blockParCord(this.joueur.getX()/64,this.joueur.getY()/64 ));
		this.chemin=chemin;
	}
	
	/*
	 * cette procedure permet au drone de rejoindre le joueur en parcourant le chemin construit par 
	 * le AStar case par case. 
	 */
	public void suitLeChemin(Personnage drone) {
		if (chemin.size()>1) {
			Block prochainBlock=this.chemin.get(1);
			if (this.xDrone<prochainBlock.getX()) {
				drone.goDroite();
			}
			if (this.xDrone>prochainBlock.getX()) {
				drone.goGauche();
			}
			if (this.yDrone>prochainBlock.getY()) {
				drone.goHaut();
			}
			if (this.yDrone<prochainBlock.getY()) {
				drone.goBas();
			}
			if((this.xDrone<this.xJoueur+50 || this.xDrone>this.xJoueur-50) && ( this.yDrone==this.yJoueur )) {
				drone.attaque(this.joueur, 1);
			}
		}
	}

	/*
	 * Cette fonction renvoie la distance entre deux blocks. La distance est calculee en diagonales
	 * et en ligne droite uniquement.
	 */
	
	public int getDistance (Block blockA, Block blockB) {
		int ecartX = Math.abs(blockA.getX() - blockB.getX());
		int ecartY = Math.abs(blockA.getY() - blockB.getY());

		if (ecartY > ecartX) {
			return ecartX*14 + 10* (ecartY-ecartX);
		}
		return 14*ecartY + (10 * (ecartX-ecartY));
	}

	@Override
	public void seDeplace(Personnage drone) {
		if (this.frameActuelleAStar==0) {
			this.frameActuelleAStar=this.frameFixeAStar;
		}
		this.frameActuelleAStar--;
		AStar(drone);
		suitLeChemin(drone);
	}

}
