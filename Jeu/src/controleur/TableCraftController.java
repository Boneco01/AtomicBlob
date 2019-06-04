package controleur;

import javafx.scene.layout.GridPane;
import modele.TableCraft;

public class TableCraftController {
	private TableCraft tcm;
	private GridPane tcv;
	public TableCraftController(GridPane tableCraft) {
		tcv= tableCraft;
		tcm=new TableCraft();
	}

}
