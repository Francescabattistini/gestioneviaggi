package francescabattistini.gestioneviaggi.repository;

import francescabattistini.gestioneviaggi.entities.Dipendente;

import java.util.Optional;

public interface DipendenteRepository {
    Optional<Dipendente> findByEmail(String email);
}
