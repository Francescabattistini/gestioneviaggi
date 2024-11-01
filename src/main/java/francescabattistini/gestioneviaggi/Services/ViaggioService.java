package francescabattistini.gestioneviaggi.Services;
import francescabattistini.gestioneviaggi.PayloadDTO.ViaggioDto;
import francescabattistini.gestioneviaggi.entities.Viaggio;
import francescabattistini.gestioneviaggi.exceptions.NotFoundException;
import francescabattistini.gestioneviaggi.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
@Service
public class ViaggioService {
    @Autowired
    private ViaggioRepository viaggioRepository;
    //GET --------------------------------------------

    public Page<Viaggio> findAll(int page, int size, String sortBy) {
        if (page > 15) page = 15;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return viaggioRepository.findAll(pageable);
    }

    public Viaggio findViaggioById( long viaggioId) {
        return this.viaggioRepository.findById(viaggioId).orElseThrow(() -> new NotFoundException(viaggioId));
    }
    public Viaggio save(ViaggioDto body) {
        Viaggio newViaggio = new Viaggio(
                body.destinazione(),
                body.dataviaggio(),
                body.statoprenotazione());
        return this.viaggioRepository.save(newViaggio);
    }
    public Viaggio findByIdAndUpdate(long viaggioId, ViaggioDto body) {
        Viaggio found = this.findViaggioById(viaggioId);

        found.setDestinazione(body.destinazione());
        found.setStatoPrenotazione(body.statoprenotazione());
        found.setStatoPrenotazione(String.valueOf(body.dataviaggio()));

        return this.viaggioRepository.save(found);
    }

    public void findByIdAndDelete(long id) {
        Viaggio found = this.findViaggioById(id);
        this.viaggioRepository.delete(found);
    }


}
