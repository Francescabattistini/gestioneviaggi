package francescabattistini.gestioneviaggi.Controllers;

import francescabattistini.gestioneviaggi.PayloadDTO.DipendenteDto;
import francescabattistini.gestioneviaggi.Services.DipendenteService;
import francescabattistini.gestioneviaggi.entities.Dipendente;
import francescabattistini.gestioneviaggi.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.stream.Collectors;
/*1. GET http://localhost:3003/dipendente
2. POST http://localhost:3003/dipendente (+ dto)
3. GET http://localhost:3003/dipendente/{userId}
4. PUT http://localhost:3003/dipendente/{userId} (+ dto)
5. DELETE http://localhost:3003/dipendente/{userId}

 */

@RestController
@RequestMapping("/dipendente")
public class DipendenteController {

    @Autowired
    DipendenteService dipendenteService;

    @GetMapping
    public Page<Dipendente> findDipendenteAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "nome") String sortBy) {
        return this.dipendenteService.findAll(page, size, sortBy);
    }

    @GetMapping("/{dipeId}")
    public Dipendente findDipendenteByid(@PathVariable long dipendenteId) {
        return this.dipendenteService.findById(dipendenteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente save(@RequestBody @Validated DipendenteDto body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }
            return this.dipendenteService.save(body);
    }
    @PutMapping("/{dipeId}")
    public Dipendente findDipendenteByIdAndUpdate(@PathVariable long dipendenteId, @RequestBody DipendenteDto body) {
        return this.dipendenteService.findByIdAndUpdate(dipendenteId, body);
    }


    @DeleteMapping("/{dipeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)//204
    public void findDipendenteIdAndDelate(@PathVariable long dipendenteId) {
        this.dipendenteService.findByIdAndDelete(dipendenteId);
    }
    @PatchMapping("/{dipeId}/img")
    public String uploadImg(@RequestParam("Img") MultipartFile immagine) {
        return this.dipendenteService.uploadImgProfilo(immagine);
    }

}
