package jeu;

import cartes.*;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Sabot implements Iterable<Carte> {
	
	Carte[] cartes;
	private int nbCartes;
	private int nbOperations;
	
	public Sabot(Carte[] cartes) {
		this.cartes = cartes;
		this.nbCartes = cartes.length;
	}
	
	public int getNbCartes() {
		return nbCartes;
	}

	public boolean estVide() {
		return nbCartes == 0;
	}
	
	public void ajouterCarte(Carte newCarte) {
		if (nbCartes >= cartes.length) {
			throw new IllegalArgumentException("Le sabot est plein");
		}
		cartes[nbCartes] = newCarte;
		nbCartes++;
		nbOperations++;
	}
	
	public Carte piocher() {
		Iterator<Carte> it = iterator();

		if (!it.hasNext()) {
			throw new IllegalArgumentException("Le sabot est vide");
		}

		Carte carte = it.next();
		it.remove();
		return carte;
	}
	
	public Iterator<Carte> iterator() {
		return new Iterateur();
	}
	
	private class Iterateur implements Iterator<Carte>{
		private int indiceIterateur = 0;
		private boolean nextEffectue = false;
		private int nbOperationsReference = nbOperations;
		
		@Override
		public boolean hasNext() {
			return indiceIterateur < nbCartes;
		}
		
		@Override
		public Carte next() {
			verificationConcurrence();

			if (hasNext()) {
				Carte carte = cartes[indiceIterateur];
				indiceIterateur++;
				nextEffectue = true;
				return carte;
			} else {
				throw new NoSuchElementException();
			}
		}
		
		@Override
		public void remove() {
			verificationConcurrence();
			if (nbCartes < 1 || !nextEffectue) {
				throw new IllegalStateException();
			}
			
			for (int i = indiceIterateur - 1; i < nbCartes - 1; i++) {
				cartes[i] = cartes[i + 1];
			}
			indiceIterateur--;
		    nbCartes--;
		    nbOperations++;
		    nbOperationsReference++;
		}
		
		public void verificationConcurrence() {
			if (nbOperations != nbOperationsReference) {
				throw new ConcurrentModificationException();
			}
		}
	}
}
