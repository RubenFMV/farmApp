package app.farm.farm.DAO;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.farm.farm.entidades.Evidencia;
import app.farm.farm.entidades.Usuario;

@Service
public class EvidenciaServices {
	
	@Autowired
	private EvidenciaRepository evidenciaRepo;
	
	
	
	public List<Evidencia> muestraListaEvidencias(){
		return evidenciaRepo.findAll();
	}
	
	public List<Evidencia> listaEvidenciaUsuario(Usuario user){
		return evidenciaRepo.findByUsuario(user);
	}
	
	public void guardaEvidencia(Evidencia evidencia) {
		Calendar fecha = Calendar.getInstance();
		System.out.println(fecha);
		evidencia.setFecha(fecha);
		evidenciaRepo.save(evidencia);
	}
}
