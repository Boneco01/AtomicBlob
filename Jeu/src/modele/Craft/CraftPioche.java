package modele.Craft;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import modele.Items.*;
import modele.Items.Block.ItemBois;
import modele.Items.Craft.ItemLingotMetal;
import modele.Items.Craft.ItemPioche;

public class CraftPioche extends Craft {
	private ArrayList<Item> craft;
	
	public CraftPioche () {
		super(new ItemPioche(),definirCraft());
		
	}
	public ArrayList<Item> definirCraft(){
		ArrayList<Item> a=new ArrayList<>();
		a.add(new ItemLingotMetal());
		a.add(new ItemLingotMetal());
		a.add(new ItemLingotMetal());
		a.add(new ItemVide());
		a.add(new ItemBois());
		a.add(new ItemVide());
		a.add(new ItemBois());
		a.add(new ItemVide());
		return a;
	}
}
