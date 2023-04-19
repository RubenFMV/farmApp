package app.farm.farm.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.farm.farm.entidades.Evidencia;

@Repository
public interface EvidenciaRepository  extends JpaRepository<Evidencia, Integer> {

}
