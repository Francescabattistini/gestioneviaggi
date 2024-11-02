package francescabattistini.gestioneviaggi.Controllers;

import francescabattistini.gestioneviaggi.PayloadDTO.ViaggioDto;
import francescabattistini.gestioneviaggi.Services.ViaggioService;
import francescabattistini.gestioneviaggi.entities.Viaggio;
import francescabattistini.gestioneviaggi.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {
    @Autowired
    ViaggioService viaggioService;

    @GetMapping
    public Page<Viaggio> findDipendenteAll(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size,
                                              @RequestParam(defaultValue = "destinazione") String sortBy) {
        return this.viaggioService.findAll(page, size, sortBy);
    }

    @GetMapping("/{viaggioId}")
    public Viaggio findDipendenteByid(@PathVariable long viaggioId) {
        return this.viaggioService.findViaggioById(viaggioId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio save(@RequestBody @Validated ViaggioDto body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.viaggioService.save(body);
    }

    @PutMapping("/{viaggioid}")
    public Viaggio findViaggioByIdAndUpdate(@PathVariable long id, @RequestBody ViaggioDto body) {
        return this.viaggioService.findByIdAndUpdate(id, body);
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) {
        this.viaggioService.findByIdAndDelete(id);
    }


}
