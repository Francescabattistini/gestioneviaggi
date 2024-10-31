package francescabattistini.gestioneviaggi.Services;

import francescabattistini.gestioneviaggi.PayloadDTO.DipendenteDto;
import francescabattistini.gestioneviaggi.entities.Dipendente;
import francescabattistini.gestioneviaggi.exceptions.BadRequestException;
import francescabattistini.gestioneviaggi.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListIterator;


@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    public List<Dipendente>findAll(){
        return this.dipendenteRepository.findAll();
    }


    public Dipendente save(DipendenteDto body) {
        this.dipendenteRepository.findByEmail(body.email()).ifPresent(autore->{
                    throw new BadRequestException("email"+body.email()+"già in uso");
                }
        );
        Dipendente newAuthor = new Dipendente(body.nome(), body.Cognome(), body.email(),body.username(), body.imgProfilo());
        return this.dipendenteRepository.save(newAuthor);
    }



}