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
	
	public Usuario obtenerUnUsuario(String numEmpleado) {
		System.out.println(userRepository.existsByNumEmpleado(numEmpleado));
		return userRepository.findByNumEmpleado(numEmpleado).get(0);
	}
	
	public void   guardarUsuario(Usuario user) {
		userRepository.save(user);
	}
	
}
