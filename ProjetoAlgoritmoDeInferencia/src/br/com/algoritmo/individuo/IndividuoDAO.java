package br.com.algoritmo.individuo;

import java.util.List;

public interface IndividuoDAO {
	
	public void salvar(Individuo individuo);
	public void delete(Individuo individuo);
	public void alter(Individuo individuo);
	public Individuo getById (Integer idIndividuo);
	public Individuo getByIdLattes (Integer idLattes);
	public List<Individuo> listar ();

}