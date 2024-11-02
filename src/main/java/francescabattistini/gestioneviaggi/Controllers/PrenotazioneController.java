package francescabattistini.gestioneviaggi.Controllers;


import francescabattistini.gestioneviaggi.PayloadDTO.PrenotazioneDto;
import francescabattistini.gestioneviaggi.Services.PrenotazioneService;
import francescabattistini.gestioneviaggi.entities.Prenotazione;
import francescabattistini.gestioneviaggi.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    PrenotazioneService prenotazioneService;

    @GetMapping
    public Page<Prenotazione> findAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "dataPrenotazione") String sortBy) {
        return this.prenotazioneService.findPrenotazioneAll(page, size, sortBy);
    }

    @GetMapping("/{prenotazioneId}")
    public Prenotazione findByid(@PathVariable long prenotazioneId) {
        return this.prenotazioneService.findPrenotazioneById(prenotazioneId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione save(@RequestBody @Validated PrenotazioneDto body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }
        return this.prenotazioneService.save(body);
    }

    @PutMapping("/{prenotazioneId}")
    public Prenotazione findPrenotazioneByIdAndUpdate(@PathVariable long prenotazioneid, @RequestBody PrenotazioneDto body) {
        return this.prenotazioneService.findByIdAndUpdate(prenotazioneid, body);
    }

    @DeleteMapping("/{prenotazioneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) {

        this.prenotazioneService.findByIdAndDelete(id);
    }


}
