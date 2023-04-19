package app.farm.farm.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.farm.farm.entidades.Evidencia;
import app.farm.farm.entidades.Usuario;

@Repository
public class EvidenciaDAO implements EvidenciaInterfaz {
	
	
	@Autowired //inyecta la dependencia de session factory como se le indico en spring-crud-servlet.xml
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void agregaEvidencia(Evidencia LaEvidencia) {
		Session miSesion=sessionFactory.getCurrentSession();
		miSesion.save(LaEvidencia);
	}

	@Override
	@Transactional
	public List<Evidencia> listaEvidencia(Usuario user) {
		Session miSesion=sessionFactory.getCurrentSession();
		List<Evidencia> listaEvidencia;
		Query query=miSesion.createQuery("from Evidencia where numEmpleado=:numEmpleado");
		query.setParameter("numEmpleado", user.getNumEmpleado());
		listaEvidencia=query.getResultList();
		return listaEvidencia;
	}
	
	@Override
	@Transactional
	public List<Evidencia> muestraBitacora() {
		Session miSesion=sessionFactory.getCurrentSession();
		List<Evidencia> listaEvidencia;
		Query query=miSesion.createQuery("from Evidencia");
		listaEvidencia=query.getResultList();
		return listaEvidencia;
	}
	
	

}
