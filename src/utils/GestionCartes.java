package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
//import java.util.Map;
//import java.util.LinkedHashMap;

import cartes.Carte;

public class GestionCartes {
	private static Random random = new Random();

	private GestionCartes() {
		throw new IllegalStateException("Utility class");
	}

	public static Carte extraire(List<Carte> liste) {
		if (liste.isEmpty())
			throw new IllegalArgumentException("La liste est vide");

		int index = random.nextInt(liste.size());
		return liste.remove(index);
	}

	public static Carte extraireIterateur(List<Carte> liste) {
		if (liste.isEmpty())
			throw new IllegalArgumentException("La liste est vide");

		int index = random.nextInt(liste.size());

		ListIterator<Carte> it = liste.listIterator(index);
		Carte carteListe = it.next();
		it.remove();
		return carteListe;
	}

	public static List<Carte> melanger(List<Carte> liste) {
		List<Carte> newList = new ArrayList<>();
		Carte newCarte = null;
		while (!liste.isEmpty()) {
			newCarte = extraire(liste);
			newList.add(newCarte);
		}
		return newList;
	}

	public static boolean verifierMelange(List<Carte> liste1, List<Carte> liste2) {
		if (liste1.size() != liste2.size())
			return false;

		for (Carte element : liste1) {
			if (Collections.frequency(liste1, element) != Collections.frequency(liste2, element))
				return false;
		}
		return true;
	}

	public static List<Carte> rassembler(List<Carte> liste) {
		List<Carte> resultat = new ArrayList<>();
		for (Carte element : liste) {
			if (!resultat.contains(element)) {
				resultat.add(element);
				Collections.frequency(liste, element);
			}
		}

		return resultat;
	}

	// TP5
//	public static List<Carte> rassembler2(List<Carte> liste) {
//		Map<Carte, Integer> occurences = new LinkedHashMap<>();
//
//		for (Carte element : liste) {
//			occurences.put(element, occurences.getOrDefault(occurences, 0) + 1);
//		}
//
//		List<Carte> listeRassembler = new ArrayList<>();
//		for (Map.Entry<Carte, Integer> entry : occurences.entrySet()) {
//			Carte element = entry.getKey();
//			int count = entry.getValue();
//			for (int i = 0; i < count; i++) {
//				listeRassembler.add(element);
//			}
//		}
//		return listeRassembler;
//	}

	public static boolean verifierRassemblement(List<Carte> liste) {
		ListIterator<Carte> it1 = liste.listIterator();
		for (Carte elementActuel : liste) {
			Carte elementSuivant = it1.next();
			if (! elementActuel.equals(elementSuivant)) {
				verifierListe(liste, it1.nextIndex(), elementSuivant);
			}
		}
		return true;
	}
	
	public static List<Carte> verifierListe(List<Carte> liste, int index, Carte elementActuel) {
		ListIterator<Carte> it = liste.listIterator(index);
		for (Carte element : liste) {
			if (element.equals(elementActuel)) {
				it.remove();
			}
		}
		return liste;
	}

	public static List<Carte> tabToList(Carte[] tab) {
		List<Carte> liste = new ArrayList<>();
		Collections.addAll(liste, tab);
		return liste;
	}

	public static Carte[] listToTab(List<Carte> liste) {
		Carte[] tab = new Carte[liste.size()];
		for (int i = 0; i < liste.size(); i++) {
			tab[i] = liste.get(i);
		}
		return tab;
	}
}
