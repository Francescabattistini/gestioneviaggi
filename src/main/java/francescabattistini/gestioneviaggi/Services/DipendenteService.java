package francescabattistini.gestioneviaggi.Services;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import francescabattistini.gestioneviaggi.PayloadDTO.DipendenteDto;
import francescabattistini.gestioneviaggi.entities.Dipendente;
import francescabattistini.gestioneviaggi.exceptions.BadRequestException;
import francescabattistini.gestioneviaggi.exceptions.NotFoundException;
import francescabattistini.gestioneviaggi.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private Cloudinary cloudinaryUploader;
    public Page<Dipendente> findAll(int page, int size, String sortBy) {
        if (page > 15) page = 15;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return dipendenteRepository.findAll(pageable);
    }

    public Dipendente findById( long dipendenteId) {
        return this.dipendenteRepository.findById(dipendenteId).orElseThrow(() -> new NotFoundException(dipendenteId));
    }

    public Dipendente save(DipendenteDto body) {
        this.dipendenteRepository.findByEmail(body.email()).ifPresent(
                dipendente -> {
                    throw new BadRequestException("Email " + body.email() + " già in uso!");
                }
        );
        Dipendente dipendente = new Dipendente(
                body.username(),
                body.nome(),
                body.cognome(),
                body.email(),
                "https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome());
        return this.dipendenteRepository.save(dipendente);
    }


    public Dipendente findByIdAndUpdate(long dipendenteId, DipendenteDto body) {
        Dipendente found = this.findById(dipendenteId);
        if (!found.getEmail().equals(body.email())) {
            this.dipendenteRepository.findByEmail(body.email()).ifPresent(
                    user -> {
                        throw new BadRequestException("Email " + body.email() + " già in uso!");
                    }
            );
        }
        found.setUsername(body.username());
        found.setNome(body.nome());
        found.setCognome(body.cognome());
        found.setEmail(body.email());
        found.setImgProfilo("https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome());

        return this.dipendenteRepository.save(found);
    }

    public void findByIdAndDelete(long dipendenteId) {
        Dipendente dipe = this.findById(dipendenteId);
        this.dipendenteRepository.delete(dipe);
    }
    public String uploadImgProfilo (MultipartFile file) {

        String url = null;
        try {
            url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        } catch (IOException e) {
            throw new BadRequestException("Ci sono stati problemi con l'upload del file!");
        }
        return url;
    }


}