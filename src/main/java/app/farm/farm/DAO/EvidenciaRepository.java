package app.farm.farm.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.farm.farm.entidades.Evidencia;
import app.farm.farm.entidades.Usuario;

@Repository
public interface EvidenciaRepository  extends JpaRepository<Evidencia, Integer> {
	
	public List<Evidencia> findByUsuario(Usuario usuario);
	
}
