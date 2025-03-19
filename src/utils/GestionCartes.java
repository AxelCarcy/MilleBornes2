package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;

import cartes.Carte;

public class GestionCartes {

	public static Carte extraire(List<Carte> liste) {
		if (liste.isEmpty())
			throw new IllegalArgumentException("La liste est vide");

		Random random = new Random();
		int index = random.nextInt(liste.size());
		return liste.remove(index);
	}

	public static Carte extraireIterateur(List<Carte> liste) {
		if (liste.isEmpty())
			throw new IllegalArgumentException("La liste est vide");

		Random random = new Random();
		int index = random.nextInt(liste.size());

		ListIterator<Carte> it = liste.listIterator();
		Carte carteListe = null;
		for (int i = 0; i <= index; i++)
			carteListe = it.next();

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
	
//	public static List<Carte> rassembler2(List<Carte> liste) {
//		ListIterator<Carte> it = liste.listIterator();
//		List<Carte> listeRassemblee = new ArrayList<>();
//		while (it.hasNext()) {
//			Carte elementActuel = it.next();
//			if (it.hasNext()) {
//				Carte prochainElement = it.next();
//				if (prochainElement.equals(elementActuel)) {
//					listeRassemblee.add(elementActuel);
//					while (prochainElement.equals(elementActuel) && it.hasNext()) { 
//						prochainElement = it.next();
//					}
//					elementActuel = it.previous();
//				}
//				listeRassemblee.add(elementActuel);
//			}
//		}
//		return listeRassemblee;
//	}
	
//	public static List<Carte> rassembler3(List<Carte> liste) {
//        List<Carte> resultat = new ArrayList<>();
//        ListIterator<Carte> it = liste.listIterator();
//        
//        while (it.hasNext()) {
//            Carte elementActuel = it.next();
//            if (!resultat.contains(elementActuel)) {
//                resultat.add(elementActuel);
//                ListIterator<Carte> it2 = liste.listIterator(it.nextIndex());
//                while (it2.hasNext()) {
//                    Carte prochainElement = it2.next();
//                    if (it.hasNext() && prochainElement.equals(elementActuel)) {
//                        resultat.add(prochainElement);
//                    }
//                }
//            }
//        }
//        
//        return resultat;
//    }
	
	public static List<Carte> rassembler(List<Carte> liste) {
		Map<Carte, Integer> occurences = new LinkedHashMap<>();
		
		for (Carte element : liste) {
			occurences.put(element, occurences.getOrDefault(occurences, 0) + 1);
		}
		
		List<Carte> listeRassembler = new ArrayList<>();
		for (Map.Entry<Carte, Integer> entry : occurences.entrySet()) {
			Carte element = entry.getKey();
			int count = entry.getValue();
			for (int i = 0; i < count; i++) {
				listeRassembler.add(element);
			}
		}
		return listeRassembler;
	}
	
	public static boolean verifierRassemblement(List<Carte> liste) {
        ListIterator<Carte> it1 = liste.listIterator();
        
        while (it1.hasNext()) {
            Carte elementActuel = it1.next();
            ListIterator<Carte> it2 = liste.listIterator(it1.nextIndex());
            
            while (it2.hasNext()) {
                Carte prochainElement = it2.next();
                if (prochainElement.equals(elementActuel))
                    return false;
            }
        }
        return true;
    }
	
	
	public static List<Carte> tabToList(Carte[] tab) {
		List<Carte> liste = new ArrayList<>();
		for (Carte carte : tab) {
			liste.add(carte);
		}
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
