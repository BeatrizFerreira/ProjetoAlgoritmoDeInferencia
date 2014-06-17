package br.com.algoritmo.util;

import br.com.algoritmo.individuo.IndividuoDAO;
import br.com.algoritmo.individuo.IndividuoDAOHibernate;

public class DAOFactory {

	public static IndividuoDAO criarUsuario (){
		
		IndividuoDAOHibernate usuarioDAO = new IndividuoDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSf().getCurrentSession());
		
		return usuarioDAO;
	}
}
