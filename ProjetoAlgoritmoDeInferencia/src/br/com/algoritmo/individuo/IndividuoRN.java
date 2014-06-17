package br.com.algoritmo.individuo;

import java.util.List;

import br.com.algoritmo.util.DAOFactory;

public class IndividuoRN {
	
	private IndividuoDAO individuoDAO;
	
	public IndividuoRN() {
		individuoDAO = DAOFactory.criarUsuario();
	}

	public Individuo getIdIndividuo(Integer idIndividuo){
		return individuoDAO.getById(idIndividuo);
	}
	
	public Individuo getByIdLattes (Integer idLattes) {
		return individuoDAO.getByIdLattes(idLattes);
	}
	
	public void salvar(Individuo individuo){
		Integer idIndividuo = individuo.getIdIndividuo();
		if(idIndividuo == 0 || idIndividuo == null){
			individuoDAO.salvar(individuo);
		}else {
			individuoDAO.alter(individuo);
		}
	}
	public void excluir (Individuo usuario){
		individuoDAO.delete(usuario);
	}
	
	public List <Individuo> listar (){
		return individuoDAO.listar();
	}
}