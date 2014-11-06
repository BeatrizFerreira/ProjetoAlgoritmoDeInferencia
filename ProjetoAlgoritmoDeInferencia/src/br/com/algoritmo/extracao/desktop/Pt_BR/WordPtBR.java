package br.com.algoritmo.extracao.desktop.Pt_BR;

import java.util.*;

public class WordPtBR {
	private MeaningPtBR[] meanings;
	@SuppressWarnings("rawtypes")
	private ArrayList[] dumbSynonyms;
	private int count;

	public WordPtBR(String value, int numMeanings) {
		meanings = new MeaningPtBR[numMeanings];
		dumbSynonyms = new ArrayList[5];
		for (int i = 0; i < 5; i++) {
			dumbSynonyms[i] = new ArrayList<String>();
		}
		count = 0;
	}

	public void addMeaning(String line) {
		meanings[count] = new MeaningPtBR(line, dumbSynonyms);
		count++;
	}

}
