package modele.deplacements;

import java.util.ArrayList;
import modele.Personnage;
import modele.Terrain;
import modele.Blocks.Block;

public class DeplacementAStar extends Deplacement{
	private Terrain terrain; 
	private Personnage Joueur;
	private ArrayList<Block> chemin;
	private int frameFixe; //calcul d'un nouveau chemin tout les x frames
	private int frameActuelle;
	private int xJoueur;
	private int yJoueur;
	private int xDrone;
	private int yDrone;

	public DeplacementAStar(Personnage joueur) {
		this.Joueur=joueur;
		this.chemin=new ArrayList<Block>();
		this.terrain=Joueur.getMonde().getMap();
		this.frameFixe=30;
		this.frameActuelle=this.frameFixe;
	}

	public void AStar(Personnage drone) {
		if (this.frameActuelle==0) {
			this.xJoueur=Joueur.getXProperty().getValue()/64;
			this.yJoueur=Joueur.getYProperty().getValue()/64;
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

				for (Block voisin : terrain.getVoisins(current)) {
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

	public void remonterChemin(Terrain terrain, Block blockDepart, Block blockArrivee) {
		ArrayList<Block> path = new ArrayList<Block>();
		Block blockActuel = blockArrivee;

		while (blockActuel != blockDepart) {
			blockActuel.setChemin(true);
			path.add(blockActuel);
			blockActuel = blockActuel.getParent();
		}
		path.add(this.terrain.blockParCord(this.Joueur.getX()/64,this.Joueur.getY()/64 ));
		this.chemin=path;
	}

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
		}
	}


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
		if (this.frameActuelle==0) {
			this.frameActuelle=this.frameFixe;
		}
		this.frameActuelle--;
		AStar(drone);
		suitLeChemin(drone);
	}

}
