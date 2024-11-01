package francescabattistini.gestioneviaggi.repository;

import francescabattistini.gestioneviaggi.entities.Dipendente;
import francescabattistini.gestioneviaggi.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface RepositoryPrenotazione extends JpaRepository< Prenotazione, Long> {
    Optional<Prenotazione> findByIddipendenteAndDataPrenotazione(Dipendente dipendente, LocalDate dataPrenotazione);
}
