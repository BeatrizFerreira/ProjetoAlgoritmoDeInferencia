package br.com.algoritmo.individuo;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class IndividuoDAOHibernate implements IndividuoDAO {

	private Session session;
	public void setSession(Session s){
		session = s;
	}

	public void salvar(Individuo individuo) {
		session.save(individuo);

	}

	public void delete(Individuo individuo) {
		session.delete(individuo);

	}

	public void alter(Individuo individuo) {
		session.update(individuo);
		session.close();

	}

	public Individuo getById(Integer idIndividuo) {
		return (Individuo) session.get(Individuo.class, idIndividuo);
	}

	public Individuo getByIdLattes(Integer idLattes) {
		String hql = "SELECT i FROM Individuo i WHERE i.idLattes =:param";
		Query consulta = session.createQuery(hql);
		consulta.setInteger("param", idLattes);
		return (Individuo) consulta.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Individuo> listar() {
		return session.createCriteria(Individuo.class).list();
	}
}
