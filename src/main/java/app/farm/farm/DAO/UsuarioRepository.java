package app.farm.farm.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.farm.farm.entidades.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
	
}
