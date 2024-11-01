package francescabattistini.gestioneviaggi.repository;

import francescabattistini.gestioneviaggi.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {
}