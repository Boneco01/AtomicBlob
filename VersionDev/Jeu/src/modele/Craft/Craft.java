package modele.Craft;

import javafx.collections.ObservableList;
import modele.Fabrication;
import modele.Items.Item;
public abstract class Craft extends Fabrication{

	public boolean egale(ObservableList<Item> table) {
	for (int index = 0; index < super.consommation.size(); index++) {
			if ((table.get(index).getClass() != consommation.get(index).getClass())) {
				System.out.println(table.get(index).getClass() + "=" + consommation.get(index).getClass()+"a l'indice "+ index);
				return false;
			}
		}
		return true;

	}

}
