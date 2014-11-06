package br.com.algoritmo.extracao.desktop.Pt_BR;

public class WordNotFoundExceptionPtBR extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WordNotFoundExceptionPtBR(String word) {
		super("Word '" + word + "' not found!");
	}
}
