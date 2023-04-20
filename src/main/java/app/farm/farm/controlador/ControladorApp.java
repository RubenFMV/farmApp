package app.farm.farm.controlador;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.farm.farm.DAO.EvidenciaServices;
import app.farm.farm.DAO.UsuarioServices;
import app.farm.farm.entidades.Evidencia;
import app.farm.farm.entidades.Usuario;
import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/frm")
public class ControladorApp {
	
	@Autowired
	private UsuarioServices userRepository;
	
	@Autowired
	private EvidenciaServices evidenciaRepo;
	
	@GetMapping("/listaUsuarios")
	public List<Usuario> listaEstudiantes(){
		List<Usuario> lista = userRepository.obtenerTodos();
		return lista;
	}
	
	@PostMapping("/creaUsusario")
	public void guardarUsuario(@RequestBody Usuario user) {
		userRepository.guardarUsuario(user);
	}
	
	@PutMapping("/creaUsusario")
	public void actualizaUsuario(@RequestBody Usuario user) {
		userRepository.guardarUsuario(user);
	}
	
	@GetMapping("/consultaUsuario")
	public void buscaUsuario(@RequestBody Usuario user, HttpSession sesion) {
		Usuario userTemp=userRepository.obtenerUnUsuario(user.getNumEmpleado());
		if(userTemp==null){
			
		}else {
			sesion.setAttribute("UsuarioSesion", userTemp);
		}
		System.out.println(sesion.getAttribute("UsuarioSesion"));
	}
	
	@GetMapping("/cerrar")
	public ResponseEntity<String> cerrarSession(HttpSession session) {
		session.removeAttribute("UsuarioSesion");
		if(session.getAttribute("UsuarioSesion")==null) {
			return new ResponseEntity<String>("Sesión cerrada conrrectamente", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Algo salio mal", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	//-------------------------------  EVIDENCIAS -----------------------------------//
	
	@GetMapping("/bitacora")
	public List<Evidencia> muestraBitacora(){
		List<Evidencia> lista =new ArrayList<Evidencia>();
		List<Usuario> listaUsuarios = userRepository.obtenerTodos();
		for(Usuario user:listaUsuarios) {
			for(Evidencia evi: user.getEvidencias()) {
				lista.add(evi);
			}
		}
		return lista;
	}
	
	@PostMapping("/guardarEvidencia")
	public void guardaEvidencia(@RequestBody Evidencia evidencia, HttpSession sesion) {
		Usuario userTemp=(Usuario) sesion.getAttribute("UsuarioSesion");
		evidencia.setUsuario(userTemp);
		evidenciaRepo.guardaEvidencia(evidencia);
	}
	
	@GetMapping("/consultaEvidenciaUser")
	public List<Evidencia> consutlaEvidenciaUser(HttpSession sesion){
		Usuario userTemp=(Usuario) sesion.getAttribute("UsuarioSesion");
		return evidenciaRepo.listaEvidenciaUsuario(userTemp);
	}
}
