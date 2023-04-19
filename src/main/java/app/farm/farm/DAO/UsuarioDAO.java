package app.farm.farm.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.farm.farm.entidades.Usuario;



@Repository
public class UsuarioDAO implements UsuarioInterfaz {
	
	@Autowired //inyecta la dependencia de session factory como se le indico en spring-crud-servlet.xml
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public Usuario validaUsuario(Usuario user) {
	
		Session miSesion=sessionFactory.openSession();
		System.out.println(user.getNumEmpleado());
		Query query=miSesion.createQuery("from Usuario where numEmpleado=:numEmpleado and password=:password");
		
		//Query query=miSesion.createNativeQuery("SELECT * FROM `usuarios` WHERE `numEmpleado`='1' AND `password`='123456'");
		//System.out.println(query.getQueryString());
		query.setParameter("numEmpleado", user.getNumEmpleado());
        query.setParameter("password", user.getPassword());
        Usuario usuarioTemporal=(Usuario)query.uniqueResult();
        System.out.println(usuarioTemporal);
		return usuarioTemporal;
	}

	@Override
	@Transactional
	public List<Usuario> AllUsers() {
		Session miSesion=sessionFactory.getCurrentSession();
		List<Usuario> listaUsuarios;
		Query query=miSesion.createQuery("from Usuario");
		listaUsuarios=query.getResultList();
		return listaUsuarios;
	}
	

	
}
