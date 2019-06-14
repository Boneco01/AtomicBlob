package modele.deplacements;

import java.util.ArrayList;
import java.util.Collections;

import modele.Personnage;
import modele.Terrain;
import modele.Blocks.Block;

public class DeplacementAStar extends Deplacement{
	private Terrain terrain;
	private int xDepart;
	private int yDepart;
	private int xArrivee;
	private int yArrivee;
	
	public DeplacementAStar(Terrain terrain, int xDepart, int yDepart, int xArrivee,  int yArrivee) {
		this.terrain=terrain;
		this.xDepart=xDepart;
		this.yDepart=yDepart;
		this.xArrivee=xArrivee;
		this.yArrivee=yArrivee;
		
	}
	
	public int AStar() {
		Block blockDepart = terrain.blockParCord(xDepart, yDepart);
		Block blockArrivee = terrain.blockParCord(xArrivee, yArrivee);
		ArrayList<Block> open = new ArrayList<Block>();
		ArrayList<Block> closed = new ArrayList<Block>();
		open.add(blockDepart);

		while (open.size() > 0) {
			Block current = open.get(0);
			for (int i = 1; i < open.size(); i ++) {
				if (open.get(i).getFCost().getValue() < current.getFCost().getValue() || open.get(i).getFCost().getValue() == current.getFCost().getValue()) {
					if (open.get(i).getHCost().getValue() < current.getHCost().getValue())
						current = open.get(i);
				}
			}

			open.remove(current);
			closed.add(current);

			if (current == blockArrivee) {
				remonterChemin(terrain, blockDepart,blockArrivee);
				return 0;
			}

			for (Block voisin : terrain.getVoisins(current)) {
				if (!voisin.getCollision() && !closed.contains(voisin)) {
					

					int coutVersVoisin = current.getGCost().getValue() + getDistance(current, voisin);
					if (coutVersVoisin < voisin.getGCost().getValue() || !open.contains(voisin)) {
						voisin.setGCost(coutVersVoisin);
						voisin.setHCost(getDistance(voisin, blockArrivee));
						voisin.calculerFCost();
						voisin.setParent(current);

						if (!open.contains(voisin))
						open.add(voisin);
					}
				}
			}
		}
		return 1;
	}
	
	public void remonterChemin(Terrain terrain, Block blockDepart, Block blockArrivee) {
		ArrayList<Block> path = new ArrayList<Block>();
		Block currentNode = blockArrivee;

		while (currentNode != blockDepart) {
			currentNode.setChemin(true);
			path.add(currentNode);
			currentNode = currentNode.getParent();
		}
		Collections.reverse(path);

		terrain.setChemin(path);
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
	public void seDeplace(Personnage personnage) {
		AStar();
	}
	
}
