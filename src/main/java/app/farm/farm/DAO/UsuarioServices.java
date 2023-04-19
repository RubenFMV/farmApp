package app.farm.farm.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.farm.farm.entidades.Usuario;

@Service
public class UsuarioServices {

	@Autowired
	private UsuarioRepository userRepository ;
	
	public List<Usuario> obtenerTodos(){
		return userRepository.findAll();
	}
	
	public Usuario obtenerUnUsuario(String numpleado) {
		try {
			System.out.println(numpleado);
			return userRepository.findById(numpleado).get();
		 } catch (Exception e) {
		    e.printStackTrace();
		    return null;
		 }
		
		
	}
	
}
