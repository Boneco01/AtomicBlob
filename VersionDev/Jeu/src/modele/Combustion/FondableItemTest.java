package modele.Combustion;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.Craft.CraftLancePierre;
import modele.Items.Item;
import modele.Items.ItemVide;
import modele.Items.Block.ItemMineraiFer;
import modele.Items.Block.ItemMineraiRadium;
import modele.Items.Block.ItemTerre;
import modele.Items.Craft.ItemFil;
import modele.Items.Craft.ItemLingotFer;


public class FondableItemTest {
	
	@Test
	public final void egaleTest() {
		FondableLingotFerPourTest lf = new FondableLingotFerPourTest();

		//Test Vrai
		ArrayList<Item> tcV = new ArrayList<>();
		ObservableList<Item> tableV= FXCollections.observableArrayList(tcV);
		
		tableV.add(new ItemMineraiFer());
		tableV.add(new ItemMineraiRadium());
		assertTrue("Uniquement bon materiaux",lf.egale(tableV));

		tableV.add(new ItemVide());
		tableV.add(new ItemVide());
		tableV.add(new ItemVide());
		tableV.add(new ItemVide());
		tableV.add(new ItemVide());
		tableV.add(new ItemVide());
		tableV.add(new ItemVide());
		
		assertTrue("Bon materiaux et Item Vide",lf.egale(tableV));
		
		//Test Faux
		
		ArrayList<Item> tcF = new ArrayList<>();
		ObservableList<Item> tableF= FXCollections.observableArrayList(tcF);
		
		assertFalse("Vide",lf.egale(tableF));
		
		tableF.add(new ItemMineraiFer());
		
		assertFalse("Uniquement du Fer",lf.egale(tableF));
		
		tableF.add(new ItemMineraiRadium());
		tableF.remove(0);
		
		assertFalse("Uniquement du Radium",lf.egale(tableF));
		
		tableF.add(new ItemMineraiRadium());
		tableF.add(new ItemMineraiFer());
		
		assertFalse("Trop de Radium",lf.egale(tableF));
		
		tableF.add(new ItemMineraiFer());
		tableF.remove(0);

		assertFalse("Trop de Fer",lf.egale(tableF));
		
		tableF.add(new ItemMineraiRadium());
		
		assertFalse("Trop de Tout",lf.egale(tableF));
		
		tableF.remove(0);
		tableF.remove(0);
		tableF.add(new ItemTerre());

		assertFalse("Autre Item Block",lf.egale(tableF));
		
		tableF.remove(2);
		tableF.add(new ItemFil());
		

		assertFalse("Autre Item Craft",lf.egale(tableF));
		
		tableF.remove(2);
		tableF.add(new ItemLingotFer());
		
		assertFalse("Item Crée",lf.egale(tableF));
	}
	
	@Test 
	public final void estDansTableauTest() {
		FondableLingotFerPourTest lf = new FondableLingotFerPourTest();
		
		//Test Vrai
		ArrayList<Integer> a = new ArrayList<>();
		
		a.add(0);
		
		assertTrue("Seul dans tableau",lf.estDansTableau(a, 0));//Seul dans tableau
		
		a.add(2);
		a.add(3);
		a.add(9);
		a.add(15);
		a.add(6);
		
		assertTrue("Première position",lf.estDansTableau(a, 0));//Première position
		assertTrue("Dernière position",lf.estDansTableau(a, 6));//Dernière position
		assertTrue("N'importe ou dans le tableau",lf.estDansTableau(a, 9));//N'importe ou dans le tableau
	
		a.add(-1);
		
		assertTrue("Chiffre negatif dans Tableau",lf.estDansTableau(a, -1));//Chiffre negatif
		
		a.add(3);
		
		assertTrue("Deux Occurence",lf.estDansTableau(a, 3));//Deux Occurence
		
		
		//Test Faux
		ArrayList<Integer> b = new ArrayList<>();
		assertFalse("Seul dans tableau",lf.estDansTableau(b,0));//Seul dans tableau
		
		b.add(0);
		b.add(2);
		b.add(6);
		b.add(6);
		
		assertFalse("Pas dans Tableau",lf.estDansTableau(b, 1));//Pas dans Tableau
		assertFalse("Chiffre negatif pas dans Tableau",lf.estDansTableau(b, -1));//Chiffre negatif
		
	}
	
	public final void definirConsomationTest() {
		FondableLingotFerPourTest lf = new FondableLingotFerPourTest();
		CraftLancePierre lp = new CraftLancePierre();
		assertFalse("Seul dans tableau",lf.definirComsomation()==lp.definirComsomation());
	}
}