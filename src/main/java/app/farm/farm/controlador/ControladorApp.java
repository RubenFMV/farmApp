package app.farm.farm.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.farm.farm.DAO.EvidenciaRepository;
import app.farm.farm.DAO.EvidenciaServices;
import app.farm.farm.DAO.UsuarioRepository;
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
	
	@GetMapping("/bitacora")
	public List<Evidencia> muestraBitacora(){
		List<Evidencia> lista= evidenciaRepo.muestraListaEvidencias();
		return lista;
	}
	
	@PostMapping("/guardarEvidencia")
	public void guardaEvidencia(@RequestBody Evidencia evidencia) {
		Usuario user=userRepository.obtenerUnUsuario("1");
		System.out.println(user);
		evidencia.setUsuario(user);
		evidenciaRepo.guardaEvidencia(evidencia);
	}
	
}
