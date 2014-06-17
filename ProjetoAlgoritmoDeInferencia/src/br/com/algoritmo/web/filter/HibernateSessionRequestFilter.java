package br.com.algoritmo.web.filter;
import javax.servlet.*;
import org.hibernate.SessionFactory;
import br.com.algoritmo.util.HibernateUtil;

public class HibernateSessionRequestFilter implements Filter{

	private SessionFactory sf;
	public void init(FilterConfig config) throws ServletException {
		sf = HibernateUtil.getSf();
	}
	
	public void doFilter (ServletRequest sRq, ServletResponse sRp, FilterChain chain) throws ServletException {
		try {
			sf.getCurrentSession().beginTransaction();
			chain.doFilter(sRq, sRp);
			sf.getCurrentSession().getTransaction().commit();
			sf.getCurrentSession().close();
		}catch (Throwable e){
			try {
				if(sf.getCurrentSession().getTransaction().isActive()){
					sf.getCurrentSession().getTransaction().rollback();
				}
			}catch (Throwable ex){
				ex.printStackTrace();
			
			}
			throw new ServletException(e);
		}
	}
	public void destroy (){
		
	}
}
