package modele.Combustion;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import modele.Fabrication;
import modele.Items.Item;

public abstract class Fondable extends Fabrication {
	
	public boolean egale(ObservableList<Item> table) {
		int index = 0;
		boolean estPresent=true;
		while(index < super.comsomation.size() && estPresent) {
			estPresent=false; 
			for(int y=0;y<table.size();y++)
				if ((table.get(y).getClass() == comsomation.get(index).getClass())) {
					estPresent=true;
				}
			index++;
			}
		if(estPresent)
			return true;
		else 
			return false;
		}


}
