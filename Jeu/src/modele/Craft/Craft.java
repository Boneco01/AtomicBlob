package modele.Craft;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import modele.Fabrication;
import modele.Items.Item;
import modele.Items.ItemCraft;

public abstract class Craft extends Fabrication{

	public boolean egale(ObservableList<Item> table) {
	for (int index = 0; index < super.comsomation.size(); index++) {
			if ((table.get(index).getClass() != comsomation.get(index).getClass())) {
				System.out.println(table.get(index).getClass() + "=" + comsomation.get(index).getClass()+"a l'indice "+ index);
				return false;
			}
		}
		return true;

	}

}
