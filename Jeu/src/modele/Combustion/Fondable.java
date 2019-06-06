package modele.Combustion;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import modele.Fabrication;
import modele.Items.Item;
import modele.Items.ItemVide;

public abstract class Fondable extends Fabrication {

	public boolean egale(ObservableList<Item> table) {
		int index = 0;
		boolean estPresent = true;
		ArrayList<Integer> listI = new ArrayList<>();
		while (index < super.comsomation.size() && estPresent) {
			estPresent = false;
			for (int y = 0; y < table.size(); y++)
				if ((table.get(y).getClass() == comsomation.get(index).getClass())) {
					estPresent = true;
					listI.add(y);
				}
			index++;
		}
		for (int i = 0; i < table.size(); i++) {
			if (estDansTableau(listI, i))
				System.out.print("");
			else if (table.get(i).getClass() != new ItemVide().getClass())
				return false;
		}
		if (estPresent)
			return true;
		else
			return false;
	}

	private boolean estDansTableau(ArrayList<Integer> listI, int i) {
		for (int y = 0; y < listI.size(); y++)
			if (i == listI.get(y))
				return true;
		return false;
	}
}
