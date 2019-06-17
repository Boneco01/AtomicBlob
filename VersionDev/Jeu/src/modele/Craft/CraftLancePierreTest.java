package modele.Craft;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.Craft.CraftLancePierre;
import modele.Items.Item;
import modele.Items.ItemVide;
import modele.Items.Block.ItemBois;
import modele.Items.Block.ItemMineraiFer;
import modele.Items.Block.ItemMineraiRadium;
import modele.Items.Block.ItemPierre;
import modele.Items.Block.ItemTerre;
import modele.Items.Craft.ItemFil;
import modele.Items.Craft.ItemLingotFer;

public class CraftLancePierreTest {

	@Test
	public final void egaleTest() {
		CraftLancePierre lp = new CraftLancePierre();

		// Test Vrai
		ArrayList<Item> tcV = new ArrayList<>();
		ObservableList<Item> tableV = FXCollections.observableArrayList(tcV);

		tableV.add(new ItemBois());
		tableV.add(new ItemPierre());
		tableV.add(new ItemBois());
		tableV.add(new ItemFil());
		tableV.add(new ItemBois());
		tableV.add(new ItemFil());
		tableV.add(new ItemVide());
		tableV.add(new ItemBois());
		tableV.add(new ItemVide());
		System.out.println(lp.egale(tableV));
		assertTrue("Bon Schema", lp.egale(tableV));

		// Test Faux

		ArrayList<Item> tcF = new ArrayList<>();
		ObservableList<Item> tableF = FXCollections.observableArrayList(tcF);

		tableF.add(new ItemMineraiFer());
		
		assertFalse("Item Rien a voir", lp.egale(tableF));

		tableF.remove(0);
		tableF.add(new ItemPierre());
		tableF.add(new ItemBois());
		tableF.add(new ItemBois());
		tableF.add(new ItemFil());
		tableF.add(new ItemBois());
		tableF.add(new ItemFil());
		tableF.add(new ItemVide());
		tableF.add(new ItemBois());
		tableF.add(new ItemVide());

		assertFalse("Deux Premier Inversé", lp.egale(tableF));

		tableF.remove(1);
		tableF.add(0, new ItemVide());
		
		assertFalse("Premier et Dernier Inversé", lp.egale(tableF));
		
		tableF.remove(0);
		tableF.add(0, new ItemBois());
		tableF.remove(7);
		tableF.remove(7);
		tableF.add(new ItemVide());
		tableF.add(new ItemBois());

		assertFalse("Deux Dernier Inversé", lp.egale(tableF));
	
		tableF.remove(0);
		tableF.remove(0);
		tableF.remove(0);
		tableF.remove(0);
		tableF.remove(0);
		tableF.remove(0);
		tableF.remove(0);
		tableF.remove(0);
		tableF.remove(0);
		tableF.add(new ItemPierre());
		tableF.add(new ItemBois());
		tableF.add(new ItemBois());
		tableF.add(new ItemFil());
		tableF.add(new ItemBois());
		tableF.add(new ItemFil());
		tableF.add(new ItemBois());

		assertFalse("Bon schema sans les ItemVide", lp.egale(tableF));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public final void egaleExceptionTest() {
		CraftLancePierre lp = new CraftLancePierre();
		ArrayList<Item> tcF = new ArrayList<>();
		ObservableList<Item> tableF = FXCollections.observableArrayList(tcF);
		assertTrue("Item Rien a voir", lp.egale(tableF));
	}
}
