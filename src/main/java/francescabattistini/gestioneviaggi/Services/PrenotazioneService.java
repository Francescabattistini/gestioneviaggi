package francescabattistini.gestioneviaggi.Services;

import francescabattistini.gestioneviaggi.PayloadDTO.PrenotazioneDto;
import francescabattistini.gestioneviaggi.entities.Dipendente;
import francescabattistini.gestioneviaggi.entities.Prenotazione;
import francescabattistini.gestioneviaggi.entities.Viaggio;
import francescabattistini.gestioneviaggi.exceptions.BadRequestException;
import francescabattistini.gestioneviaggi.exceptions.NotFoundException;
import francescabattistini.gestioneviaggi.repository.RepositoryPrenotazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {
    @Autowired
    private RepositoryPrenotazione repositoryPrenotazione;

    @Autowired
    private ViaggioService viaggioService;

    @Autowired
    private DipendenteService dipendenteService;


    public Page<Prenotazione> findPrenotazioneAll(int page, int size, String sortBy) {
        if (page > 15) page = 15;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return repositoryPrenotazione.findAll(pageable);
    }

    public Prenotazione findPrenotazioneById( long prenotazioneId) {
        return this.repositoryPrenotazione.findById(prenotazioneId).orElseThrow(() -> new NotFoundException(prenotazioneId));
    }

    public Prenotazione save(PrenotazioneDto body) {

        Dipendente dipendente = this.dipendenteService.findById(body.iddipendente());
        Viaggio viaggio = this.viaggioService.findViaggioById(body.idviaggio());

       repositoryPrenotazione.findByIddipendenteAndDataPrenotazione(dipendente, body.dataPrenotazione())
                .ifPresent(existingPrenotazione -> {
                    throw new BadRequestException("Questa data non è disponibile ");
                });

        Prenotazione newPrenotazione = new Prenotazione( body.dataPrenotazione(), body.preferenzeDipendente(),viaggio, dipendente);
        return this.repositoryPrenotazione.save(newPrenotazione);
    }
    public Prenotazione findByIdAndUpdate(long id, PrenotazioneDto body) {
        Prenotazione found = this.findPrenotazioneById(id);

        found.setPreferenzeDipendente(body.preferenzeDipendente());
        found.setDataPrenotazione(body.dataPrenotazione());

        return this.repositoryPrenotazione.save(found);
    }

    public void findByIdAndDelete(long id) {
        Prenotazione found = this.findPrenotazioneById(id);
        this.repositoryPrenotazione.delete(found);
    }

}
