package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.Combustion.FondableLingotFerPourTest;
import modele.Craft.CraftLancePierre;
import modele.Items.Item;
import modele.Items.ItemVide;
import modele.Items.Block.ItemMineraiFer;
import modele.Items.Block.ItemMineraiRadium;
import modele.Items.Block.ItemTerre;
import modele.Items.Craft.ItemFil;
import modele.Items.Craft.ItemLingotFer;

public class FondableLingotFerTest {
	@Test
	public final void egaleTest() {
		FondableLingotFerPourTest lf = new FondableLingotFerPourTest();

		// Test Vrai si contient les deux matériaux peux importe placé ou et dans
		// n'importe quelle ordre
		ArrayList<Item> tcV = new ArrayList<>();
		ObservableList<Item> tableV = FXCollections.observableArrayList(tcV);

		tableV.add(new ItemMineraiFer());
		tableV.add(new ItemMineraiRadium());

		assertTrue("Uniquement bon materiaux", lf.egale(tableV));

		tableV.remove(0);
		tableV.remove(0);
		tableV.add(0, new ItemMineraiFer());
		tableV.add(0, new ItemMineraiRadium());

		assertTrue("Uniquement bon materiaux mais inversé", lf.egale(tableV));

		tableV.add(new ItemVide());
		tableV.add(new ItemVide());
		tableV.add(new ItemVide());
		tableV.add(new ItemVide());
		tableV.add(new ItemVide());
		tableV.add(new ItemVide());
		tableV.add(new ItemVide());

		assertTrue("Bon materiaux et Item Vide", lf.egale(tableV));
		for (int index = 0; index < 1000000000; index++) {
			ArrayList<Item> aMettre= new ArrayList<Item>();
			aMettre.add(new ItemVide());
			aMettre.add(new ItemVide());
			aMettre.add(new ItemVide());
			aMettre.add(new ItemVide());
			aMettre.add(new ItemVide());
			aMettre.add(new ItemVide());
			aMettre.add(new ItemVide());
			aMettre.add(new ItemMineraiFer());
			aMettre.add(new ItemMineraiRadium());
			for (int i = 0; i < 9; i++)
				tableV.remove(0);
			for(int i=0; i<9;i++) {
				int aleaInd= (int)(Math.random()*i);
				int aleaItem= (int)(Math.random()*(8-i));
				tableV.add(aleaInd,aMettre.get(aleaItem));
				aMettre.remove(aleaItem);
				}
			assertTrue("Bon materiaux et Item Vide placé n'importe ou a l'indice "+ index, lf.egale(tableV));
		
		}
		/*
		 * Test Faux Si la table ne contient pas assez ou trop de composant de l'un ou
		 * de l'autre, ou contient un autre élément non référencée
		 * 
		 */
		ArrayList<Item> tcF = new ArrayList<>();
		ObservableList<Item> tableF = FXCollections.observableArrayList(tcF);

		assertFalse("Vide", lf.egale(tableF));

		tableF.add(new ItemMineraiFer());

		assertFalse("Uniquement du Fer", lf.egale(tableF));

		tableF.add(new ItemMineraiRadium());
		tableF.remove(0);

		assertFalse("Uniquement du Radium", lf.egale(tableF));

		tableF.add(new ItemMineraiRadium());
		tableF.add(new ItemMineraiFer());

		assertFalse("Trop de Radium", lf.egale(tableF));

		tableF.add(new ItemMineraiFer());
		tableF.remove(0);

		assertFalse("Trop de Fer", lf.egale(tableF));

		tableF.add(new ItemMineraiRadium());

		assertFalse("Trop de Tout", lf.egale(tableF));

		tableF.remove(0);
		tableF.remove(0);
		tableF.add(new ItemTerre());

		assertFalse("Autre Item Block", lf.egale(tableF));

		tableF.remove(2);
		tableF.add(new ItemFil());

		assertFalse("Autre Item Craft", lf.egale(tableF));

		tableF.remove(2);
		tableF.add(new ItemLingotFer());

		assertFalse("Item Crée", lf.egale(tableF));
	}

	@Test
	public final void estDansTableauTest() {

		FondableLingotFerPourTest lf = new FondableLingotFerPourTest();

		// Test Vrai vérifie que le int donné est bien dans la liste
		ArrayList<Integer> a = new ArrayList<>();

		a.add(0);

		assertTrue("Seul dans tableau", lf.estDansTableau(a, 0));

		a.add(2);
		a.add(3);
		a.add(9);
		a.add(15);
		a.add(6);

		assertTrue("Première position", lf.estDansTableau(a, 0));
		assertTrue("Dernière position", lf.estDansTableau(a, 6));
		assertTrue("N'importe ou dans le tableau", lf.estDansTableau(a, 9));

		a.add(-1);

		assertTrue("Chiffre negatif dans Tableau", lf.estDansTableau(a, -1));

		a.add(3);

		assertTrue("Deux Occurence", lf.estDansTableau(a, 3));

		// Test Faux si le int n'est pas dans la liste
		ArrayList<Integer> b = new ArrayList<>();
		assertFalse("Liste vide", lf.estDansTableau(b, 0));

		b.add(0);
		b.add(2);
		b.add(6);
		b.add(6);

		assertFalse("Pas dans Tableau", lf.estDansTableau(b, 1));
		assertFalse("Chiffre negatif pas dans Tableau", lf.estDansTableau(b, -1));

	}

	public final void definirConsomationTest() {
		FondableLingotFerPourTest lf = new FondableLingotFerPourTest();
		CraftLancePierre lp = new CraftLancePierre();

		// Test vrai doit toujours être egale a lui même
		assertTrue("comparer a lui même", lf.definirComsomation() == lf.getComsomation());

		// Test Faux doit toujours être différent de tous les autres objets
		assertFalse("Comparer a un autre objet", lf.definirComsomation() == lp.definirComsomation());
	}
}
